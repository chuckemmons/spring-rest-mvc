/**
 * 
 */
package cee.spring.rest.mvc.dto;

import org.junit.Test;

/**
 * @author chuck
 *
 */
public class DogDtoTest {

	@Test
	public void testEmptyBuilder() {
		DogDto dto = DogDto.builder()
				.build();
	}
}
