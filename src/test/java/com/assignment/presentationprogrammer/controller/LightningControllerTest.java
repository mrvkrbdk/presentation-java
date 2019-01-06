package com.assignment.presentationprogrammer.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mervek
 */
public class LightningControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/api/lightning"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void findLigtning() throws Exception {
    }

    @Test
    public void create() throws Exception {
    }

}