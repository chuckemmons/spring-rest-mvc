/**
 *
 */
package cee.spring.rest.mvc.controller;

import static cee.spring.rest.mvc.controller.DogRestController.GET_BY_ID_URL;
import static cee.spring.rest.mvc.controller.DogRestController.URL;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultActions;

import cee.spring.rest.mvc.dto.DogDto;
import cee.spring.rest.mvc.repository.DogData;
import cee.spring.rest.mvc.service.DogService;

/**
 * Slice tests for the {@link DogRestController}.
 *
 * @author chuck
 */
@WebMvcTest(DogRestController.class)
public class DogRestControllerSliceTest extends RestControllerSliceTest {

	@MockBean
	DogService dogServiceMock;

	private static DogDto dtoBeforeSave;

	private static DogDto dtoAfterSave;

	@BeforeClass
	public static void setupTests() {
		dtoBeforeSave = dobermanWithoutIdDtoBuilder().build();
		dtoAfterSave = dobermanWithoutIdDtoBuilder().id("1").build();
	}

	@Test
	public void testCreate_returnsCreated() throws Exception {
		// mock service results
		when(dogServiceMock.save(dtoBeforeSave)).thenReturn(dtoAfterSave);
		// request create dog
		ResultActions results = requestCreate(URL, dtoBeforeSave);
		// verify results
		verifyCreatedWithExpectedContent(results, dtoAfterSave, getByIdUrl(dtoAfterSave.getId()));
	}

	@Test
	public void testReadByName_returnsOk() throws Exception {
		DogData testData = DogData.GOLDIE;
		Optional<DogDto> expectedResults = Optional.of(testData.getDto());

		when(dogServiceMock.fetchByName(testData.getName())).thenReturn(expectedResults);

		ResultActions results = requestReadBy(URL, "name", testData.getName());

		verifyOkWithExpectedContent(results, expectedResults.get());
	}

	@Test
	public void testReadAll_returnsOk() throws Exception {
		//given
		List<DogDto> expectedData = DogData.getAllDtos();

		when(dogServiceMock.fetchAll()).thenReturn(expectedData);

		ResultActions results = requestReadAll(URL);
				//mockMvc.perform(get(DogRestController.URL));

		verifyOkWithExpectedContent(results, expectedData);
	}

	@Test
	public void requestReadAll_whenNoDataFound_returnsNotFound() throws Exception {
		//given
		final List<DogDto> expectedData = new ArrayList<>();

		when(dogServiceMock.fetchAll()).thenReturn(expectedData);

		final ResultActions results = requestReadAll(URL);

		verifyNotFound(results);
	}

	@Test
	public void requestReadAll_whenNullPointerException_returnsInternalServerError() throws Exception {

		when(dogServiceMock.fetchAll()).thenThrow(new NullPointerException("ouch!"));

		final ResultActions results = requestReadAll(URL);

		verifyInternalServerError(results);
	}

	@Test
	public void requestReadAll_whenRuntimeException_returnsInternalServerError() throws Exception {

		when(dogServiceMock.fetchAll()).thenThrow(new RuntimeException("ouch!"));

		final ResultActions results = requestReadAll(URL);

		verifyInternalServerError(results);
	}

	@Test
	public void requestReadAll_whenBadUrl_returnsNotFound() throws Exception {

		final ResultActions results = requestReadAll("sdgsgsgsfg");

		verifyNotFound(results);
	}

	private String getByIdUrl(final String idValue) {
		return GET_BY_ID_URL + idValue;
	}

	private static DogDto.DogDtoBuilder dobermanWithoutIdDtoBuilder() {
		return DogDto.builder()
				.breed("Doberman")
				.dob("2020-01-01")
				.name("Alistair")
				.sex("Male");
	}
}
