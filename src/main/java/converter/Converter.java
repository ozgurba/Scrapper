package converter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import parameters.ScrapperParameter;
import parameters.ScrapperUrl;


public class Converter {
	public static org.jsoup.nodes.Document crawlUrlPage(String url) {

		try {
			System.out.println("Coverter.crawlUrlPage:" + url);
			org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
			// Remove all script and style elements and those of class "hidden".
			doc.select("script, style, .hidden,comment, CDATA, #comment,meta, nbsp, input").remove();
			removeComments(doc);
			return doc;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String htmlToXML(String htmlStr) {
		String unescapeEntities = Parser.unescapeEntities(htmlStr, false);
		org.jsoup.nodes.Document document = Jsoup.parseBodyFragment(unescapeEntities);
		document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
		document.outputSettings().charset("UTF-8");
		String result = document.toString().replaceAll("&nbsp;", " ");
		return result;
	}

	private static void removeComments(org.jsoup.nodes.Document doc) {
		List<org.jsoup.nodes.Node> nodeList=doc.childNodes();
		for(org.jsoup.nodes.Node node:nodeList) {
			removeComments(node);
		}
	}

	private static void removeComments(org.jsoup.nodes.Node node) {
		for (int i = 0; i < node.childNodes().size(); ) {
            org.jsoup.nodes.Node child = node.childNode(i);
            if (child.nodeName().equals("#comment"))
                child.remove();
            else {
                removeComments(child);
                i++;
            }
        }
	}

	public static ScrapperParameter readScrapperParametersFromXmlFile(String paramfileLoc) {
		ScrapperParameter scraperParameter = new ScrapperParameter();
		try {

			File fXmlFile = new File(paramfileLoc);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Reading scrapper parameters :" + doc.getLocalName());

			NodeList urlNodeList = doc.getElementsByTagName("url");

			System.out.println("----------------------------");

			for (int urlPos = 0; urlPos < urlNodeList.getLength(); urlPos++) {
				Node nNode = urlNodeList.item(urlPos);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					ScrapperUrl scrapperUrl = new ScrapperUrl(eElement.getAttribute("urlAddress"));
					scrapperUrl.setId(eElement.getAttribute("id"));
					scrapperUrl.setParentId(eElement.getAttribute("parentId"));
					String xQueryStr = eElement.getElementsByTagName("xQuery").item(0).getTextContent();
					scrapperUrl.setxQueryStr(xQueryStr);

					String linkXQueryStr = eElement.getElementsByTagName("linkXQuery").item(0).getTextContent();
					scrapperUrl.setLinkXQueryStr(linkXQueryStr);
					scraperParameter.add(scrapperUrl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scraperParameter;
	}

}
