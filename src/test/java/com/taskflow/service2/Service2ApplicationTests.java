package com.taskflow.service2;

import com.taskflow.service2.dto.LoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class Service2ApplicationTests {

	@Autowired
	RestTemplate restTemplate;
	private MockMvc mockMvc;


	@Test
	void contextLoads() {

	}

	@Test
	void authenticationTest() throws Exception {
		// Mock JSON payload for login
		String loginPayload = """
        {
            "email": "faterAhmed@gmail.com",
            "password": "a"
        }
        """;

		// Perform the POST request and verify response
		mockMvc.perform(post("/api/user/authenticate")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginPayload))
				.andExpect(status().isUnauthorized()) // Verify HTTP status
				.andExpect(content().string("Invalid credentials")); // Verify response content
	}
}
