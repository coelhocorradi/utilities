package br.com.utilities.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CsvWriter {

	private File f;

	private String delimiter;

	private FileOutputStream fos = null;

	public static CsvWriter createDefault(File f) {
		return new CsvWriter(f, ",");
	}

	public CsvWriter(File f, String delimiter) {
		this.f = f;
		this.delimiter = delimiter;
		try {
			fos = new FileOutputStream(this.f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void delimiter() {
		try {
			fos.write(delimiter.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void write(byte[] b) {
		try {
			fos.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(String[] strs) {
		for (int i = 0; i < strs.length; i++) {
			if (i != 0)
				delimiter();
			write(strs[i].getBytes());
		}
	}

	public void writeLine(String[] strs) {
		for (int i = 0; i < strs.length; i++) {
			if (i != 0)
				delimiter();
			write(strs[i].getBytes());
		}
		newLine();
	}

	public void newLine() {
		try {
			fos.write(SystemUtils.lineSeparator.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
