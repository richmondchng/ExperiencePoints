package com.platform.cloud.xpmanagement;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.platform.cloud.xpmanagement.controller.ExperienceController;
import com.platform.cloud.xpmanagement.service.ExperienceManagementService;
import com.platform.cloud.xpmanagement.service.dto.ExperienceDTO;

/**
 * Test web layer.
 *
 * @author richmondchng
 *
 */
@WebMvcTest(ExperienceController.class)
class WebRestpApiExperienceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExperienceManagementService experienceManagementService;

    @AfterEach
    public void tearDown() throws Exception {
        reset(experienceManagementService);
    }

    /**
     * Test GET request to get player balance.
     *
     * @throws Exception
     */
    @Test
    public void testGetRequest_hasPlayerIdParameter() throws Exception {
        when(experienceManagementService.getPlayerBalance(anyInt())).thenReturn(new ExperienceDTO(100,
                LocalDateTime.of(2020, 9, 12, 18, 7, 23), LocalDateTime.of(2020, 9, 12, 20, 7, 23)));

        mockMvc.perform(get("/experience/23"))
                // 200 status
                .andExpect(status().isOk())
                // has balance, and matches expected value
                .andExpect(jsonPath("$.balance", is(100)))
                .andExpect(jsonPath("$.created_at_timestamp", is("2020-09-12T18:07:23")))
                .andExpect(jsonPath("$.updated_at_timestamp", is("2020-09-12T20:07:23")));

        // verify service
        verify(experienceManagementService, times(1)).getPlayerBalance(23);
    }

    /**
     * Test GET request to get player balance.
     *
     * No parameter provided, return 4xx error
     *
     * @throws Exception
     */
    @Test
    public void testGetRequest_invalidPlayerIdParameter() throws Exception {
        mockMvc.perform(get("/experience/aa"))
                // 4xx status
                .andExpect(status().is4xxClientError());

        // verify service
        verifyNoInteractions(experienceManagementService);
    }

    /**
     * Test POST request to add experience points.
     *
     * @throws Exception
     */
    @Test
    public void testPostRequest_hasPlayerIdParameter_hasRequestBody() throws Exception {

        mockMvc.perform(post("/experience/23")
                // json content
                .contentType(MediaType.APPLICATION_JSON).content("{\"points\" : 5}"))
                // 201 status
                .andExpect(status().isCreated());

        // verify service
        verify(experienceManagementService, times(1)).addExperiencePoints(23, 5);
    }

    /**
     * Test POST request to add experience points.
     *
     * No request body provided, return 4xx error.
     *
     * @throws Exception
     */
    @Test
    public void testPostRequest_hasPlayerIdParameter_noRequestBody() throws Exception {

        mockMvc.perform(post("/experience/23"))
                // 4xx status
                .andExpect(status().is4xxClientError());

        // verify service
        verify(experienceManagementService, times(0)).addExperiencePoints(anyInt(), anyInt());
    }

    /**
     * Test POST request to add experience points.
     *
     * Invalid player id provided, return 4xx error.
     *
     * @throws Exception
     */
    @Test
    public void testPostRequest_invalidPlayerIdParameter_hasRequestBody() throws Exception {

        mockMvc.perform(post("/experience/aa")
                // json content
                .contentType(MediaType.APPLICATION_JSON).content("{\"points\" : 5}"))
                // exx status
                .andExpect(status().is4xxClientError());

        // verify service
        verify(experienceManagementService, times(0)).addExperiencePoints(anyInt(), anyInt());
    }

    /**
     * Test POST request to add experience points.
     *
     * Invalid json body, return 4xx error.
     *
     * @throws Exception
     */
    @Test
    public void testPostRequest_hasPlayerIdParameter_invalidRequestBody() throws Exception {

        mockMvc.perform(post("/experience/aa")
                // json content
                .contentType(MediaType.APPLICATION_JSON).content("{\"hello\" : \"world\"}"))
                // exx status
                .andExpect(status().is4xxClientError());

        // verify service
        verify(experienceManagementService, times(0)).addExperiencePoints(anyInt(), anyInt());
    }
}
