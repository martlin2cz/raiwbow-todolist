package cz.martlin.rainbowtodolist.view.resource;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import cz.martlin.rainbowtodolist.model.model.RainbowTask;
import cz.martlin.rainbowtodolist.model.model.RainbowTaskStatus;

/**
 * Test for {@link RainbowTasksResource}.
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RainbowTasksResourceTest {

	/**
	 * The temporary server port number.
	 */
	@LocalServerPort
	private String port;
	
	/**
	 * The rest client.
	 */
	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * The base uri of the services, on the server, root-relative path.
	 */
	private static URI baseUri = URI.create("/api/v0.1/rainbowtodolist/tasks");
	
	/**
	 * The id of the lipsum task.
	 */
	private static UUID lipsumTaskId;
	
	@Test
	@Order(0)
	public void testList() throws Exception {
		URI serviceUri = baseUri;
		RainbowTasksResponse<List<RainbowTask>> response = restTemplate.exchange(serviceUri, HttpMethod.GET, //
				null, //
				new ParameterizedTypeReference<RainbowTasksResponse<List<RainbowTask>>>() {}) //
				.getBody(); 
		
		System.out.println(response);
		
		List<RainbowTask> tasks = response.getResult();
		assertThat(tasks).singleElement();
		
		RainbowTask tryTheAppTask = tasks.get(0);
		assertThat(tryTheAppTask.getText()).isEqualTo("Try the app");
		assertThat(tryTheAppTask.getId()).isNotNull();
//		tryTheAppTaskID = tryTheAppTask.get(0).getId();
	}
	
	@Test
	@Order(1)
	public void testCreate() throws Exception {
		URI serviceUri = baseUri;
		RainbowTasksResponse<RainbowTask> response = restTemplate.exchange(serviceUri, HttpMethod.PUT, //
				new HttpEntity<>("lorem ipsum"), //
				new ParameterizedTypeReference<RainbowTasksResponse<RainbowTask>>() {}) //
				.getBody(); 
		
		System.out.println(response);
		
		RainbowTask lipsumTask = response.getResult();
		assertThat(lipsumTask.getText()).isEqualTo("lorem ipsum");
		assertThat(lipsumTask.getStatus()).isEqualTo(RainbowTaskStatus.OPEN);
		assertThat(lipsumTask.getId()).isNotNull();
		lipsumTaskId = lipsumTask.getId();
	}
	
	@Test
	@Order(2)
	public void testGet() throws Exception {
		URI serviceUri = URI.create(baseUri.toASCIIString() + "/" + lipsumTaskId);
		RainbowTasksResponse<RainbowTask> response = restTemplate.exchange(serviceUri, HttpMethod.GET, //
				null, //
				new ParameterizedTypeReference<RainbowTasksResponse<RainbowTask>>() {}) //
				.getBody(); 
		
		System.out.println(response);
		
		RainbowTask lipsumTask = response.getResult();
		assertThat(lipsumTask.getId()).isEqualTo(lipsumTaskId);
		assertThat(lipsumTask.getText()).isEqualTo("lorem ipsum");
		assertThat(lipsumTask.getStatus()).isEqualTo(RainbowTaskStatus.OPEN);
	}

	@Test
	@Order(3)
	public void testUpdateText() throws Exception {
		URI serviceUri = URI.create(baseUri.toASCIIString() + "/" + lipsumTaskId + "/" + "text");
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		
		RainbowTasksResponse<RainbowTask> response = restTemplate.exchange(serviceUri, HttpMethod.PATCH, //
				new HttpEntity<>("lipsum"), //
				new ParameterizedTypeReference<RainbowTasksResponse<RainbowTask>>() {}) //
				.getBody(); 
		
		System.out.println(response);
		
		RainbowTask lipsumTask = response.getResult();
		assertThat(lipsumTask.getId()).isEqualTo(lipsumTaskId);
		assertThat(lipsumTask.getText()).isEqualTo("lipsum");
		assertThat(lipsumTask.getStatus()).isEqualTo(RainbowTaskStatus.OPEN);
	}

	@Test
	@Order(4)
	public void testChangeStatus() throws Exception {
		URI serviceUri = URI.create(baseUri.toASCIIString() + "/" + lipsumTaskId + "/" + "status");
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.TEXT_PLAIN);
		requestHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
		
		RainbowTasksResponse<RainbowTask> response = restTemplate.exchange(serviceUri, HttpMethod.PATCH, //
				new HttpEntity<>(RainbowTaskStatus.IN_PROGRESS, requestHeaders), //
				new ParameterizedTypeReference<RainbowTasksResponse<RainbowTask>>() {}) //
				.getBody(); 
		
		System.out.println(response);
		
		RainbowTask lipsumTask = response.getResult();
		assertThat(lipsumTask.getId()).isEqualTo(lipsumTaskId);
		assertThat(lipsumTask.getText()).isEqualTo("lipsum");
		assertThat(lipsumTask.getStatus()).isEqualTo(RainbowTaskStatus.IN_PROGRESS);
	}
	

	@Test
	@Order(5)
	public void testDelete() throws Exception {
		URI serviceUri = URI.create(baseUri.toASCIIString() + "/" + lipsumTaskId);
		RainbowTasksResponse<RainbowTask> response = restTemplate.exchange(serviceUri, HttpMethod.DELETE, //
				null, //
				new ParameterizedTypeReference<RainbowTasksResponse<RainbowTask>>() {}) //
				.getBody(); 
		
		System.out.println(response);
		
		RainbowTask lipsumTask = response.getResult();
		assertThat(lipsumTask.getId()).isEqualTo(lipsumTaskId);
		assertThat(lipsumTask.getText()).isEqualTo("lipsum");
		assertThat(lipsumTask.getStatus()).isEqualTo(RainbowTaskStatus.IN_PROGRESS);
	}
	
}
