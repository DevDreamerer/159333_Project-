package com.nzHub.controller;


import com.nzHub.entity.User;
import com.nzHub.service.CartService;
import com.nzHub.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * <p>
 *  frontend controller
 * </p>
 *
 */
@Controller
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;

    /**
     * date from homepage
     * @param session
     * @return
     */
    @GetMapping("/main")
    public ModelAndView main(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        //Encapsulate product category menu
        modelAndView.addObject("list", this.productCategoryService.buildProductCategoryMenu());
        //Product information corresponding to the primary classification of packaging
        modelAndView.addObject("levelOneProductList",this.productCategoryService.findAllProductByCategoryLevelOne());
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

}

