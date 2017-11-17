package parameters;

public class ScrapperUrl {
	private String urlStr;
	private ScrapperUrl innerUrl;
	private String xQueryStr;
	private String resultStr;
	private String linkXQueryStr;
	private String id;
	private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ScrapperUrl(String purlStr) {
		setUrlStr(purlStr);
	}

	public ScrapperUrl getInnerUrl() {
		return innerUrl;
	}

	public void setInnerUrl(ScrapperUrl innerUrl) {
		this.innerUrl = innerUrl;
	}

	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}

	public String getxQueryStr() {
		return xQueryStr;
	}

	public void setxQueryStr(String xQueryStr) {
		this.xQueryStr = xQueryStr;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

	public String getLinkXQueryStr() {
		return linkXQueryStr;
	}

	public void setLinkXQueryStr(String linkXQueryStr) {
		this.linkXQueryStr = linkXQueryStr;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
