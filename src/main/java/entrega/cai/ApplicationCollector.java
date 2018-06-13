package entrega.cai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class ApplicationCollector {
	private int pageCount = 1;

//	public abstract String getFilterString();

	public abstract String getUrl(Element e);
	public abstract String getDetailedFilter();

	public abstract List<String> getAllPages(String url);

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public abstract ApplicationPageAnalyser createAnalyserObject(String url);

	public List<ApplicationPageAnalyser> collect(String rootUrl) {
		// (pageCount==1)?rootUrl:rootUrl + i
		List<ApplicationPageAnalyser> analysers = new ArrayList<ApplicationPageAnalyser>();
		Document doc;
		System.out
				.println("Se comienza la búsqueda de las páginas comenzando con: "
						+ rootUrl);
		List<String> allPages = getAllPages(rootUrl);
		for (String page : allPages) {
			try {
				doc = Jsoup.connect(page).get();
				Elements clusterDetailsTitle = doc.select(getDetailedFilter());
				for (Element headline : clusterDetailsTitle) {
					analysers.add(createAnalyserObject(getUrl(headline)));
				}
				System.out.println("Se procesó la página " + page);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return analysers;
	}
}
