package br.com.utilities.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 * 
 * @author gustavo
 *
 */
public abstract class MailUtils {

	public static final Integer DEFAULT_PORT = 25;

	/**
	 * 
	 * @param sender
	 * @param receivers
	 * @param ccs
	 * @param bccs
	 * @param subject
	 * @param msg
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 */
	public void sendMail(String sender, String[] receivers, String[] ccs, String[] bccs, String subject, String msg,
			String host, Integer port, String user, String password) {
		try {
			if (sender == null) {
				throw new Exception("The 'sender' param can not be null!");
			} else if (sender.isEmpty()) {
				throw new Exception("You can not post mail without sender mail!");
			}

			if (receivers == null) {
				throw new Exception("The 'receivers' param can not be null!");
			} else if (receivers.length == 0) {
				throw new Exception("You can not post mail without receivers mails!");
			}

			if (host == null) {
				throw new Exception("The 'host' param can not be null!");
			}

			if (port == null) {
				throw new Exception("The 'port' param can not be null!");
			}

			if (user == null) {
				throw new Exception("The 'user' param can not be null!");
			}

			if (password == null) {
				throw new Exception("The 'password' param can not be null!");
			}

			Email email = new SimpleEmail();
			email.setFrom(sender);
			for (String value : receivers) {
				email.addTo(value);
			}
			if (ccs != null) {
				for (String value : ccs) {
					email.addCc(value);
				}
			}
			if (bccs != null) {
				for (String value : bccs) {
					email.addBcc(value);
				}
			}
			if (subject != null) {
				email.setSubject(subject);
			}
			if (msg != null) {
				email.setMsg(msg);
			}
			email.setHostName(host);
			email.setSmtpPort(port);
			email.setAuthenticator(new DefaultAuthenticator(user, password));

			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param sender
	 * @param receivers
	 * @param subject
	 * @param msg
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 */
	public void sendMail(String sender, String[] receivers, String subject, String msg, String host, Integer port,
			String user, String password) {
		sendMail(sender, receivers, null, null, subject, msg, host, port, user, password);
	}

	/**
	 * 
	 * @param sender
	 * @param receivers
	 * @param subject
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 */
	public void sendMail(String sender, String[] receivers, String subject, String host, Integer port, String user,
			String password) {
		sendMail(sender, receivers, null, null, subject, null, host, port, user, password);
	}

}