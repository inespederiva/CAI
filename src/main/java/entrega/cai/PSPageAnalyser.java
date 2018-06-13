package entrega.cai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PSPageAnalyser extends ApplicationPageAnalyser {

	public PSPageAnalyser(String url) {
		super(url);

	}

	public MobileApp extractApp() {
		Document doc;
		MobileApp app = null;
		try {
			doc = Jsoup.connect(getUrl()).get();
			String appName = doc.select("h2").first().html();
			Elements classifications = doc
					.select(".content-rating__descriptors ul li");
			List<String> classList = new ArrayList<String>();
			for (Element li : classifications) {
				classList.add(li.html());
			}
			String desc = doc.select(".pdp__description p").html();
			app = new MobileApp(appName, classList, desc, "Play Station");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return app;
	}
}
