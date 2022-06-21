package br.com.utilities.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author gustavo
 *
 */
public abstract class XmlUtils2 {

	private String caminho;
	private DocumentBuilder dBuilder;
	private File xmlFile;

	/**
	 * 
	 * @param caminho
	 */
	public XmlUtils2(String caminho) {
		this.caminho = caminho;
		xmlFile = new File(caminho);
	}

	/**
	 * 
	 * @param caminho
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void lerXML(String caminho) throws ParserConfigurationException, SAXException, IOException {
		try {
			Document doc = dBuilder.parse(caminho);

			NodeList nList = doc.getElementsByTagName("coordinates");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nElement :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String arrayCoordenadas = eElement.getTextContent().trim();
					System.out.println("Coordenadas : " + arrayCoordenadas);
					String[] coordenadas = arrayCoordenadas.split(",");
					System.out.println(coordenadas);
					for (String coordenada : coordenadas) {
						System.out.println(coordenada);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao ler xml");
			e.printStackTrace();
		}
	}
}
