package com.taskflow.service2;

import com.taskflow.service2.dto.LoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Service2ApplicationTests {

	@Autowired
	RestTemplate restTemplate;

    @Test
	void contextLoads() {

	}

	@Test
	void authenticationTest(){
		String url = "http://localhost:8080/api/user/authenticate";
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("faterAhmed@gmail.com");
		loginDTO.setPassword("a");
		HttpEntity<LoginDTO> httpEntity = new HttpEntity<>(loginDTO);
		assertThat(this.restTemplate.exchange(
				url,
				HttpMethod.POST,
				httpEntity,
				String.class
		)).isEqualTo("Invalid credentials");
	}
}
