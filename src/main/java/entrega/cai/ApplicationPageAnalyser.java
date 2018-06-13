package entrega.cai;

public abstract class ApplicationPageAnalyser {
	private String url;
	
	public ApplicationPageAnalyser(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public abstract MobileApp extractApp();
}
