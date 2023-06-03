package kr.ac.hansung.cse.hellospringdatajpa.controller;

import kr.ac.hansung.cse.hellospringdatajpa.entity.Product;
import kr.ac.hansung.cse.hellospringdatajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Product> productList = productService.listAll();
        model.addAttribute("productList", productList);

        return "index";
    }

    @GetMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable(name = "id") int id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);

        return "edit_product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        productService.delete(id);
        return "redirect:/";
    }
}
