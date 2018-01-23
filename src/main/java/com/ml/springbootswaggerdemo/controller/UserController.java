package com.ml.springbootswaggerdemo.controller;

import com.ml.springbootswaggerdemo.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Marcus Li
 * @date 2018/1/21
 */
@RestController
@RequestMapping("/rest")
public class UserController {

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = Arrays.asList(
                new User(1, "张三", 20),
                new User(2, "王五", 33),
                new User(3, "李四", 29));
        return ResponseEntity.ok(users);
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(new Random().nextInt());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @ApiOperation(value = "修改用户", notes = "根据User对象修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要更新用户的Id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "删除用户", notes = "根据用户Id删除用户")
    @ApiImplicitParam(name = "id", value = "要删除用户的Id", required = true, dataType = "int")
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(null);
    }


}
