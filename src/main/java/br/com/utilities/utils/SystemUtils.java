package br.com.utilities.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.utilities.json.JsonObjectExt;

/**
 * 
 * @author gustavo
 *
 */
public abstract class SystemUtils {

	public static final String fileSeparator = System.getProperty("file.separator");

	public static final String javaClassPath = System.getProperty("java.class.path");

	public static final String javaVendor = System.getProperty("java.vendor");

	public static final String javaVendorUrl = System.getProperty("java.vendor.url");

	public static final String javaVersion = System.getProperty("java.version");

	public static final String lineSeparator = System.getProperty("line.separator");

	public static final String osArchitecture = System.getProperty("os.arch");

	public static final String osName = System.getProperty("os.name");

	public static final String osVersion = System.getProperty("os.version");

	public static final String pathSeparator = System.getProperty("path.separator");

	public static final String userWorkingDirectory = System.getProperty("user.dir");

	public static final String userHomeDirectory = System.getProperty("user.home");

	public static final String userName = System.getProperty("user.name");

	/**
	 * @return
	 */
	public static final File getUserDirectory() {
		return new File(userHomeDirectory);
	}

	/**
	 * 
	 * @return
	 */
	public static final File getDefaultDirectory() {
		return FileSystemView.getFileSystemView().getDefaultDirectory();
	}

	public static final String defaultDirectory = getDefaultDirectory().getAbsolutePath();

	/**
	 * 
	 * @return
	 */
	public static final File getHomeDirectory() {
		return FileSystemView.getFileSystemView().getHomeDirectory();
	}

	/**
	 * No windows, homeDirectory � o desktop.
	 */
	public static final String homeDirectory = getHomeDirectory().getAbsolutePath();

	/**
	 * 
	 * @param f
	 * @return
	 */
	public static final boolean verifyIsRoot(File f) {
		return FileSystemView.getFileSystemView().isRoot(f);
	}

	/**
	 * 
	 * @param f
	 * @return
	 */
	public static final boolean verifyIsRoot(String f) {
		return verifyIsRoot(new File(f));
	}

	/**
	 * 
	 * @return
	 */
	public static final File[] getRoots() {
		return FileSystemView.getFileSystemView().getRoots();
	}

	/**
	 * 
	 * @return
	 */
	public static final String[] getRootPaths() {
		List<String> list = new LinkedList<String>();
		for (File f : getRoots()) {
			list.add(f.getAbsolutePath());
		}
		return CollectionUtils.<String>toArray(list);
	}

	private static Map<String, Object> systemProperties = null;

	static {
		systemProperties = new HashMap<String, Object>();
		systemProperties.put("java.vendor", javaVendor);
		systemProperties.put("java.version", javaVersion);
		systemProperties.put("java.vendor.url", javaVendorUrl);
		systemProperties.put("os.name", osName);
		systemProperties.put("os.arch", osArchitecture);
		systemProperties.put("os.version", osVersion);
	}

	/**
	 * 
	 * @return
	 */
	public static final JsonObject getSystemProperties() {
		return new JsonObjectExt().mapToJson(systemProperties).getJsono();
	}

	/**
	 * 
	 */
	public static final void showSystemProperties() {
		System.out.println(getSystemProperties().toString());
	}

	/**
	 * valida��o se existe o caminho. se n�o existir for a cria��o do caminho
	 * indicado. se debug cria a pasta e subpastas do caminho do desktop se windows,
	 * ou homedirectory em outros;
	 * 
	 * @param path
	 * @return
	 */
	public static final String validatePath(String path, boolean useHome) {
		String result = "";
		if (useHome) {
			result = homeDirectory;
		}
		result += path;
		File aux = new File(path);
		while (!aux.exists()) {
			aux.mkdirs();
		}
		return result;
	}

	/**
	 * can be used to open a file or a folder
	 * 
	 * @param path
	 */
	public static void openFile(String path) {
		openFile(new File(path));
	}

