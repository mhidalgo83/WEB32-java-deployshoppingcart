package com.lambdaschool.shoppingcart.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.shoppingcart.config.DBConfig;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    List<User> uList = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        User u1 = new User();
        u1.setUserid(10);
        u1.setUsername("testing");
        u1.setComments("");
        u1.setPassword("password");

        User u2 = new User();
        u1.setUserid(20);
        u1.setUsername("testing2");
        u1.setComments("");
        u1.setPassword("password");

        User u3 = new User();
        u3.setUserid(30);
        u3.setUsername("testing3");
        u3.setComments("");
        u3.setPassword("password");


        uList.add(u1);
        uList.add(u2);
        uList.add(u3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getLoggedInUserInfo() {
    }

    @Test
    public void listAllUsers() throws Exception {
        String apiUrl = "/users/users";
        Mockito.when(userService.findAll()).thenReturn(uList);
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb).andReturn();
        String testResult = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(uList);
        assertEquals(expectedResult, testResult);
    }

    @Test
    public void getUserById() throws Exception {
        String apiUrl = "/users/user/30";
        Mockito.when(userService.findUserById(30)).thenReturn(uList.get(2));
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(rb).andReturn();
        String testResult = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(uList.get(2));
        assertEquals(expectedResult, testResult);
    }

    @Test
    public void addUser() {
    }

    @Test
    public void deleteUserById() {
    }
}