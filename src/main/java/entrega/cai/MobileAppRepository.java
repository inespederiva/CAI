package entrega.cai;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

// Más info en https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html

public interface MobileAppRepository extends MongoRepository<MobileApp, String> {

	MobileApp findByName(String name);

	List<MobileApp> findByClassification(String classification);
	
}