package kr.ac.hansung.cse.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/productInventory")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		return "productInventory";
	}
	
	@GetMapping("/productInventory/addProduct")
	public String addProduct(Model model) {
		Product product = new Product();
		product.setCategory("컴퓨터");
		model.addAttribute("product", product);
		return "editProduct";
	}
	
	@PostMapping("/productInventory/addProduct")
	public String addProductPost(@Valid Product product, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("=== Form does not validated ===");

			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error: errors) {
				System.out.println(error.getDefaultMessage());
			}

			return "editProduct";
		}

		productService.addProduct(product);

		return "redirect:/productInventory";
	}
	
	@GetMapping("/productInventory/deleteProduct/{id}")
	public String deleteProduct(@PathVariable int id) {
		Product hasToDelete = productService.getProductById(id);
		productService.deleteProduct(hasToDelete);

		return "redirect:/productInventory";
	}
	
	@GetMapping("/productInventory/updateProduct/{id}")
	public String updateProduct(@PathVariable int id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "editProduct";
	}
	
	@PostMapping("/productInventory/updateProduct")
	public String updateProductPost(@Valid Product product, BindingResult result) {

		if(result.hasErrors()) {
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "editProduct";
		}
		productService.updateProduct(product);
		return "redirect:/productInventory";
	}
}
