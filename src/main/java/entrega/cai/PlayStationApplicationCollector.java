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
	/*
	 * A partir de la url pasada como parámetro hace pageCount iteraciones con
	 * el fin de recolectar cada una de las páginas de las aplicaciones sobre
	 * las que tiene que ir a buscar el detalle para guardar en la base de
	 * datos.
	 * 
	 * @see entrega.cai.ApplicationCollector#collect(java.lang.String)
	 */
	// public List<ApplicationPageAnalyser> collect(String rootUrl) {
	//
	// List<ApplicationPageAnalyser> analysers = new
	// ArrayList<ApplicationPageAnalyser>();
	// Document doc;
	// System.out
	// .println("Se comienza la búsqueda de las páginas comenzando con: "
	// + rootUrl);
	// for (int i = 1; i < getPageCount(); i++) {
	// try {
	// doc = Jsoup.connect(rootUrl + i).get();
	// Elements clusterDetailsTitle = doc
	// .select(".grid-cell-container .grid-cell-row .grid-cell--game");
	// for (Element headline : clusterDetailsTitle) {
	// analysers.add(new PSPageAnalyser(
	// "https://store.playstation.com"
	// + headline.select("a").attr("href")));
	// }
	// System.out.println(String.format(
	// "Se procesó la página %s de PlayStation Store", i));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// return analysers;
	// }

}
