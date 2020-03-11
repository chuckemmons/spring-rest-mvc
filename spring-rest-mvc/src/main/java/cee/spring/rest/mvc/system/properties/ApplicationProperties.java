/**
 *
 */
package cee.spring.rest.mvc.system.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chuck
 *
 */
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

}
