package com.example.springboottest;

import com.example.springboottest.entity.User;
import com.example.springboottest.controller.UserController;
import com.example.springboottest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Arrays;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureRestDocs
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(
            new User(1L, "lee", "lee@google.com"),
            new User(2L, "aaaa", "bbbb@naver.com")
        ));

        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andDo(document("get-all-users",
                preprocessResponse(prettyPrint()),
                responseFields(
                    fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("ID"),
                    fieldWithPath("[].name").type(JsonFieldType.STRING).description("이름"),
                    fieldWithPath("[].email").type(JsonFieldType.STRING).description("이메일")
                )
            ));
    }
    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(new User(1L, "lee", "lee@google.com"));

        mockMvc.perform(get("/users/{id}", 1L))
            .andExpect(status().isOk())
            .andDo(document("get-user-by-id",
                pathParameters(
                    parameterWithName("id").description("검색할 유저 ID")
                ),
                responseFields(
                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("ID"),
                    fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                    fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
                )
            ));
    }
    @Test
    public void testAddUser() throws Exception {
        String userJson = "{\"name\": \"lee\", \"email\": \"lee@google.com\"}";

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
            .andExpect(status().isOk())
            .andDo(document("add-user",
                requestFields(
                    fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                    fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
                )
            ));
    }
    @Test
    public void testUpdateUser() throws Exception {
        String userJson = "{\"name\": \"abcd\", \"email\": \"abcd@google.com\"}";

        mockMvc.perform(put("/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
            .andExpect(status().isOk())
            .andDo(document("update-user",
                pathParameters(
                    parameterWithName("id").description("업데이트할 ID")
                ),
                requestFields(
                    fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                    fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
                )
            ));
    }
    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/{id}", 1L))
            .andExpect(status().isOk())
            .andDo(document("delete-user",
                pathParameters(
                    parameterWithName("id").description("삭제할 ID")
                )
            ));
    }
}
