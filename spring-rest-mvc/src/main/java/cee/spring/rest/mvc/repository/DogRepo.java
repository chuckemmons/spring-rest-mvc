/**
 *
 */
package cee.spring.rest.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cee.spring.rest.mvc.domain.Dog;

/**
 * @author chuck
 *
 */
@Repository
public interface DogRepo extends JpaRepository<Dog, Integer>{
	List<Dog> findByBreed(String breed);
	
	Dog findByName(String name);
}
