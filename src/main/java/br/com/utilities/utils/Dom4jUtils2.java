package br.com.utilities.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
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
public abstract class Dom4jUtils2 {

	private Map<String, String> namespaceUris = new HashMap<String, String>();
	private XPath xPath;
	private Node node;
	// private String nameSpace;
	private Document document;

	/**
	 * 
	 * @param id
	 * @param uri
	 */
	public void setUri(String id, String uri) {
		namespaceUris.put(id, uri);
	}

	/**
	 * 
	 */
	public void releaseAndNew() {
		namespaceUris = new HashMap<String, String>();
	}

	/**
	 * 
	 * @param nameSpace
	 * @param data
	 */
	public Dom4jUtils2(String nameSpace, byte[] data) {

	}

	/**
	 * 
	 * @param nameSpace
	 * @param data
	 */
	public Dom4jUtils2(String nameSpace, File data) {
		String parts[] = nameSpace.split("/");
		nameSpace = parts[parts.length];
		// Document document = reader.read(file);

		Map<String, String> namespaceUris = new HashMap<String, String>();
		namespaceUris.put("nfe", "http://www.portalfiscal.inf.br/nfe");

		/*
		 * XPath xPath = (XPath) new Dom4jXPath(
		 * "//nfe:NFe/nfe:infNFe/nfe:emit/nfe:enderEmit/nfe:UF");
		 * xPath.setNamespaceContext(new SimpleNamespaceContext(namespaceUris)); //Node
		 * node = (Node) xPath.selectSingleNode(document);
		 */
	}

	/**
	 * 
	 * @param document
	 * @param namespaceUris
	 * @throws Exception
	 */
	public Dom4jUtils2(Document document, Map<String, String> namespaceUris) throws Exception {
		this.document = document;
		this.namespaceUris = namespaceUris;
	}

	/**
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String getNodeText(String path) throws Exception {
		XPath xPath = new Dom4jXPath(path);
		xPath.setNamespaceContext(new SimpleNamespaceContext(namespaceUris));
		Node node = (Node) xPath.selectSingleNode(document);
		if (node != null)
			return node.getText();
		else
			return null;
	}

	/**
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DefaultElement> getNodes(String path) throws Exception {
		XPath xPath = new Dom4jXPath(path);
		xPath.setNamespaceContext(new SimpleNamespaceContext(namespaceUris));
		return (xPath.selectNodes(document));
	}

	/**
	 * 
	 * @param data
	 */
	public Dom4jUtils2(byte[] data) {

	}

	/**
	 * 
	 * @param data
	 */
	public Dom4jUtils2(File data) {

	}

	/**
	 * 
	 * @param file
	 * @param caminho
	 * @return
	 * @throws JaxenException
	 * @throws DocumentException
	 */
	public String obterNodeText(byte[] file, String caminho) throws JaxenException, DocumentException {
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
	public List<DefaultElement> obterNodeSelect(byte[] file, String caminho) throws JaxenException, DocumentException {
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
	public String obterNodeSelect(byte[] file, String caminho, int elemento) throws JaxenException, DocumentException {
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
	public DefaultElement obterDefaultElementSelect(byte[] file, String caminho, int elemento)
			throws JaxenException, DocumentException {
		xPath = new Dom4jXPath(caminho);
		xPath.setNamespaceContext(new SimpleNamespaceContext(namespaceUris));
		return ((List<DefaultElement>) xPath.selectNodes(new SAXReader().read(new ByteArrayInputStream(file))))
				.get(elemento);
	}
}