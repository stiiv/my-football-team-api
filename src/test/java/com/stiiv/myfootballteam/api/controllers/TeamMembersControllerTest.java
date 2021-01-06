package com.stiiv.myfootballteam.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stiiv.myfootballteam.api.MyFootballTeamApiApplication;
import com.stiiv.myfootballteam.api.models.TeamMember;
import com.stiiv.myfootballteam.api.repositories.TeamMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MyFootballTeamApiApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class TeamMembersControllerTest {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private TeamMembersController teamMembersController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getMembers() throws JsonProcessingException {
        String url = "/api/team/members";
        ObjectMapper objectMapper = new ObjectMapper();
        String member = objectMapper.writeValueAsString(new TeamMember("Mate"));

        MediaType jsonString = new MediaType(MediaType.APPLICATION_JSON);
        try {
            mockMvc.perform(post(url)
                    .content(member)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content()
                            .contentType(jsonString));

            mockMvc.perform(get(url)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(
                            jsonPath("$[0].name", is("Mate"))
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}