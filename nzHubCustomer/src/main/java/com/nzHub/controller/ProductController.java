package com.nzHub.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nzHub.entity.Product;
import com.nzHub.entity.User;
import com.nzHub.exception.nzHubException;
import com.nzHub.result.ResponseEnum;
import com.nzHub.service.CartService;
import com.nzHub.service.ProductCategoryService;
import com.nzHub.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;

    /**
     * product list
     * @param type
     * @param productCategoryId
     * @param session
     * @return
     */
    @GetMapping("/list/{type}/{id}")
    public ModelAndView list(
            @PathVariable("type") Integer type,
            @PathVariable("id") Integer productCategoryId,
            HttpSession session
    ){
        if(type == null || productCategoryId == null){
            log.info("[product list]empty parameter");
            throw new nzHubException(ResponseEnum.PARAMETER_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("productList", this.productService.findAllByTypeAndProductCategoryId(type, productCategoryId));
        //Check whether the user is already logged in
        User user = (User) session.getAttribute("user");
        if(user == null){
            //no login
            modelAndView.addObject("cartList", new ArrayList<>());
        }else{
            //login
            //query the cart history of user
            modelAndView.addObject("cartList", this.cartService.findVOListByUserId(user.getId()));
        }
        //product category
        modelAndView.addObject("list", this.productCategoryService.buildProductCategoryMenu());
        return modelAndView;
    }

    /**
     * product search
     * @param keyWord
     * @param session
     * @return
     */
    @PostMapping("/search")
    public ModelAndView search(String keyWord,HttpSession session){
        if(keyWord == null){
            log.info("[product search]empty parameter");
            throw new nzHubException(ResponseEnum.PARAMETER_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        //Fuzzy query data
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", keyWord);
        modelAndView.addObject("productList",this.productService.list(queryWrapper));
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
        //product category
        modelAndView.addObject("list", this.productCategoryService.buildProductCategoryMenu());
        return modelAndView;
    }

    /**
     * product detail
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Integer id,HttpSession session){
        if(id == null){
            log.info("[product detail]empty parameter");
            throw new nzHubException(ResponseEnum.PARAMETER_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
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
        //product category
        modelAndView.addObject("list", this.productCategoryService.buildProductCategoryMenu());
        //product detail
        modelAndView.addObject("product", this.productService.getById(id));
        return modelAndView;
    }

}

