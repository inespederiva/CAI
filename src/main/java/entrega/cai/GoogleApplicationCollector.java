package entrega.cai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleApplicationCollector extends ApplicationCollector {

	private String getFilterString() {
		return ".see-more";
//		return ".grid-cell-container .grid-cell-row .grid-cell--game";

	}
	public String getDetailedFilter(){
		return ".cluster .details .title";
	}

	public ApplicationPageAnalyser createAnalyserObject(String url) {
		return new GoogleAppPageAnalyser(url);
	}
	
	public String getUrl(Element headline){
		return headline.absUrl("href");
	}
	
	public List<String> getAllPages(String url){
		List<String> pages = new ArrayList<String>();
		pages.add(url);
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements clusterDetailsTitle = doc.select(getFilterString());
			for (Element headline : clusterDetailsTitle) {
				pages.add(getUrl(headline));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pages;
	}
	

}
