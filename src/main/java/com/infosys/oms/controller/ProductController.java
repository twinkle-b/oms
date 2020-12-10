package com.infosys.oms.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infosys.oms.dto.ProductDTO;
import com.infosys.oms.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService prodService; 
	
	@Autowired
	private Environment env;
	
	@PostMapping(value="/saveproduct",consumes=MediaType.APPLICATION_JSON_VALUE)
	public Integer saveProducts(@RequestBody ProductDTO p) throws Exception
	{
		return prodService.addProduct(p);
	}
	
	@GetMapping(value="/showproducts")
	public ResponseEntity<List<ProductDTO>> showProducts() throws Exception{
		try
		{
			List<ProductDTO> list=prodService.getProducts();
			return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
	
	
	@GetMapping(value="/removeproducts/{id:.+}")
	public ResponseEntity<String> removeProduct(@PathVariable("id") Integer id) throws Exception{
		
		try {
			Integer i=prodService.removeProduct(id);
			String msg=env.getProperty("ProductController.PRODUCT_REMOVED_SUCCESSFULLY");
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value="/showproductsbyname/{name:.+}")
	public ResponseEntity<List<ProductDTO>> showProductsByName(@PathVariable("name") String name) throws Exception{
		try
		{
			List<ProductDTO> list=prodService.getProductsByName(name);
			return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value="/showproductsbycategory/{category:.+}")
	public ResponseEntity<List<ProductDTO>> showProductsByCategory(@PathVariable("category") String category) throws Exception{
		try
		{
			List<ProductDTO> list=prodService.getProductsByCategory(category);
			return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value="/updatestock/{id:.+}/{stock:.+}")
	public ResponseEntity<String> updateStock(@PathVariable("id") Integer id,@PathVariable("stock") Integer stock) throws Exception{
		try
		{
			Integer n=prodService.updateStock(id, stock);
			String msg=env.getProperty("ProductController.STOCK_UPDATED_SUCCESSFULLY")+" "+n;
			return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, env.getProperty(e.getMessage()));
		}
	}
	
	//getProduct for buyer
	@GetMapping(value="/getProduct/{id}")
	public ProductDTO getProducts(@PathVariable("id") Integer id) throws Exception{
		try
		{
			ProductDTO productDTO=prodService.findById(id);	
			return productDTO;
		}
		catch(Exception e)
		{
			throw new Exception("Error this product does not exist");
		}
	}
}
