/**
 *
 */
package cee.spring.rest.mvc.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chuck
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dog")
public class Dog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "dob", nullable = false)
	private LocalDate dob;

	@Column(name = "sex", length = 6, nullable = false)
	private String sex;

	@Column(name = "breed", length = 50, nullable = false)
	private String breed;
}
