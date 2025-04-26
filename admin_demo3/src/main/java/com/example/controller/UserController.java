package com.example.controller;


import com.example.service.UserService;
import com.example.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2025-04-26
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService; // 注入接口

    // 通过构造函数注入
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User()); // 用于表单绑定
        return "list";
    }

//    @PostMapping("/save")
//    public String saveUser(@ModelAttribute User user) {
//        userService.saveUser(user);
//        return "redirect:/user";
//    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        // 自动填充时间字段的逻辑由 MyBatis-Plus 处理
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        User user = userService.getAllUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "list";
    }

}

