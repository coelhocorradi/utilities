package br.com.utilities.doinbackground;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author gustavo
 *
 *         default timeout is 1000 ms default maxTimes is 2 times
 * 
 *         use setTimeout() to set time after period in milliseconds it will
 *         repeat use setMaxTimes() to set how many times it will repeat after
 *         first error use setMessage() to set a custom message if error
 *
 * @param <A>
 */
public abstract class CustomThread<A> extends Thread {

	private int timeout = 1000;

	private int times = 0;

	private int maxTimes = 2;

	private Timer t;

	private String msg;

	private A[] args;

	private CustomThread<A> self;

	public CustomThread() {
		this.msg = null;
		this.args = null;
		self = this;
	}

	/**
	 * A... args - put the some args to execute
	 * 
	 * @param args
	 */
	public CustomThread(@SuppressWarnings("unchecked") A... args) {
		this.msg = null;
		this.args = args;
		self = this;
	}

	/**
	 * set a custom message error
	 * 
	 * @param msg
	 */
	public void setMessage(String msg) {
		this.msg = msg;
	}

	private String resolveMessage() {
		return this.msg != null ? msg : "Try running task again on " + Integer.toString(timeout) + " milliseconds!";
	}

	/**
	 * set the max times to execute schedule after first error
	 * 
	 * @param maxTimes
	 */
	public void setMaxTimes(int maxTimes) {
		this.maxTimes = maxTimes;
	}

	/**
	 * set the timeout used to execute task before this time
	 * 
	 * @param timeout
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public abstract boolean customRun(CustomThread<A> thread, @SuppressWarnings("unchecked") A... args)
			throws Exception;

	@Override
	public void run() {
		try {

			if (customRun(self, args) || times >= maxTimes) {
				throw new Exception(resolveMessage());
			}

			stopMe();

		} catch (Exception e) {
			if (times < maxTimes) {
				times++;

				TimerTask tt = new TimerTask() {

					@Override
					public void run() {
						self.run();
					}
				};

				t = new Timer();
				t.schedule(tt, timeout);
			} else {
				System.out.println("Can not run this task anymore!");
				stopMe();
			}
		}
	}

	private void stopMe() {
		if (t != null) {
			t.purge();
			t.cancel();
			t = null;
		}
	}

}
