package br.com.utilities.schedulers;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import br.com.utilities.datetime.DateFormater;
import br.com.utilities.tasks.TaskBase;

@Component
public abstract class CustomSchedulerTask extends TaskBase<CustomSchedulerTask> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3833546427763015715L;

	protected static Logger log = Logger.getLogger(CustomSchedulerTask.class);

	protected static boolean ENABLE_CRONJOBS = true;

	protected boolean ativo;

	protected String origem;

	public void setString(String origem) {
		this.origem = origem;
	}

	public String getOrigem() {
		return origem;
	}

	public CustomSchedulerTask() {
		super();
		super.nome = "reagendador_servicos_padrao";
		super.info = "Reagendador de serviços padrão";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void executar() {
		try {
			if (!ENABLE_CRONJOBS)
				return;

			CustomSchedulerService schedulerService = CustomSchedulerService.getInstance();

			List<GrRegistroDeServico> ceList = CustomSchedulerService.getRegistrosDeServicoPorOrigem(ativo, origem);

			if (ceList != null) {
				log.info(DateFormater.toCanonical(new Date()) + " : " + this.getClass().getSimpleName());

				for (GrRegistroDeServico ce : ceList) {

					Class<? extends TaskBase<?>> classJob = (Class<? extends TaskBase<?>>) Class
							.forName(ce.getCaminho());
					if (classJob == null)
						continue;

					ScheduledFuture<?> sf = schedulerService.foundScheduler(ce.getId());

					// se não existir na lista e job estiver ativo no banco
					if (sf == null && ce.getServicoAtivo()) {
						schedulerService.addTaskToScheduler(classJob, ce);
					} else
					// se existir na lista e job estiver inativo no banco
					if (sf != null && !ce.getServicoAtivo()) {
						schedulerService.removeTaskFromScheduler(ce);
					} else
					// se existir na lista e job estiver ativo no banco
					if (sf != null && ce.getServicoAtivo()) {
						if (ce.getReagendarServico()) {
							if (schedulerService.rescheduleTaskToScheduler(classJob, ce)) {
								ce.setReagendarServico(false);
								CustomSchedulerService.servicoReagendado(ce.getId());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("Erro ao executar o Job de Agendamento de serviços.", e);
		}
	}

}
