package entrega.cai;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

public class PlayStationApplicationCollector extends ApplicationCollector {

	public List<String> getAllPages(String url) {
		List<String> pages = new ArrayList<String>();
		for (int i = 1; i < getPageCount(); i++) {
			pages.add(url + i);
		}
		return pages;
	}

	public ApplicationPageAnalyser createAnalyserObject(String url) {
		return new PSPageAnalyser("https://store.playstation.com" + url);
	}

	public String getDetailedFilter() {
		return ".grid-cell-container .grid-cell-row .grid-cell--game";
	}
	
	public String getUrl(Element headline){
		return headline.select("a").attr("href");
	}

}
