package br.com.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface CommandLineReader {

	public default void ReadLines(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {

			String command = null;

			while (true) {
				command = br.readLine();

				System.out.println(command);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