	/**
	 * can be used to open a file or a folder
	 * 
	 * @param file
	 */
	public static void openFile(File file) {
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get System Informations
	 */
	public static void systemInfo() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("Available processors (cores): ").append(Runtime.getRuntime().availableProcessors())
					.append(lineSeparator);
			sb.append("Total memory (bytes): ").append(Runtime.getRuntime().totalMemory()).append(lineSeparator);
			sb.append("Maximum memory (bytes): ").append(Runtime.getRuntime().maxMemory()).append(lineSeparator);
			sb.append("Free memory (bytes): ").append(Runtime.getRuntime().freeMemory()).append(lineSeparator);

			// http://download.oracle.com/javase/6/docs/jre/api/management/extension/com/sun/management/OperatingSystemMXBean.html#getTotalPhysicalMemorySize%28%29
			File file = new File("");
			long totalSpace = file.getTotalSpace();
			long usableSpace = file.getUsableSpace();
			sb.append("Total space (bytes): ").append(totalSpace).append(lineSeparator);
			sb.append("Usable space (bytes): ").append(usableSpace).append(lineSeparator);

			Map<String, String> env = System.getenv();
			for (String envName : env.keySet()) {
				sb.append(String.format("%s=%s%n", envName, env.get(envName))).append(lineSeparator);
			}

			OperatingSystemMXBean osmxBean = ManagementFactory.getOperatingSystemMXBean();
			sb.append("System load average: ").append(osmxBean.getSystemLoadAverage()).append(lineSeparator);
			sb.append("Virtual Machine available processors: ").append(osmxBean.getAvailableProcessors())
					.append(lineSeparator);

			sb.append("Specot Version : ").append(ManagementFactory.getRuntimeMXBean().getSpecVersion())
					.append(lineSeparator);
			sb.append("Uptime : ").append(ManagementFactory.getRuntimeMXBean().getUptime()).append(lineSeparator);
			sb.append("Vm Version : ").append(ManagementFactory.getRuntimeMXBean().getVmVersion())
					.append(lineSeparator);

			MemoryMXBean mxbean = ManagementFactory.getMemoryMXBean();
			MemoryUsage mu = mxbean.getHeapMemoryUsage();
			sb.append("Virtual Machine initial heap memory requested (byte): ").append(mu.getInit())
					.append(lineSeparator);
			sb.append("Virtual Machine max heap memory (bytes): ").append(mu.getMax()).append(lineSeparator);
			sb.append("Virtual Machine used heap memory (bytes): ").append(mu.getUsed()).append(lineSeparator);

			mu = mxbean.getNonHeapMemoryUsage();
			sb.append("Virtual Machine initial non heap memory requested (byte): ").append(mu.getInit())
					.append(lineSeparator);
			sb.append("Virtual Machine max non heap memory (bytes): ").append(mu.getMax()).append(lineSeparator);
			sb.append("Virtual Machine used non heap memory (bytes): ").append(mu.getUsed()).append(lineSeparator);

			System.out.println(sb.toString());
		} catch (Exception e) {
			System.out.println(sb.toString());
			e.printStackTrace();
		}
	}

	/**
	 * get System Informations
	 */
	public static JsonObject jsonSystemInfo() {
		JsonObject jsono = null;
		try {
			jsono = new JsonObject();
			jsono.addProperty("Available processors (cores)", Runtime.getRuntime().availableProcessors());
			jsono.addProperty("Total memory (bytes)", Runtime.getRuntime().totalMemory());
			jsono.addProperty("Maximum memory (bytes)", Runtime.getRuntime().maxMemory());
			jsono.addProperty("Free memory (byte)", Runtime.getRuntime().freeMemory());

			// http://download.oracle.com/javase/6/docs/jre/api/management/extension/com/sun/management/OperatingSystemMXBean.html#getTotalPhysicalMemorySize%28%29
			File file = new File("");
			long totalSpace = file.getTotalSpace();
			long usableSpace = file.getUsableSpace();
			jsono.addProperty("Total space (bytes)", totalSpace);
			jsono.addProperty("Usable space (bytes)", usableSpace);

			Map<String, String> env = System.getenv();
			for (String envName : env.keySet()) {
				jsono.addProperty(envName, env.get(envName));
			}

			JsonObject jsonvm = new JsonObject();
			OperatingSystemMXBean osmxBean = ManagementFactory.getOperatingSystemMXBean();
			jsonvm.addProperty("System load average", osmxBean.getSystemLoadAverage());
			jsonvm.addProperty("Virtual Machine available processors", osmxBean.getAvailableProcessors());
			jsonvm.addProperty("Spec Version", ManagementFactory.getRuntimeMXBean().getSpecVersion());
			jsonvm.addProperty("Uptime", ManagementFactory.getRuntimeMXBean().getUptime());
			jsonvm.addProperty("Vm Version", ManagementFactory.getRuntimeMXBean().getVmVersion());

			MemoryMXBean mxbean = ManagementFactory.getMemoryMXBean();
			MemoryUsage heapMu = mxbean.getHeapMemoryUsage();
			jsonvm.addProperty("Vm initial heap memory requested (byte)", heapMu.getInit());
			jsonvm.addProperty("Vm initial max memory requested (byte)", heapMu.getMax());
			jsonvm.addProperty("Vm initial used memory requested (byte)", heapMu.getUsed());

			MemoryUsage nonHeapMu = mxbean.getNonHeapMemoryUsage();
			jsonvm.addProperty("Vm initial non heap memory requested (byte)", nonHeapMu.getInit());
			jsonvm.addProperty("Vm initial non max memory requested (byte)", nonHeapMu.getMax());
			jsonvm.addProperty("Vm initial non used memory requested (byte)", nonHeapMu.getUsed());

			jsono.add("Virtual Machine", jsonvm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsono;
	}

	/**
	 * 
	 */
	public static void printSystemInfo() {
		System.out.println(new Gson().toJson(jsonSystemInfo()));
	}

	public static void println(List<String> list) {
		StringBuilder sb = new StringBuilder();
		list.forEach(s -> sb.append(s).append(System.lineSeparator()));
		System.out.println(sb.toString());
	}

	public static void println(String[] list) {
		println(Arrays.asList(list));
	}
}
