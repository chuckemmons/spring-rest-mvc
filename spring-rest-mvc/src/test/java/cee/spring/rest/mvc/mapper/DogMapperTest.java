/**
 *
 */
package cee.spring.rest.mvc.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import cee.spring.rest.mvc.domain.Dog;
import cee.spring.rest.mvc.dto.DogDto;
import cee.spring.rest.mvc.repository.DogData;

/**
 * Unit tests for {@link DogMapper}.
 * @author chuck
 */
public class DogMapperTest {

	private DogMapper mapper;

	@Before
	public void setup() {
		mapper = new DogMapper();
	}

	@Test
	public void getDto_whenValidEntity_returnsValidDto() {
		DogData data = DogData.GOLDIE;

		DogDto dto = data.getDto();

		Dog entity = mapper.getEntity(dto);

		assertThat(entity).isEqualTo(data.getEntity());
	}

	@Test
	public void getDto_whenNullEntity_returnsNullDto() {

		assertThat(mapper.getEntity(null)).isEqualTo(null);
	}

	@Test
	public void getEntity_whenValidDto_returnsValidDto() {
		DogData data = DogData.GOLDIE;

		Dog entity = data.getEntity();

		DogDto dto = mapper.getDto(entity);

		assertThat(dto).isEqualTo(data.getDto());
	}

	@Test
	public void getEntity_whenNullDto_returnsNullEntity() {

		assertThat(mapper.getDto(null)).isEqualTo(null);
	}
}
