package com.WebApplicationTwitter;

import com.WebApplicationTwitter.models.UserModel;
import com.WebApplicationTwitter.controllers.ControllerTwitter;
import com.WebApplicationTwitter.services.ServiceLayer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
public class TwitterWebApiApplicationTests {

//	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ServiceLayer serviceLayer;

	@Autowired
	private ControllerTwitter controllerTwitter;

	@Test
	void contextLoads() {
	}

//	@Test
//	void getUserTest() throws Exception {
//		UserModel user = new UserModel("user1", "Bio 1", Arrays.asList("follower1", "follower2"));
//
//		when(serviceLayer.getUser("user1")).thenReturn(user);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/twitter/users/{userId}", "user1"))
//				.andDo(print())
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("user1"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$.bio").value("Bio 1"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$.followers[0]").value("follower1"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$.followers[1]").value("follower2"));
//	}

//	@Test
//	void getCommonFollowersTest() throws Exception {
//		List<String> commonFollowers = Arrays.asList("follower1", "follower2", "follower5");
//
//		when(serviceLayer.CommonFollowersfetch("user1", "user2")).thenReturn(commonFollowers);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/twitter/users")
//						.param("user1", "user1")
//						.param("user2", "user2"))
//				.andDo(print())
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("follower1"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("follower2"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$[2]").value("follower5"));
//	}
}
