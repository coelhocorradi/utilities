package br.com.utilities.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public abstract class FileUtils {

	/*
	 * @Deprecated public static String getExtensionFile(String filePah) {
	 * 
	 * if (filePah.lastIndexOf(".") != -1 && filePah.lastIndexOf(".") != 0) { return
	 * filePah.substring(filePah.lastIndexOf(".") + 1); } else { return ""; } }
	 */

	/*
	 * @Deprecated public static File getFile(String filePath, String localFileName,
	 * String fileName) {
	 * 
	 * Path path = Paths.get(filePath + "/" + localFileName); File file =
	 * path.toFile();
	 * 
	 * return file; }
	 */

	public static File getFile(String filePath, String localFileName) {
		Path path = Paths.get(filePath + "/" + localFileName);
		File file = path.toFile();
		return file;
	}

	/*
	 * public static String getFileExtension(String filename) { return
	 * FilenameUtils.getExtension(filename); }
	 * 
	 * public static String getFileExtension(File file) { return
	 * FilenameUtils.getExtension(file.getAbsolutePath()); }
	 */

	public static String getFileExtension(String filename) {
		String result = "";
		if (filename != null && !filename.trim().isEmpty()) {
			filename = filename.trim();
			if (filename.lastIndexOf(".") > 0) {
				result = filename.substring(filename.lastIndexOf(".") + 1);
			}
		}
		return result;
	}

	public static String getFileExtension(File file) {
		return getFileExtension(file.getAbsolutePath());
		// return FilenameUtils.getExtension(file.getAbsolutePath());
	}

	public static String getFileBaseName(String filename) {
		return FilenameUtils.getBaseName(filename);
	}

	public static String getFileBaseName(File file) {
		return FilenameUtils.getBaseName(file.getAbsolutePath());
	}

	public static BasicFileAttributes getFileAttributes(File file) {
		BasicFileAttributes result = null;
		try {
			result = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getJsonFileAttributes(File file) {
		String result = null;
		try {
			result = new Gson().toJson(getFileAttributes(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean saveFile(String fileName, byte[] dados, String[] replacesFrom, String[] replacesTo) {
		boolean result = false;
		try {
			if (dados == null) {
				throw new Exception("The byte array can't be null!");
			}
			if (replacesFrom != null && replacesTo != null) {
				if (replacesFrom.length != replacesTo.length) {
					throw new Exception("The length of arrays of replaces 'from' and 'to' must have the same lenght!");
				}
			}

			File file = new File(fileName.toString());
			if (!file.exists()) {
				file.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));

				String str = new String(dados, StandardCharsets.UTF_8);

				for (int i = 0; i < replacesFrom.length; i++) {
					String rplFrom = replacesFrom[i], rplTo = replacesTo[i];
					str = str.replace(rplFrom, rplTo);
				}

				bw.write(str);
				bw.flush();
				bw.close();
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean saveFile(String fileName, byte[] dados) {
		return saveFile(fileName, dados, null, null);
	}

	public static boolean fileCopy(String fileNameFrom, String fileNameTo, String[] replaceFrom, String[] replaceTo) {
		boolean result = false;
		try {
			File fileFrom = new File(fileNameFrom);

			if (fileFrom.exists() && fileFrom.isFile()) {

				FileInputStream in = new FileInputStream(fileFrom);

				byte[] dados = IOUtils.toByteArray(in);

				result = saveFile(fileNameTo, dados, replaceFrom, replaceTo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean fileCopy(String fileNameFrom, String fileNameTo) {
		return fileCopy(fileNameFrom, fileNameTo, null, null);
	}
}
