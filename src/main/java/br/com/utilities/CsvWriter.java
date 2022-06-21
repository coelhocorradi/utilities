package br.com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.utilities.utils.SystemUtils;

public class CsvWriter {

	private File f;

	private String delimiter;

	private FileOutputStream fos = null;

	public CsvWriter(File f, String delimiter) {
		this.f = f;
		this.delimiter = delimiter;
		try {
			fos = new FileOutputStream(this.f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void write(byte[] b) {
		try {
			fos.write(b);
			fos.write(delimiter.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(String str) {
		write(str.getBytes());
	}

	public void writeLine(String str) {
		write(str.getBytes());
		newLine();
	}

	public void newLine() {
		// write(delimiter);
		write(SystemUtils.lineSeparator);
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
