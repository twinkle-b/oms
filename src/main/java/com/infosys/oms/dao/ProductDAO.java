package com.infosys.oms.dao;

import java.util.List;

import com.infosys.oms.dto.ProductDTO;

public interface ProductDAO {
	
	public List<ProductDTO> getAllProducts();
	public Integer addProduct(ProductDTO p);
	public Integer removeProduct(Integer id);
	public List<ProductDTO> getAllProductsByName(String name);
	public List<ProductDTO> getAllProductsByCategory(String category);
	public Integer updateStock(Integer id, Integer stock);
	public ProductDTO findById(Integer id);
}
