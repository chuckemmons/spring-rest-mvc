/**
 *
 */
package cee.spring.rest.mvc.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import cee.spring.rest.mvc.domain.Dog;
import lombok.extern.slf4j.Slf4j;

/**
 * Loads the database on application startup.
 * @author chuck
 *
 */
@Slf4j
@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private DogRepo dogRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("Loading database");
		loadDogs();
		log.info("Database has been successfully populated");
	}

	private void loadDogs() {
		final List<Dog> dogs = DogData.getAllEntities();
		dogRepo.saveAll(dogs);
		logLoaded(dogs);
	}

	private <T> void logLoaded(List<T> entities) {
		log.info("loaded {} {}:", entities.size(), entities.get(0).getClass().getName());
		Stream.of(entities).forEach(entity -> {
			log.info("{}", entity);
		});
	}

}
