package br.com.utilities.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class ShellCMD {

	/**
	 * como exemplo, a lista de commandos com monta_windows
	 */
	private static String[] commandList = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			// add("~/../usr/local/bin/monta_windows");
			add("/usr/local/bin/monta_windows");
		}
	}.toArray(new String[0]);

	/**
	 * subistitui a lista de commandos existentes pela lista de commandos definida
	 * pelo usuário
	 * 
	 * @param commands
	 */
	public static final void setCommands(List<String> commands) {
		commandList = commands.toArray(new String[0]);
	}

	/**
	 * adiciona uma lista de commandos ao final da lista de commandos
	 * 
	 * @param commands
	 */
	public static final void addCommands(String... commands) {
		List<String> aux = new ArrayList<String>();
		for (String s : commandList) {
			aux.add(s);
		}
		for (String s : commands) {
			aux.add(s);
		}
		commandList = aux.toArray(new String[0]);
	}

	/**
	 * adiciona uma lista de commandos ao final da lista de commandos
	 * 
	 * @param commands
	 */
	public static final void addCommands(List<String> commands) {
		List<String> aux = new ArrayList<String>();
		for (String s : commandList) {
			aux.add(s);
		}
		aux.addAll(commands);
		commandList = aux.toArray(new String[0]);
	}

	/**
	 * adiciona um commando ao final da lista de commandos
	 * 
	 * @param command
	 */
	public static final void addCommand(String command) {
		List<String> aux = new ArrayList<String>();
		for (String s : commandList) {
			aux.add(s);
		}
		aux.add(command);
		commandList = aux.toArray(new String[0]);
	}

	private static final void execute() {
		execute(commandList);
	}

	public static final void execute(List<String> commands) {
		execute(commands.toArray(new String[0]));
	}

	/**
	 * informe a lista de commandos a ser executada
	 * 
	 * @param commands
	 */
	public static final void execute(String[] commands) {
		for (String str : commands) {
			try {
				Process proc = Runtime.getRuntime().exec(str);
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

				// Read the output from the command
				System.out.println("Here is the standard output of the command:\n");
				String s = null;
				while ((s = stdInput.readLine()) != null) {
					System.out.println(s);
				}

				// Read any errors from the attempted command
				System.out.println("Here is the standard error of the command (if any):\n");
				while ((s = stdError.readLine()) != null) {
					System.out.println(s);
				}
			} catch (IOException e) {
				System.out.println("Can not execute mount script... Try start than manualy!");
				e.printStackTrace();
			}
		}
	}

	public static final void initialize() {
		if (OSValidator.isWindows()) {
			System.out.println("OS Windows, could not needed to execute mount script!");
		} else if (OSValidator.isUnix()) {
			System.out.println("Unix based OS, It's must execute mount script!");
			execute();
		}
	}
}
