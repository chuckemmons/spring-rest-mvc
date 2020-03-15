/**
 *
 */
package cee.spring.rest.mvc.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cee.spring.rest.mvc.domain.Dog;
import cee.spring.rest.mvc.dto.DogDto;
import lombok.Getter;
import lombok.ToString;

/**
 * Data that represents {@link Dog}s.
 * @author chuck
 *
 */
@Getter
@ToString
public enum DogData {
	GOLDIE( new Builder()
			.id(1)
			.name("Goldie")
			.dob(LocalDate.of(2017, 04, 01))
			.gender("female")
			.breed("Yellow English Labrador Retriever"));

	private Integer id;
	private String name;
	private LocalDate dob;
	private String gender;
	private String breed;

	private DogData(final Builder builder) {
		id = builder.id;
		name = builder.name;
		dob = builder.dob;
		gender = builder.gender;
		breed = builder.breed;
	}

	public Dog getEntity() {
		return Dog.builder()
				.id(id)
				.name(name)
				.dob(dob)
				.gender(gender)
				.breed(breed)
				.build();
	}

	public DogDto getDto() {
		return DogDto.builder()
				.id(id.toString())
				.name(name)
				.dob(dob.format(DateTimeFormatter.ISO_LOCAL_DATE))
				.gender(gender)
				.breed(breed)
				.build();
	}

	public static List<Dog> getAllEntities() {
		return Stream.of(values())
				.map(DogData::getEntity)
				.collect(Collectors.toList());
	}

	public static List<DogDto> getAllDtos() {
		return Stream.of(values())
				.map(DogData::getDto)
				.collect(Collectors.toList());
	}

	private static class Builder {
		Integer id;
		String name;
		LocalDate dob;
		String gender;
		String breed;

		Builder id(final Integer id) {
			this.id = id;
			return this;
		}

		Builder name(final String name) {
			this.name = name;
			return this;
		}

		Builder dob(final LocalDate dob) {
			this.dob = dob;
			return this;
		}

		Builder gender(final String gender) {
			this.gender = gender;
			return this;
		}

		Builder breed(final String breed) {
			this.breed = breed;
			return this;
		}
	}
}
