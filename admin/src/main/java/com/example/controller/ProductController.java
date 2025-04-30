package com.example.controller;


import com.example.entity.Product;
import com.example.entity.ProductCategory;
import com.example.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2025-04-26
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
//        model.addAttribute("product", new Product());
//        return "product_admin";

        // 加载所有一级分类
        List<ProductCategory> levelOneCategories = productService.getCategoriesByType(1);
        model.addAttribute("levelOneCategories", levelOneCategories);
        model.addAttribute("product", new Product());
        return "product_admin";
    }

    // 添加获取子分类的接口
    @GetMapping("/subcategories")
    @ResponseBody
    public List<ProductCategory> getSubcategories(@RequestParam Integer parentId) {
        // 处理 parentId=0 的情况（获取一级分类）
        if (parentId == 0) {
            return productService.getCategoriesByType(1);
        }
        return productService.getCategoriesByParentId(parentId);
    }


    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        Product product = productService.getAllProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID. " + id));
        model.addAttribute("product", product);
        return "product_admin";
    }



    @GetMapping("/category")
    public String findByCategory(@RequestParam int level, @RequestParam int id, Model model) {
//        model.addAttribute("products", productService.findByCategoryId(level, id));
//        return "product_admin";
        List<Product> products = productService.findByCategoryId(level, id);
//        if (products == null) {
//            products = new ArrayList<>();
//        }
        model.addAttribute("products", products);
        model.addAttribute("product", new Product()); // 添加此行，确保表单能渲染
        return "product_admin";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam String keyword, Model model) {
//        model.addAttribute("products", productService.searchByName(keyword));
//        return "product_admin";
        List<Product> products = productService.searchByName(keyword);
//        if (products == null) {
//            products = new ArrayList<>();
//        }
        model.addAttribute("products", products);
        model.addAttribute("product", new Product()); // 添加此行
        return "product_admin";
    }


}

