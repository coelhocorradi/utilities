package br.com.utilities.schedulers;

import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import br.com.utilities.datetime.DateFormater;
import br.com.utilities.gsonutils.DateTypeAdapter;
import br.com.utilities.gsonutils.FieldNamingStrategies;
import br.com.utilities.interfaces.ITaskManager;
import br.com.utilities.tasks.TaskBase;
import br.com.utilities.utils.HttpUtils;
import br.com.utilities.utils.StringUtils;

@Configuration
@EnableScheduling
public abstract class CustomSchedulerService implements SchedulingConfigurer {

	protected static Logger log = Logger.getLogger(CustomSchedulerService.class);

	private static CustomSchedulerService self;

	public static CustomSchedulerService getInstance() {
		return self;
	}

	protected static String origem = "teste.interno";

	protected static String ROTA_G_API = "http://10.0.0.128/g_api/";

	protected static String ROTA_SGT20_API;

	protected static void resolve() {
		ROTA_SGT20_API = ROTA_G_API + "sgt20_api/gr_registro_de_servico_business/";
	}

	static {
		resolve();
	}

	public static String getOrigem() {
		return origem;
	}

	protected static Class<? extends CustomSchedulerTask> taskClass;

	@Autowired
	private AutowireCapableBeanFactory autowireCapableBeanFactory;

	public ThreadPoolTaskScheduler scheduler;

	protected ITaskManager taskManager;

	private Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

	public CustomSchedulerService() {
		super();
		self = this;
	}

	@Bean
	public TaskScheduler getTaskScheduler() {
		int quantidadeDeServicos = quantidadeDeServicos();
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
		scheduler.setPoolSize(quantidadeDeServicos);
		scheduler.initialize();
		return scheduler;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(getTaskScheduler());
		log.info("procurando " + taskClass.getCanonicalName() + " origem " + origem);
		GrRegistroDeServico rs = getRegistroDeServicoPorCaminho(taskClass.getCanonicalName(), origem);
		if (rs == null) {
			rs = new GrRegistroDeServico();
			rs.setCaminho(taskClass.getCanonicalName());
			rs.setExpressaoCron("0 * * * * ?");
		}
		addTaskToScheduler(taskClass, rs);
	}

	public ScheduledFuture<?> foundScheduler(String id) {
		return jobsMap.get(id);
	}

