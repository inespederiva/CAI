package entrega.cai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleAppPageAnalyser extends ApplicationPageAnalyser {

	public GoogleAppPageAnalyser(String url) {
		super(url);

	}

	public MobileApp extractApp() {
		Document doc;
		MobileApp app = null;
		try {
			doc = Jsoup.connect(getUrl()).get();
			String appName = doc.select("h1 span").first().html();
			Elements classifications = doc.select(".i4sPve img");
			List<String> classif = new ArrayList<String>();
			for (Element cl : classifications) {
				classif.add(cl.attr("alt"));
			}
			String desc = doc.select(".PHBdkd .DWPxHb div").html();
			app = new MobileApp(appName, classif, desc, "Google Play");
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return app;
	}

}
