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

	public static final String ID_PARAM = "id";

	public static final String GET_BY_ID_PARAM = "?" + ID_PARAM + "=";

	public static final String GET_BY_ID_URL = URL + GET_BY_ID_PARAM;

	public static final String NAME_PARAM = "name";

	public static final String GET_BY_NAME_PARAM = "?" + NAME_PARAM + "=";

	public static final String GET_BY_NAME_URL = URL + GET_BY_NAME_PARAM;

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

		return ResponseEntity.created(URI.create(getByIdUrl(dto.getId()))).body(dto);
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

	@GetMapping(params = {ID_PARAM})
	public ResponseEntity<DogDto> getById(@RequestParam(ID_PARAM) final String id) throws NotFoundException {
		log.debug("received getById request for {} '{}'", ID_PARAM, id);

		DogDto dog = dogService.fetchById(id)
								.orElseThrow( () -> new NotFoundException("dog", ID_PARAM, id) );

		log.debug("ok getById, returning: {}", dog);

		return ResponseEntity.ok().body(dog);
	}

	@GetMapping(params = {NAME_PARAM})
	public ResponseEntity<DogDto> getByName(@RequestParam(NAME_PARAM) final String name) throws NotFoundException {
		log.debug("received getByName request for {} '{}'", NAME_PARAM, name);

		DogDto dog = dogService.fetchByName(name)
								.orElseThrow( () -> new NotFoundException("dog", NAME_PARAM, name) );

		log.debug("ok readByName, returning: {}", dog);

		return ResponseEntity.ok().body(dog);
	}

	public static String getByIdUrl(final String id) {
		return GET_BY_ID_URL + id;
	}

	public static String getByNameUrl(final String name) {
		return GET_BY_NAME_URL + name;
	}

}