	public void addTaskToScheduler(Class<? extends TaskBase<?>> jobClass, GrRegistroDeServico rs) {
		try {
			CronTrigger cron = new CronTrigger(rs.getExpressaoCron());
			Constructor<? extends TaskBase<?>> constructor = jobClass.getConstructor();
			TaskBase<?> task = constructor.newInstance();
			task.setTaskManager(taskManager);
			task.setId(rs.getId());
			autowireCapableBeanFactory.autowireBean(task);
			log.info("Iniciando " + rs.getCaminho() + ":" + rs.getId() + " cron expression " + rs.getExpressaoCron());
			addTaskToScheduler(rs.getId(), rs.getCaminho(), task, cron);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void addTaskToScheduler(String id, String jobName, Runnable task, Trigger trigger) {
		try {
			if (jobsMap.get(id) != null) {
				throw new Exception("Task " + jobName + " já iniciada anteriormente!");
			}
			ScheduledFuture<?> scheduledTask = scheduler.schedule(task, trigger);
			jobsMap.put(id, scheduledTask);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean rescheduleTaskToScheduler(Class<? extends TaskBase<?>> jobClass, GrRegistroDeServico rs) {
		boolean result = false;
		try {
			CronTrigger cron = new CronTrigger(rs.getExpressaoCron());
			Constructor<? extends TaskBase<?>> constructor = jobClass.getConstructor();
			TaskBase<?> task = constructor.newInstance();
			task.setTaskManager(taskManager);
			task.setId(rs.getId());
			autowireCapableBeanFactory.autowireBean(task);
			log.info("reagendando " + rs.getCaminho() + ":" + rs.getId() + " cron expression " + rs.getExpressaoCron());
			result = rescheduleTaskToScheduler(rs.getId(), task, cron);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean rescheduleTaskToScheduler(String id, Runnable task, Trigger trigger) {
		boolean result = false;
		try {
			ScheduledFuture<?> scheduledTask = jobsMap.get(id);
			if (scheduledTask != null) {
				scheduledTask.cancel(true);
			}
			scheduledTask = scheduler.schedule(task, trigger);
			jobsMap.put(id, scheduledTask);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void removeTaskFromScheduler(GrRegistroDeServico rs) {
		try {
			removeTaskFromScheduler(rs.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeTaskFromScheduler(String id) {
		try {
			ScheduledFuture<?> scheduledTask = jobsMap.get(id);
			if (scheduledTask != null) {
				scheduledTask.cancel(true);
				jobsMap.remove(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@EventListener({ ContextRefreshedEvent.class })
	void contextRefreshedEvent() {
		// Get all tasks from DB and reschedule them in case of context restarted
	}

	public static List<GrRegistroDeServico> getRegistrosDeServico(Boolean ativo, String origem, String nome,
			String caminho, String id) {
		List<GrRegistroDeServico> result = new ArrayList<>();
		try {
			String url = ROTA_SGT20_API + "listar.php";
			url = HttpUtils.makeUrlByMap(url, (map) -> {
				if (Objects.nonNull(ativo)) {
					map.put("ativo", true);
				}
				if (!StringUtils.isNullOrEmpty(origem)) {
					map.put("origem", origem);
				}
				if (Objects.nonNull(nome)) {
					map.put("nome", nome);
				}
				if (Objects.nonNull(caminho)) {
					map.put("caminho", caminho);
				}
				if (Objects.nonNull(id)) {
					map.put("id", id);
				}
			});
			String json = HttpUtils.httpGetAndReceive(url, MediaType.APPLICATION_JSON_UTF8_VALUE);

			Type type = new TypeToken<List<GrRegistroDeServico>>() {
			}.getType();
			Gson gson = new GsonBuilder()//
					// .setLenient()//
					.serializeNulls()//
					.setPrettyPrinting()//
					.registerTypeHierarchyAdapter(Date.class, DateTypeAdapter.getAdapter())//
					.setFieldNamingStrategy(FieldNamingStrategies.getDefaultJavaFieldFormatToLowerUnderscore())//
					.create();//
			result = gson.fromJson(json, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<GrRegistroDeServico> getRegistrosDeServicoPorOrigem(Boolean ativo, String origem) {
		return getRegistrosDeServico(ativo, origem, null, null, null);
	}

	public static GrRegistroDeServico getRegistroDeServicoPorCaminho(String caminho, String origem) {
		List<GrRegistroDeServico> lista = getRegistrosDeServico(null, origem, null, caminho, null);
		return !lista.isEmpty() ? lista.get(0) : null;
	}

	public static GrRegistroDeServico getRegistroDeServicoPorId(String id) {
		List<GrRegistroDeServico> lista = getRegistrosDeServico(null, null, null, null, id);
		return !lista.isEmpty() ? lista.get(0) : null;
	}

	public static int quantidadeDeServicos() {
		List<GrRegistroDeServico> lista = getRegistrosDeServicoPorOrigem(true, origem);
		return lista != null && !lista.isEmpty() ? lista.size() : 10;
	}

	public static GrRegistroDeServico servicoReagendado(String id) {
		GrRegistroDeServico result = null;
		try {
			String url = ROTA_SGT20_API + "servico_reagendado.php";
			JsonObject jo = new JsonObject();
			jo.addProperty("sec_users_id", "sistema.jornada");
			jo.addProperty("id", id);
			jo.addProperty("origem", origem);
			String json = HttpUtils.httpPostAndReceive(url, jo.toString(), MediaType.APPLICATION_JSON_UTF8_VALUE);

			JsonReader jr = new JsonReader(new StringReader(json));
			jr.setLenient(true);
			jo = new JsonParser().parse(jr).getAsJsonObject();
			if (jo.has("erro")) {
				String erro = jo.get("erro").getAsString();
				throw new Exception(erro);
			} else if (!jo.has("id")) {
				throw new Exception("Não foi possível verificar o reagendamento do serviço!");
			} else {
				Type type = new TypeToken<GrRegistroDeServico>() {
				}.getType();
				Gson gson = new GsonBuilder()//
						// .setLenient()//
						.serializeNulls()//
						.setPrettyPrinting()//
						.registerTypeHierarchyAdapter(Date.class, DateTypeAdapter.getAdapter())//
						.setFieldNamingStrategy(FieldNamingStrategies.getDefaultJavaFieldFormatToLowerUnderscore())//
						.create();//
				result = gson.fromJson(json, type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static GrRegistroDeServico servicoReagendar(String id, String expressao_cron) {
		GrRegistroDeServico result = null;
		try {
			String url = ROTA_SGT20_API + "servico_reagendar.php";
			JsonObject jo = new JsonObject();
			jo.addProperty("sec_users_id", "sistema.jornada");
			jo.addProperty("id", id);
			jo.addProperty("origem", origem);
			jo.addProperty("expressao_cron", expressao_cron);
			String json = HttpUtils.httpPostAndReceive(url, jo.toString(), MediaType.APPLICATION_JSON_UTF8_VALUE);

			JsonReader jr = new JsonReader(new StringReader(json));
			jr.setLenient(true);
			jo = new JsonParser().parse(jr).getAsJsonObject();
			if (jo.has("erro")) {
				String erro = jo.get("erro").getAsString();
				throw new Exception(erro);
			} else if (!jo.has("id")) {
				throw new Exception("Não foi possível verificar o reagendamento do serviço!");
			} else {
				Type type = new TypeToken<GrRegistroDeServico>() {
				}.getType();
				Gson gson = new GsonBuilder()//
						// .setLenient()//
						.serializeNulls()//
						.setPrettyPrinting()//
						.registerTypeHierarchyAdapter(Date.class, DateTypeAdapter.getAdapter())//
						.setFieldNamingStrategy(FieldNamingStrategies.getDefaultJavaFieldFormatToLowerUnderscore())//
						.create();//
				result = gson.fromJson(json, type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static GrRegistroDeServicoStatus marcarStatus(String id, String caminho, Date quando, String status,
			String informacao) {
		GrRegistroDeServicoStatus result = null;
		try {
			String url = ROTA_SGT20_API + "status_servico.php";
			JsonObject jo = new JsonObject();
			jo.addProperty("id", id);
			jo.addProperty("origem", origem);
			jo.addProperty("caminho", caminho);
			jo.addProperty("quando", DateFormater.toDateTime(quando));
			jo.addProperty("status", status);
			informacao = !StringUtils.isNullOrEmpty(informacao) ? informacao : "";
			jo.addProperty("informacao", informacao);
			String json = HttpUtils.httpPostAndReceive(url, jo.toString(), MediaType.APPLICATION_JSON_UTF8_VALUE);

			JsonReader jr = new JsonReader(new StringReader(json));
			jr.setLenient(true);
			jo = new JsonParser().parse(jr).getAsJsonObject();
			if (jo.has("erro")) {
				String erro = jo.get("erro").getAsString();
				throw new Exception(erro);
			} else if (!jo.has("id")) {
				throw new Exception("Não foi possível verificar a marcação do serviço!");
			} else {
				Type type = new TypeToken<GrRegistroDeServicoStatus>() {
				}.getType();
				Gson gson = new GsonBuilder()//
						// .setLenient()
						.serializeNulls()//
						.setPrettyPrinting()//
						.registerTypeHierarchyAdapter(Date.class, DateTypeAdapter.getAdapter())//
						.setFieldNamingStrategy(FieldNamingStrategies.getDefaultJavaFieldFormatToLowerUnderscore())//
						.create();//
				result = gson.fromJson(json, type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static GrRegistroDeServico servicoExecutado(String id, String caminho) {
		GrRegistroDeServico result = null;
		try {
			String url = ROTA_SGT20_API + "servico_executado.php";
			JsonObject jo = new JsonObject();
			jo.addProperty("id", id);
			jo.addProperty("origem", origem);
			jo.addProperty("caminho", caminho);
			String json = HttpUtils.httpPostAndReceive(url, jo.toString(), MediaType.APPLICATION_JSON_UTF8_VALUE);

			JsonReader jr = new JsonReader(new StringReader(json));
			jr.setLenient(true);
			jo = new JsonParser().parse(jr).getAsJsonObject();
			if (jo.has("erro")) {
				String erro = jo.get("erro").getAsString();
				throw new Exception(erro);
			} else if (!jo.has("id")) {
				throw new Exception("Não foi possível verificar a execução do serviço!");
			} else {
				Type type = new TypeToken<GrRegistroDeServico>() {
				}.getType();
				Gson gson = new GsonBuilder()//
						// .setLenient()
						.serializeNulls()//
						.setPrettyPrinting()//
						.registerTypeHierarchyAdapter(Date.class, DateTypeAdapter.getAdapter())//
						.setFieldNamingStrategy(FieldNamingStrategies.getDefaultJavaFieldFormatToLowerUnderscore())//
						.create();//
				result = gson.fromJson(json, type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
