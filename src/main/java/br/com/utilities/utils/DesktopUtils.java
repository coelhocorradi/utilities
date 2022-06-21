package br.com.utilities.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public abstract class DesktopUtils {

	public static void openFolder(String path) {
		openFile(new File(path));
	}

	public static void openFile(File file) {
		try {
			if (!Desktop.isDesktopSupported()) {
				throw new Exception("Desktop is not supported!");
			}
			if (file.exists()) {
				Desktop.getDesktop().open(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
