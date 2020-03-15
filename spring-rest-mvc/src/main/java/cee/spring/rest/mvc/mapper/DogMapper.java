/**
 *
 */
package cee.spring.rest.mvc.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import cee.spring.rest.mvc.domain.Dog;
import cee.spring.rest.mvc.dto.DogDto;
import lombok.extern.slf4j.Slf4j;

/**
 * Maps {@link Dog} entities to/from {@link DogDto} dtos.
 *
 * @author chuck
 */
@Slf4j
@Component
public class DogMapper {

	/**
	 * Converts a {@link Dog} entity to a {@link DogDto} dto.
	 * @param entity the Dog to convert.
	 * @return DogDto representing the given Dog. null if given Dog is null.
	 */
	public DogDto getDto(final Dog entity) {
		log.debug("mapping {}", entity);

		DogDto dto = null;

		if (null != entity) {
			String id = entity.getId() == null ? null
					: entity.getId().toString();

			String dob = entity.getDob() == null ? null
					: entity.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE);

			dto = DogDto.builder()
						.id(id)
						.name(entity.getName())
						.dob(dob)
						.gender(entity.getGender())
						.breed(entity.getBreed())
						.build();
		}

		return dto;
	}

	/**
	 * Converts a {@link DogDto} dto to a {@link Dog} entity.
	 * @param dto the DogDto to convert.
	 * @return Dog representing the given DogDto. null if given DogDto is null.
	 */
	public Dog getEntity(final DogDto dto) {
		log.debug("mapping {}", dto);

		Dog entity = null;

		if (null != dto) {
			Integer id = dto.getId() == null ? null
					: Integer.valueOf(dto.getId());

			LocalDate dob = dto.getDob() == null ? null
					: LocalDate.parse(dto.getDob(), DateTimeFormatter.ISO_LOCAL_DATE);

			entity = Dog.builder()
						.id(id)
						.name(dto.getName())
						.dob(dob)
						.gender(dto.getGender())
						.breed(dto.getBreed())
						.build();
		}

		return entity;
	}
}
