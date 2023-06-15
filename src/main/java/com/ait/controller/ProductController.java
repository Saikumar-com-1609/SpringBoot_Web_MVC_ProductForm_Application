package com.ait.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ait.entity.Product;
import com.ait.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository repo;

	@GetMapping("/")
	public String indexPage(Model model) {

		model.addAttribute("products", new Product());

		return "index";
	}

	@PostMapping("/save")
	public String save(@Validated @ModelAttribute("products") Product p, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "index";
		}

		if (p.getPId() != null) {
			p = repo.save(p);
			model.addAttribute("msg", "Product Updated Successfully");
		} else {
			p = repo.save(p);
			if (p.getPId() != null) {
				model.addAttribute("msg", "Product Saved Successfully");
			}
		}
		return "index";

	}

	@GetMapping("/retrieval")
	public String retrieveProducts(Model model) {
		List<Product> findAll = repo.findAll();
		model.addAttribute("list", findAll);

		return "retrieve";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("pId") Integer pId, Model model) {

		repo.deleteById(pId);

		model.addAttribute("msg", "Product Deleted");
		model.addAttribute("list", repo.findAll());

		return "retrieve";

	}

	@GetMapping("/update")
	public String upDate(@RequestParam("pId") Integer pId, Model model) {
		// get employee from the service
		Optional<Product> findById = repo.findById(pId);

		if (findById.isPresent()) {
			Product product = findById.get();

			// set employee as a model attribute to pre-populate the form
			model.addAttribute("products", product);

		}
		return "index";
	}
}
