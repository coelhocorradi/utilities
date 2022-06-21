package br.com.utilities.utils;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.jaxen.JaxenException;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;

/**
 * 
 * @author gustavo
 *
 */
public abstract class Dom4jUtils {

	private static Map<String, String> namespaceUris = new HashMap<String, String>();
	private static XPath xPath;
	private static Node node;

	static {
		namespaceUris.put("cte", "http://www.portalfiscal.inf.br/cte");
	}

	/**
	 * 
	 * @param id
	 * @param uri
	 */
	public static void setUri(String id, String uri) {
		namespaceUris.put(id, uri);
	}

	/**
	 * 
	 */
	public static void releaseAndNew() {
		namespaceUris = new HashMap<String, String>();
	}

	/**
	 * 
	 * @param file
	 * @param caminho
	 * @return
	 * @throws JaxenException
	 * @throws DocumentException
	 */
	public static String obterNodeText(byte[] file, String caminho) throws JaxenException, DocumentException {
		xPath = new Dom4jXPath(caminho);
		xPath.setNamespaceContext(new SimpleNamespaceContext(namespaceUris));
		if ((node = (Node) xPath.selectSingleNode(new SAXReader().read(new ByteArrayInputStream(file)))) == null)
			return null;
		return node.getText();
	}

	/**
	 * 
	 * @param file
	 * @param caminho
	 * @return
	 * @throws JaxenException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static List<DefaultElement> obterNodeSelect(byte[] file, String caminho)
			throws JaxenException, DocumentException {
		xPath = new Dom4jXPath(caminho);
		xPath.setNamespaceContext(new SimpleNamespaceContext(namespaceUris));
		return (xPath.selectNodes(new SAXReader().read(new ByteArrayInputStream(file))));
	}

	/**
	 * 
	 * @param file
	 * @param caminho
	 * @param elemento
	 * @return
	 * @throws JaxenException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static String obterNodeSelect(byte[] file, String caminho, int elemento)
			throws JaxenException, DocumentException {
		xPath = new Dom4jXPath(caminho);
		xPath.setNamespaceContext(new SimpleNamespaceContext(namespaceUris));
		return ((List<DefaultElement>) xPath.selectNodes(new SAXReader().read(new ByteArrayInputStream(file))))
				.get(elemento).getText();
	}

	/**
	 * 
	 * @param file
	 * @param caminho
	 * @param elemento
	 * @return
	 * @throws JaxenException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static DefaultElement obterDefaultElementSelect(byte[] file, String caminho, int elemento)
			throws JaxenException, DocumentException {
		xPath = new Dom4jXPath(caminho);
		xPath.setNamespaceContext(new SimpleNamespaceContext(namespaceUris));
		return ((List<DefaultElement>) xPath.selectNodes(new SAXReader().read(new ByteArrayInputStream(file))))
				.get(elemento);
	}
}