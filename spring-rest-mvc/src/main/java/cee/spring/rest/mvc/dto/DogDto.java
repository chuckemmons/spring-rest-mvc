/**
 *
 */
package cee.spring.rest.mvc.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import cee.spring.rest.mvc.system.utility.RegEx;
import lombok.Builder;
import lombok.Value;

/**
 * @author chuck
 *
 */
@Value
@Builder
public class DogDto {
	private String id;
	
	@NotBlank @Length(max = 50)
	private String name;
	
	@NotNull @Pattern(message = "must be formatted 'yyyy-MM-dd'", regexp = RegEx.DATE)
	private String dob;
	
	@NotBlank @Length(max = 6)
	private String sex;
	
	@NotBlank @Length(max = 50)
	private String breed;
}
