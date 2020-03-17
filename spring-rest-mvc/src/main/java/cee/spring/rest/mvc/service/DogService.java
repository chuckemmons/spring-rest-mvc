/**
 *
 */
package cee.spring.rest.mvc.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cee.spring.rest.mvc.domain.Dog;
import cee.spring.rest.mvc.dto.DogDto;
import cee.spring.rest.mvc.mapper.DogMapper;
import cee.spring.rest.mvc.repository.DogRepo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chuck
 *
 */
@Slf4j
@Service
@Transactional
public class DogService {

	  private final DogRepo dogRepo;
	  private final DogMapper dogMapper;

	  @Inject
	  public DogService(final DogRepo dogRepo, final DogMapper dogMapper) {
		  this.dogRepo = dogRepo;
		  this.dogMapper = dogMapper;
	  }

	  public List<DogDto> fetchAll() {
		  log.debug("finding all");

		  return dogRepo.findAll().stream()
				  .map(dogMapper::getDto)
				  .collect(Collectors.toList());
	  }

	/**
	 * @param id - the id of the dog. (must parse to an integer).
	 */
	public Optional<DogDto> fetchById(final String id) {
		log.debug("fetching by id '{}'", id);

		  try {
			  return Optional.ofNullable(
						dogMapper.getDto(
							dogRepo.findById(Integer.valueOf(id)).orElse(null)));

		  } catch (NumberFormatException ex) {
			  throw new IllegalArgumentException(String.format("id must be an integer, not '%s'", id), ex);
		  }
	  }

	  public Optional<DogDto> fetchByName(final String name) {
		  log.debug("fetching by name '{}'", name);

		  return Optional.ofNullable(
				  dogMapper.getDto(
						  dogRepo.findByName(name)));
	  }

	  public Optional<DogDto> save(final DogDto dto) {
		  log.debug("saving {}", dto);

		  Dog savedEntity = dogRepo.save(dogMapper.getEntity(dto));

		  log.debug("saved {}", savedEntity);

		  return Optional.ofNullable(
				  	dogMapper.getDto(savedEntity));
	  }

}
