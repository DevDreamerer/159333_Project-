package com.nzHub.controller;

import com.nzHub.entity.User;
import com.nzHub.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class RedirectController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{url}")
    public ModelAndView redirect(@PathVariable("url") String url, HttpSession session){

      //modify
        if ("error".equals(url)) {
            throw new RuntimeException("Manually throw an exception and let Spring Boot handle it");
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        //Check whether the user is already logged in
        User user = (User) session.getAttribute("user");
        if(user == null){
            //no login
            modelAndView.addObject("cartList", new ArrayList<>());
        }else{
            //login user
            //query the cart history of user
            modelAndView.addObject("cartList", this.cartService.findVOListByUserId(user.getId()));
        }
        return modelAndView;
    }

    @GetMapping("/")
    public String main(){
        return "redirect:/productCategory/main";
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

}