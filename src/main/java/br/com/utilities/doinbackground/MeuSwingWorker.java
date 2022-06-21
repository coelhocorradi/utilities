package br.com.utilities.doinbackground;

import java.util.List;

import javax.swing.SwingWorker;

public class MeuSwingWorker extends SwingWorker<String, String> {

	@Override
	protected void process(List<String> chunks) {
		super.process(chunks);
	}

	@Override
	protected String doInBackground() throws Exception {
		return null;
	}

	@Override
	protected void done() {
		super.done();
	}
}