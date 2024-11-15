package com.fashion.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class FashionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetFashionItems() throws Exception {
    	mockMvc.perform(get("/api/v1/fashion/filter?page=1&sortby=price&sortdirection=asc"))
               .andExpect(status().isOk())
               ;
    }
}