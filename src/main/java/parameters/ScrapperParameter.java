package parameters;

import java.util.ArrayList;
import java.util.List;

public class ScrapperParameter {
	private List<ScrapperUrl> scrapperUrlList;

	public ScrapperParameter() {
		scrapperUrlList = new ArrayList<ScrapperUrl>();
	}

	public List<ScrapperUrl> getScrapperUrlList() {
		return scrapperUrlList;
	}

	public void setScrapperUrlList(List<ScrapperUrl> scrapperUrlList) {
		this.scrapperUrlList = scrapperUrlList;
	}

	public void add(ScrapperUrl scrapperUrl) {
		scrapperUrlList.add(scrapperUrl);

	}

}
