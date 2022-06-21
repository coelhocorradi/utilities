package br.com.utilities.composers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class CsvBuffer {

	private StringBuffer sb;

	private String defaultDelimiter;

	private String onull;

	private String newLine;

	private String delimiter;

	public CsvBuffer() {
		defaultDelimiter = ";";
		delimiter = "";
		onull = ""; // "null";
		newLine = System.lineSeparator();
		sb = new StringBuffer();
	}

	public void appendNull() {
		sb.append(onull);
	}

	public StringBuffer append(Object o) {
		sb.append(delimiter);
		if (o == null) {
			appendNull();
		} else if (o instanceof Byte) {
			sb.append(((Byte) o).byteValue());
		} else if (o instanceof Short) {
			sb.append(((Short) o).shortValue());
		} else if (o instanceof Integer) {
			sb.append(((Integer) o).intValue());
		} else if (o instanceof Long) {
			sb.append(((Long) o).longValue());
		} else if (o instanceof Float) {
			sb.append(((Float) o).floatValue());
		} else if (o instanceof Double) {
			sb.append(((Double) o).doubleValue());
		} else if (o instanceof BigDecimal) {
			sb.append(((BigDecimal) o).doubleValue());
		} else if (o instanceof BigInteger) {
			sb.append(((BigInteger) o).intValueExact());
		} else if (o instanceof Character) {
			sb.append(((Character) o).charValue());
		} else if (o instanceof Boolean) {
			sb.append(((Boolean) o).booleanValue());
		} else if (o instanceof String) {
			sb.append((String) o);
		} else if (o instanceof byte[]) {
			sb.append(new String((byte[]) o));
		} else if (o instanceof char[]) {
			sb.append(new String((char[]) o));
		} else if (o instanceof Object) {
			sb.append(o.toString());
		} else if (o instanceof Object[]) {
			for (Object ox : (Object[]) o) {
				append(ox);
			}
		}
		delimiter = defaultDelimiter;
		return sb;
	}

	public StringBuffer newLine() {
		delimiter = "";
		return sb.append(newLine);
	}

	public StringBuffer appendAndNewLine(Object o) {
		append(o);
		return newLine();
	}

	public void saveOnFile(String fileName) {
		try {
			File f = new File(fileName);
			FileOutputStream fos;
			fos = new FileOutputStream(f);
			fos.write(sb.toString().getBytes());
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFromFile(String fileName) {
		try {
			File f = new File(fileName);
			FileReader fr;
			fr = new FileReader(f);
			char[] buffer = new char[(int) f.length()];
			fr.read(buffer);
			sb.append(buffer);
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
