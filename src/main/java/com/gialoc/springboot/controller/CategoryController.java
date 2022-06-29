package com.gialoc.springboot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gialoc.springboot.exception.ResourceNotFoundException;
import com.gialoc.springboot.model.Category;
import com.gialoc.springboot.model.ResponseObject;
import com.gialoc.springboot.repository.CategoryRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")

public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

	// get category
	@GetMapping("/category")
	public List<Category> getAllCategory() {
		return this.categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	// get category by id
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId)
			throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
		return ResponseEntity.ok().body(category);
	}
	
	// save category
	@PostMapping("/category")
	ResponseEntity<ResponseObject> inserCategory(@RequestBody Category newCategory) {
		newCategory.setCreated_on_utc(new Date());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Post Category successfully", categoryRepository.save(newCategory)));
	}

	// update category
	@PutMapping("/category/{id}")
	public ResponseEntity<ResponseObject> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		Category updateCategory = categoryRepository.findById(id).map(oldCategory -> {
			oldCategory.setName(category.getName());
			oldCategory.setImage(category.getImage());
			oldCategory.setSlug(category.getSlug());
			oldCategory.setUpdate_on_utc(new Date());
			return categoryRepository.save(oldCategory);
		}).orElseGet(() -> {
			category.setId(id);
			return categoryRepository.save(category);
		});

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Update Category successfully", updateCategory));
	}

	// delete category
	@DeleteMapping("category/{id}")
	public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Long categoryId)
			throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));

		this.categoryRepository.delete(category);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}
}
