/**
 *
 */
package cee.spring.rest.mvc.controller;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cee.spring.rest.mvc.dto.DogDto;
import cee.spring.rest.mvc.service.DogService;
import cee.spring.rest.mvc.system.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chuck
 *
 */
@Slf4j
@RestController
@RequestMapping(DogRestController.URL)
public class DogRestController {


	public static final String URL = "/api/dogs";

	public static final String GET_BY_ID_PARAM = "?id=";

	public static final String GET_BY_ID_URL = URL + GET_BY_ID_PARAM;

	public final DogService dogService;

	@Autowired
	public DogRestController(final DogService dogService) {
		this.dogService = dogService;
	}

	@PostMapping
	public ResponseEntity<DogDto> create(@Valid @RequestBody final DogDto dog) {
		log.debug("received create request for: {}", dog);

		DogDto dto = dogService.save(dog);

		log.debug("created {}", dto);

		return ResponseEntity.created(URI.create(GET_BY_ID_URL + dto.getId())).body(dto);
	}

	@GetMapping
	public ResponseEntity<Collection<DogDto>> readAll() throws NotFoundException {
		log.debug("received readAll request");

		final Collection<DogDto> dogs = dogService.fetchAll();

		if (dogs.isEmpty()) {
			throw new NotFoundException("dog");
		}

		log.debug("ok read all {}", dogs.size() );

		return ResponseEntity.ok().body(dogs);
	}

	@GetMapping(params = {"name"})
	public ResponseEntity<DogDto> readByName(@RequestParam("name") final String name) throws NotFoundException {
		log.debug("received readByName request for name '{}'", name);

		DogDto dog = dogService.fetchByName(name)
								.orElseThrow( () -> new NotFoundException("dog", "name", name) );

		log.debug("ok readByName, returning: {}", dog);

		return ResponseEntity.ok().body(dog);
	}

}
