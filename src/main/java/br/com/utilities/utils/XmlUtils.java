package br.com.utilities.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author gustavo
 *
 */
public class XmlUtils {

	private Document doc;
	private DocumentBuilder dBuilder;
	private DocumentBuilderFactory dbFactory;

	private void load(InputStream is) {
		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();

			doc = dBuilder.parse(is);
		} catch (Exception e) {
			doc = null;
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param filename � o nome do arquivo
	 */
	public void loadXmlFile(String fileName) {
		try {
			load(new FileInputStream(new File(fileName)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param data � o conteudo do arquivo
	 */
	public void loadXmlData(String data) {
		try {
			load(new ByteArrayInputStream(data.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param data � o conteudo do arquivo
	 */
	public void loadXmlData(byte[] data) {
		try {
			load(new ByteArrayInputStream(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param tagName
	 * @return
	 */
	public Node searchFirstNodeByName(String tagName) {
		return searchFirstNodeByName(tagName, doc.getDocumentElement());
	}

	/**
	 * 
	 * @param tagName
	 * @return
	 */
	public List<Node> searchNodesByName(String tagName) {
		return searchNodesByName(tagName, doc.getDocumentElement());
	}

	/**
	 * 
	 * @param nodeType
	 * @return
	 */
	public Node searchFirstNodeByType(Short nodeType) {
		return searchFirstNodeByType(nodeType, doc.getDocumentElement());
	}

	/**
	 * 
	 * @param nodeType
	 * @return
	 */
	public List<Node> searchNodesByType(Short nodeType) {
		return searchNodesByType(nodeType, doc.getDocumentElement());
	}

	/**
	 * @param tagName
	 * @param node
	 * @return
	 */
	public Node searchFirstNodeByName(String tagName, Node node) {
		return searchFirstNodeByName(tagName, node.getChildNodes());
	}

	/**
	 * 
	 * @param tagName
	 * @param node
	 * @return
	 */
	public List<Node> searchNodesByName(String tagName, Node node) {
		return searchNodesByName(tagName, node.getChildNodes());
	}

	/**
	 * 
	 * @param nodeType
	 * @param node
	 * @return
	 */
	public Node searchFirstNodeByType(Short nodeType, Node node) {
		return searchFirstNodeByType(nodeType, node.getChildNodes());
	}

	/**
	 * 
	 * @param nodeType
	 * @param node
	 * @return
	 */
	public List<Node> searchNodesByType(Short nodeType, Node node) {
		return searchNodesByType(nodeType, node.getChildNodes());
	}

	/**
	 * 
	 * @param tagName
	 * @param nodes
	 * @return
	 */
	public Node searchFirstNodeByName(String tagName, NodeList nodes) {
		Node result = null;
		for (int index = 0; index < nodes.getLength() && result == null; index++) {
			Node node = nodes.item(index);
			if (node.getNodeName().equals(tagName)) {
				result = node;
			} else {
				result = searchFirstNodeByName(tagName, node);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param tagName
	 * @param nodes
	 * @return
	 */
	public List<Node> searchNodesByName(String tagName, NodeList nodes) {
		List<Node> result = new ArrayList<Node>();
		for (int index = 0; index < nodes.getLength(); index++) {
			Node node = nodes.item(index);
			if (node.getNodeName().equals(tagName)) {
				result.add(node);
			} else {
				result.addAll(searchNodesByName(tagName, node));
			}
		}
		return result;
	}

	/**
	 * 
	 * @param nodeType
	 * @param nodes
	 * @return
	 */
	public Node searchFirstNodeByType(Short nodeType, NodeList nodes) {
		Node result = null;
		for (int index = 0; index < nodes.getLength() && result == null; index++) {
			Node node = nodes.item(index);
			if (node.getNodeType() == nodeType) {
				result = node;
			} else {
				result = searchFirstNodeByType(nodeType, node);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param nodeType
	 * @param nodes
	 * @return
	 */
	public List<Node> searchNodesByType(Short nodeType, NodeList nodes) {
		List<Node> result = new ArrayList<Node>();
		for (int index = 0; index < nodes.getLength(); index++) {
			Node node = nodes.item(index);
			if (node.getNodeType() == nodeType) {
				result.add(node);
			} else {
				result.addAll(searchNodesByType(nodeType, node));
			}
		}
		return result;
	}

	public String getValue(Node node, String nn) {
		String result = null;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) node;
			result = e.getNodeValue();
		}
		return result;
	}

	/**
	 * 
	 * @param node
	 * @param nomeAtributo
	 * @return
	 */
	public String getAttribute(Node node, String nomeAtributo) {
		String result = null;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) node;
			result = e.getAttribute(nomeAtributo);
		}
		return result;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public String getContent(Node node) {
		String result = null;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) node;
			result = e.getTextContent();
		}
		return result;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public boolean hasSibling(Node node) {
		boolean result = false;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) node;
			result = e.getNextSibling() != null || e.getPreviousSibling() != null;
		}
		return result;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public boolean hasNextSibling(Node node) {
		boolean result = false;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) node;
			result = e.getNextSibling() != null;
		}
		return result;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public boolean hasPreviousSibling(Node node) {
		boolean result = false;
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) node;
			result = e.getPreviousSibling() != null;
		}
		return result;
	}
}
