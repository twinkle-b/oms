package com.infosys.oms.service;

import java.util.List;

import com.infosys.oms.dto.ProductDTO;

public interface ProductService {
	
	public Integer addProduct(ProductDTO p) throws Exception;
	public Integer removeProduct(Integer pid) throws Exception;
	public List<ProductDTO> getProducts() throws Exception;
	public List<ProductDTO> getProductsByName(String name) throws Exception;
	public List<ProductDTO> getProductsByCategory(String category) throws Exception;
	public Integer updateStock(Integer id, Integer stock) throws Exception;
	public ProductDTO findById(Integer id) throws Exception;
}
