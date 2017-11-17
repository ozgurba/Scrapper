import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.basex.core.BaseXException;
import org.basex.examples.local.RunQueries;
import org.jsoup.nodes.Document;

import converter.Converter;
import parameters.ScrapperParameter;
import parameters.ScrapperUrl;

public class ScrapperWithoutUI {
	final static String xmlProcessingParametersFileLocationStr = "Scrapper.xml";
	final static String xmlPath = "data\\xmldocs\\";
	static boolean isCrawled = true;

	public static void main(String args[]) throws BaseXException {
		System.out.println("Starting processing of " + xmlProcessingParametersFileLocationStr);
		ScrapperParameter scrapperParameter = Converter
				.readScrapperParametersFromXmlFile(xmlProcessingParametersFileLocationStr);
		List<ScrapperUrl> scrapperUrlList = scrapperParameter.getScrapperUrlList();
		if (!isCrawled) {
			for (ScrapperUrl scrapperUrl : scrapperUrlList) {
				if (scrapperUrl.getParentId() == null || scrapperUrl.getParentId().equals("")) {
					final Document crawledDoc = Converter.crawlUrlPage(scrapperUrl.getUrlStr());
					String xmlDocumentStr = Converter.htmlToXML(crawledDoc.html());
					try {
						FileUtils.writeStringToFile(new File(xmlPath + "" + scrapperUrl.getId() + ".xml"),
								xmlDocumentStr, "UTF-8");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					// child page processing is done from parent url
				}
			}
		}

		for (ScrapperUrl scrapperUrl : scrapperUrlList) {
			if (scrapperUrl.getParentId() == null || scrapperUrl.getParentId().equals("")) {
				final String queryStr = scrapperUrl.getxQueryStr();
				System.out.println("Running query:" + scrapperUrl.getId());
				String xqueryResult = RunQueries.query(queryStr);
				try {
					FileUtils.writeStringToFile(new File(xmlPath + "" + scrapperUrl.getId() + "result.xml"),
							xqueryResult, "UTF-8");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else{
				//child query execution
			}
		}

	}

}
