/**
 *
 */
package cee.spring.rest.mvc.repository;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cee.spring.rest.mvc.data.DogData;
import cee.spring.rest.mvc.domain.Dog;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chuck
 *
 */
@Slf4j
@SpringBootTest
// @DataJpaTest(showSql = true) -- cannot use because data is loaded with DataLoader.class instead of .sql files
// and this annotation won't load DataLoader into context.
@RunWith(SpringRunner.class)
public class DogRepoIT {

	@Inject
	DogRepo repo;

	@Test
	public void findAll_whenDataExists_returnsAllEntities() {
		// given all DogData gets loaded by the DataLoader.
		Collection<Dog> expected = DogData.getAllEntities();
		// when
		Collection<Dog> actual = repo.findAll();
		// then
		assertEquals(expected, actual);
		log.info("returned {} dogs", actual.size());
	}

	@Test
	public void findByName_whenDataWithNameExists_returnsData() {
		var expectedData = DogData.GOLDIE;

		Dog actualEntity = repo.findByName(expectedData.getName());

		assertEquals(expectedData.getEntity(), actualEntity);
	}

	@Test
	public void findByName_whenDataWithNameDoesntExist_returnsNull() {
		Dog expectedEntity = null;

		Dog actualEntity = repo.findByName(null);

		assertEquals(expectedEntity, actualEntity);
	}

	@Test
	public void findByName_whenDataWithDoesntExist_returnsNull() {
		Dog expectedEntity = null;

		Dog actualEntity = repo.findByName(null);

		assertEquals(expectedEntity, actualEntity);
	}

}
