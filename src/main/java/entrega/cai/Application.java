package entrega.cai;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	// private PersonRepository repository;
	private MobileAppRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		long start = System.currentTimeMillis();
		for (String s : args) {
			if (s.equals("collect")) {
				collect(repository);
			}
			if (s.equals("analyse")) {
				analyse(repository);
			}
		}
		long stop = System.currentTimeMillis();
		System.out.println(new Date(start));
		System.out.println(new Date(stop));
		System.out.println("Duración en milisegundos: " + (stop - start));
		// En casa duró desde Tue Jun 12 18:59:55 ART 2018
		// hasta Tue Jun 12 21:00:46 ART 2018
		// Duración en milisegundos: 7251424

		// git init
		// git config --global user.name "Inés Pederiva"
		// git config --global user.email "ines.pederiva@gmail.com"
		// git add pom.xml
		// git remote add origin https://github.com/inespederiva/CAI.git
		// git remote -v
		// git add src/.
		// git commit -m "Primer commit completo"
		// git push origin master


	}

	public static void collect(MobileAppRepository repository) {
		repository.deleteAll();

		process(repository, new GoogleApplicationCollector(), 0,
				"https://play.google.com/store/apps");
		process(repository, new PlayStationApplicationCollector(), 54,
				"https://store.playstation.com/es-ar/grid/STORE-MSF77008-PS3ALLPS3GAMES/");

		process(repository, new PlayStationApplicationCollector(), 89,
				"https://store.playstation.com/es-ar/grid/STORE-MSF77008-PS4ALLGAMESCATEG/");

	}

	private static void process(MobileAppRepository repository,
			ApplicationCollector collector, int pageCount, String url) {
		if (pageCount > 0) {
			collector.setPageCount(pageCount);
		}
		List<ApplicationPageAnalyser> pageAnalysers = collector.collect(url);
		System.out.println(String.format(
				"Se recolectaron %s aplicaciones que serán procesadas",
				pageAnalysers.size()));
		for (ApplicationPageAnalyser analyser : pageAnalysers) {
			MobileApp app = analyser.extractApp();
			if (app != null) {
				System.out
						.println("Se guardará la aplicación " + app.getName());
				repository.save(app);
			}
		}
	}

	public static void analyse(MobileAppRepository repository) {
		System.out
				.println(String
						.format("Hay un total de %s aplicaciones persistidas en la base de datos",
								repository.findAll().size()));

		List<MobileApp> mobileAppCol = repository
				.findByClassification("Everyone");
		System.out.println(String.format(
				"Hay un total de %s clasificados como para todos",
				mobileAppCol.size()));
//		for (MobileApp app : mobileAppCol) {
//			System.out.println(app);
//		}
		List<MobileApp> viol = repository.findByClassification("Violencia");
		System.out.println(String.format(
				"Hay un total de %s clasificados como violencia", viol.size()));
//		for (MobileApp app : viol) {
//			System.out.println(app);
//		}
		List<MobileApp> alco = repository
				.findByClassification("Referencia de alcohol");
		// List<MobileApp> alco = repository
		// .findByClassification("Uso de alcohol");
		System.out.println(String.format(
				"Hay un total de %s clasificados como alcohol", alco.size()));
//		for (MobileApp app : alco) {
//			System.out.println(app);
//		}
	}

}