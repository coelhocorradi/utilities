package br.com.utilities.datetime;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class TimeProcess {

	private static final Logger log = LoggerFactory.getLogger(TimeProcess.class);

	private Instant t1, t2;

	boolean logEnabled;

	public TimeProcess() {
		logEnabled = false;
		start();
	}

	public TimeProcess(String information) {
		logEnabled = false;
		start(information);
	}

	public TimeProcess(boolean logEnabled) {
		this.logEnabled = logEnabled;
		start();
	}

	public TimeProcess(String information, boolean logEnabled) {
		this.logEnabled = logEnabled;
		start(information);
	}

	private void start() {
		start(null);
	}

	public void start(String information) {
		t1 = Instant.now();
		if (this.logEnabled) {
			String aux = (information != null ? information + " : " : "");
			log.info(StringUtils.capitalize(aux + "process started at " + DateFormater.toCanonical(new Date())));
		}
	}

	public void stop() {
		stop(null);
	}

	public void stop(String information) {
		t2 = Instant.now();
		if (this.logEnabled) {
			String aux = (information != null ? information + " : " : "");
			log.info(StringUtils.capitalize(aux + "process ended at " + DateFormater.toCanonical(new Date())));
			log.info(StringUtils.capitalize(aux + "time ellapsed in process " + getEllapsedTime() + " milliseconds"));
		}
	}

	public long getEllapsedTime() {
		return Duration.between(t1, t2).toMillis();
	}
}
