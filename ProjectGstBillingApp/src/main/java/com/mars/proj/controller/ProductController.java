package com.mars.proj.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mars.proj.entity.Product;
import com.mars.proj.service.ProductService;

@Controller
@RequestMapping({"/products","/"})
public class ProductController {

	private ProductService productService;

	
	public ProductController(ProductService theEmployeeService) {
		productService = theEmployeeService;
	}
	
	@GetMapping("/")
	public String home()
	{
		return "redirect:/list";
	}
	@GetMapping("/list")
	public String listProducts(Model theModel) {
		
		// get products from db
		List<Product> theEmployees = productService.findAll();
		
		// add to the spring model
		theModel.addAttribute("products", theEmployees);
		
		return "products/list-products";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Product theEmployee = new Product();
		
		theModel.addAttribute("product", theEmployee);
		
		return "products/product-form";
	}
    
	@GetMapping("/showFormForBilling")
	public String showFormForBilling() {
		
		return "products/billing-form";
	}
	
	@GetMapping("/search")
	public String Searching(@RequestParam("thecode") String theCode, Model theModel)
	{
		List <Product> theEmployees = productService.findByCode(theCode);
		theModel.addAttribute("products",theEmployees);
		return "products/billing-form-action";
	}
	@GetMapping("/searchbyname")
	public String Searchingbyname(@RequestParam("thename") String theName, Model theModel)
	{
		List <Product> theEmployees = productService.findByName(theName);
		theModel.addAttribute("products",theEmployees);
		return "products/billing-form-action";
	}
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("productId") int theId,
									Model theModel) {
		
		// get the product from the service
		Product theEmployee = productService.findById(theId);
		
		// set product as a model attribute to pre-populate the form
		theModel.addAttribute("product", theEmployee);
		
		// send over to our form
		return "products/product-form";			
	}
	
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product theEmployee) {
		
		// save the product
		productService.save(theEmployee);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/products/list";
	}
	
	
	@PostMapping("/delete")
	public String delete(@RequestParam("productId") int theId) {
		
		// delete the product
		productService.deleteById(theId);
		
		// redirect to /products/list
		return "redirect:/products/list";
		
	}
}


















