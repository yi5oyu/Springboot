package com.example.springboottest.controller;

import com.example.springboottest.entity.User;
import com.example.springboottest.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@OpenAPIDefinition(
    info = @Info(
        title = "Spring Test API",
        version = "1.0",
        description = "Spring boot Test API 문서"
    )
)
@Tag(name = "User", description = "유저 관리")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "ID로 유저 찾기", description = "유니크 ID로 특정 유저 찾음")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "유저 발견",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "유저 없음")
    })
    @GetMapping("/{id}")
    public User getUserById(
        @Parameter(description = "유저의 유니크 ID", required = true, example = "1")
        @PathVariable Long id
    ){
        return userService.getUserById(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
