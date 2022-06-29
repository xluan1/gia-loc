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
import com.gialoc.springboot.model.Brand;
import com.gialoc.springboot.model.ResponseObject;
import com.gialoc.springboot.repository.BrandRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BrandController {
	@Autowired
	private BrandRepository brandRepository;

	// get brand
	@GetMapping("/brand")
	public List<Brand> getAllBrand() {
		return this.brandRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	// get brand by id
	@GetMapping("/brand/{id}")
	public ResponseEntity<Brand> getBrandById(@PathVariable(value = "id") Long brandId)
			throws ResourceNotFoundException {
		Brand brand = brandRepository.findById(brandId)
				.orElseThrow(() -> new ResourceNotFoundException("Brand not found for this id :: " + brandId));
		return ResponseEntity.ok().body(brand);
	}
	
	// save brand
	@PostMapping("/brand")
	ResponseEntity<ResponseObject> inserBrand(@RequestBody Brand newBrand) {
		newBrand.setCreated_on_utc(new Date());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Post Brand successfully", brandRepository.save(newBrand)));
	}

	// update brand
	@PutMapping("/brand/{id}")
	public ResponseEntity<ResponseObject> updateBrand(@PathVariable Long id, @RequestBody Brand brand) {
		Brand updateBrand = brandRepository.findById(id).map(oldBrand -> {
			oldBrand.setName(brand.getName());
			oldBrand.setImage(brand.getImage());
			oldBrand.setSlug(brand.getSlug());
			oldBrand.setUpdate_on_utc(brand.getUpdate_on_utc());
			return brandRepository.save(oldBrand);
		}).orElseGet(() -> {
			brand.setId(id);
			return brandRepository.save(brand);
		});

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Update Brand successfully", updateBrand));
	}

	// delete brand
	@DeleteMapping("brand/{id}")
	public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Long brandId)
			throws ResourceNotFoundException {
		Brand brand = brandRepository.findById(brandId)
				.orElseThrow(() -> new ResourceNotFoundException("Brand not found for this id :: " + brandId));

		this.brandRepository.delete(brand);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}
}
