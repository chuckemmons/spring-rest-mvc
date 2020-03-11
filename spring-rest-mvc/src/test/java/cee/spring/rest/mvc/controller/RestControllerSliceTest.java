/**
 *
 */
package cee.spring.rest.mvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chuck
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
public abstract class RestControllerSliceTest {

	@Autowired
	protected MockMvc mockMvc;

	private static ObjectMapper objectMapper = new ObjectMapper();


	/**
	 * Performs a POST request to the <code>requestUrl</code> to create
	 * the given content sent as json data.
	 *
	 * @param requestUrl - the url to the service.
	 * @param contentObject - the content to be created.
	 * @return ResultActions - the results of the create request.
	 * @throws Exception
	 */
	protected ResultActions requestCreate(final String requestUrl, final Object contentObject) throws Exception {
		return mockMvc.perform(
					post(URI.create(requestUrl))
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(toJson(contentObject)));
	}

	/**
	 * Performs a GET request to the <code>requestUrl</code> to read
	 * all content.
	 *
	 * @param requestUrl - the url to the service
	 * @return ResultActions - the results of the read request.
	 * @throws Exception
	 */
	protected ResultActions requestReadAll(final String requestUrl ) throws Exception {
		return mockMvc.perform(get(URI.create(requestUrl)));
	}

	/**
	 * Performs a GET request to the <code>requestUrl</code> to read the content with the <code>paramName</code>
	 * equal to the <code>paramValue</code>.
	 *
	 * @param requestUrl - the url to the service
	 * @param paramName - the name of property to search by.
	 * @param paramValues - the value of the property to search for.
	 * @return ResultActions - the results of the read request.
	 * @throws Exception
	 */
	protected ResultActions requestReadBy(final String requestUrl, final String paramName, final String paramValues)
			throws Exception {
		return mockMvc.perform(get(requestUrl).param(paramName, paramValues));
	}

	/**
	 * Verifies the response reported a 'Created' HTTP status code 201,
	 * the Content-Type was 'application/json;charset=UTF-8',
	 * and the <code>expectedContent</code> was written to the body as json.
	 *
	 * @param results - contain the request/response metrics.
	 * @param expectedContent - the expected content in the body of the response as json.
	 * @param expectedLocation
	 * @throws Exception
	 */
	protected void verifyCreatedWithExpectedContent(final ResultActions results, final Object expectedContent,
			final String expectedLocation) throws Exception {
		printResults(results);

		results
			.andExpect(status().isCreated())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(header().string("Location", expectedLocation))
			.andExpect(content().json(toJson(expectedContent), true));
	}

	/**
	 * Verifies the response reported an 'Ok' HTTP status code 200,
	 * the Content-Type was 'application/json;charset=UTF-8',
	 * and the <code>expectedContent</code> was written to the body as json.
	 *
	 * @param results - contain the request/response metrics.
	 * @param expectedContent - the expected content in the body of the response as json.
	 * @throws Exception
	 */
	protected void verifyOkWithExpectedContent(final ResultActions results, final Object expectedContent) throws Exception {
		printResults(results);

		results
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json(toJson(expectedContent), true));
	}

	/**
	 * Verifies the response reported an 'InternalServerError' HTTP status code 500.
	 *
	 * @param results - contain the request/response metrics.
	 * @throws Exception
	 */
	protected void verifyInternalServerError(final ResultActions results) throws Exception {
		printResults(results);

		results.andExpect(status().isInternalServerError());
	}

	/**
	 * Verifies the response reported a 'NotFound' HTTP status code 404.
	 *
	 * @param results - contain the request/response metrics.
	 * @throws Exception
	 */
	protected void verifyNotFound(final ResultActions results) throws Exception {
		printResults(results);

		results.andExpect(status().isNotFound());
	}

	/**
	 * Creates a json formatted representation of the object.
	 *
	 * @param object - the object to transform.
	 * @return String - json formatted representation of the given object.
	 * @throws JsonProcessingException
	 */
	protected String toJson(final Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}

	private void printResults(final ResultActions results) throws Exception {
		//if ( log.isTraceEnabled() || log.isDebugEnabled() ) {
			results.andDo(print());
		//}
	}
}
