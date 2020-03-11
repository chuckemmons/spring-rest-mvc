/**
 *
 */
package cee.spring.rest.mvc.repository;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cee.spring.rest.mvc.domain.Dog;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chuck
 *
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DogRepoIT {

	@Autowired
	DogRepo repo;

	@Test
	public void testFindAll_returnsAllEntities() {
		// given
		final Collection<Dog> expected = DogData.getAllEntities();
		// when
		final Collection<Dog> actual = repo.findAll();
		// then
		assertEquals(expected, actual);
		log.info("returned {} dogs", actual.size());
	}

}
