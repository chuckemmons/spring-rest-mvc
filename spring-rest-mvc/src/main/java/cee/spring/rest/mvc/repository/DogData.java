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
			.sex("female")
			.breed("Yellow English Labrador Retriever"));

	private Integer id;
	private String name;
	private LocalDate dob;
	private String sex;
	private String breed;
	
	private DogData(Builder builder) {
		id = builder.id;
		name = builder.name;
		dob = builder.dob;
		sex = builder.sex;
		breed = builder.breed;

	}

	public Dog getEntity() {
		return Dog.builder()
				.id(id)
				.name(name)
				.dob(dob)
				.sex(sex)
				.breed(breed)
				.build();
	}
	
	public DogDto getDto() {
		return DogDto.builder()
				.id(id.toString())
				.name(name)
				.dob(dob.format(DateTimeFormatter.ISO_LOCAL_DATE))
				.sex(sex)
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
 
	public static class Builder {
		Integer id;
		String name;
		LocalDate dob;
		String sex;
		String breed;

		Builder id(Integer id) {
			this.id = id;
			return this;
		}

		Builder name(String name) {
			this.name = name;
			return this;
		}

		Builder dob(LocalDate dob) {
			this.dob = dob;
			return this;
		}

		Builder sex(String sex) {
			this.sex = sex;
			return this;
		}

		Builder breed(String breed) {
			this.breed = breed;
			return this;
		}
	}
}
