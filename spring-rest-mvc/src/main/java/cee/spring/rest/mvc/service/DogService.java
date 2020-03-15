/**
 *
 */
package cee.spring.rest.mvc.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

	  @Autowired
	  public DogService(final DogRepo dogRepo, final DogMapper dogMapper) {
		  this.dogRepo = dogRepo; this.dogMapper = dogMapper;
	  }

	  public List<DogDto> fetchAll() {
		  return dogRepo.findAll().stream()
				  .map(dogMapper::getDto)
				  .collect(Collectors.toList());
	  }

	  public Optional<DogDto> fetchById(final String id) {

		  return Optional.ofNullable(
				  dogMapper.getDto(
						  dogRepo.findById(Integer.valueOf(id)).orElse(null))
				  );
	  }

	  public Optional<DogDto> fetchByName(final String name) {
		  return Optional.ofNullable(dogMapper.getDto(dogRepo.findByName(name)));
	  }

	  public DogDto save(final DogDto dto) {
		  log.info("saving {}", dto);

		  Dog entity = dogRepo.save(dogMapper.getEntity(dto));

		  log.info("saved {}", entity);

		  return dogMapper.getDto(entity);
	  }

}
