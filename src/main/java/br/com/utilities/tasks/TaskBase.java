package br.com.utilities.tasks;

import java.io.Serializable;
import java.lang.reflect.Type;

import org.apache.log4j.Logger;

import com.google.gson.reflect.TypeToken;

import br.com.utilities.datetime.DateUtils;
import br.com.utilities.enums.EPriority;
import br.com.utilities.exceptions.StillProcessingException;
import br.com.utilities.interfaces.ITaskManager;
import br.com.utilities.schedulers.CustomSchedulerService;

public abstract class TaskBase<T> implements ITaskManager, Runnable, Comparable<TaskBase<?>>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7569007931557778972L;

	public static final String prefix = "spring.tasks";

	protected EPriority priority;

	protected Logger log;

	protected boolean processando = false;

	protected String nome;

	protected String info;

	protected String id;

	protected ITaskManager taskManager;

	public TaskBase() {
		this.priority = EPriority.NORMAL;
		Type type = new TypeToken<T>() {
		}.getType();
		log = Logger.getLogger(type.getClass());
		this.taskManager = null;
	}

	public TaskBase(String nome, String info) {
		this.priority = EPriority.NORMAL;
		Type type = new TypeToken<T>() {
		}.getType();
		log = Logger.getLogger(type.getClass());
		this.taskManager = null;
		this.nome = nome;
		this.info = info;
	}

	public String getNome() {
		return nome != null ? nome : "nome";
	}

	public String getInfo() {
		return info != null ? info : "info";
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public EPriority getPriority() {
		return priority;
	}

	public void setPriority(EPriority priority) {
		this.priority = priority;
	}

	public ITaskManager getTaskManager() {
		return taskManager;
	}

	public void setTaskManager(ITaskManager taskManager) {
		this.taskManager = taskManager;
	}

	public boolean getProcessando() {
		return processando;
	}

	public void setLoggerBy(Class<?> clazz) {
		log = Logger.getLogger(clazz);
	}

	public void onInicialize() throws Exception {
		log.info("Current Thread : { " + Thread.currentThread().getName() + " }");

		if (!podeExecutarTarefas()) {
			throw new StillProcessingException(getInfo() + " - N�o inicializado devido a parametro.");
		}

		log.info(getInfo() + " - Inicializando.");

		Type type = new TypeToken<T>() {
		}.getType();
		CustomSchedulerService.marcarStatus(id, type.getClass().getCanonicalName(), DateUtils.now(), "iniciado", null);
	}

	public void onExecute() {
		log.info(getInfo() + " - Executando.");
	}

	public void stillExecute() {
		log.info(getInfo() + " - Ainda executando itera��o anterior.");
	}

	public void onFinalize() {
		log.info(getInfo() + " - Finalizado.");
		processando = false;
		Type type = new TypeToken<T>() {
		}.getType();
		CustomSchedulerService.marcarStatus(id, type.getClass().getCanonicalName(), DateUtils.now(), "terminado", null);
	}

	public void onFinalize(Exception e) {
		log.info(getInfo() + " - Finalizado.");
		if (!StillProcessingException.class.isInstance(e)) {
			processando = false;
		}
		Type type = new TypeToken<T>() {
		}.getType();
		String info = "erro";
		if (e.getCause().getMessage() == "executando") {
			info = "executando";
		}
		CustomSchedulerService.marcarStatus(id, type.getClass().getCanonicalName(), DateUtils.now(), info,
				e.getMessage());
	}

	public void verifyProcess() throws Exception {
		if (processando) {
			throw new Exception("Ainda processando dados anteriores", new Throwable("executando"));
		} else {
			processando = true;
		}
	}

	public void inicializar() {
		try {
			onInicialize();

			verifyProcess();

			executar();

			onFinalize();
		} catch (Exception e) {
			onFinalize(e);
			log.error(e.getMessage(), e.getCause());
		} finally {
			atualiza();
		}
	}

	protected void atualiza() {
		Type type = new TypeToken<T>() {
		}.getType();
		CustomSchedulerService.servicoExecutado(id, type.getClass().getCanonicalName());
	}

	@Override
	public boolean podeExecutarTarefas() {
		return (taskManager != null) ? taskManager.podeExecutarTarefas() : true;
	}

	@Override
	public int compareTo(TaskBase<?> o) {
		return getPriority().compareTo(o.getPriority());
	}

	@Override
	public void run() {
		inicializar();
	}

	public abstract void executar() throws Exception;

}