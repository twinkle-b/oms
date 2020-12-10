package com.infosys.oms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.oms.dao.ProductDAO;
import com.infosys.oms.dto.ProductDTO;

@Transactional
@Service(value="productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public Integer addProduct(ProductDTO p) throws Exception{
		Integer n=productDAO.addProduct(p);
		if(n==0)
		{
			throw new Exception("ProductService.PRODUCT_NOT_ADDED");
		}
		return n;
	}

	@Override
	public Integer removeProduct(Integer pid) throws Exception{
		Integer id=productDAO.removeProduct(pid);
		if(id==0)
		{
			throw new Exception("ProductService.PRODUCT_NOT_FOUND");
		}
		return id;
	}

	@Override
	public List<ProductDTO> getProducts() throws Exception{
		List<ProductDTO> list=productDAO.getAllProducts();
		if(list.isEmpty())
		{
			throw new Exception("ProductService.NO_PRODUCTS_AVAILABLE");
		}
		return list;
	}

	@Override
	public List<ProductDTO> getProductsByName(String name) throws Exception {
		List<ProductDTO> list=productDAO.getAllProductsByName(name);
		if(list.isEmpty())
		{
			throw new Exception("ProductService.NO_PRODUCTS_AVAILABLE");
		}
		return list;
	}

	@Override
	public List<ProductDTO> getProductsByCategory(String category) throws Exception {
		List<ProductDTO> list=productDAO.getAllProductsByCategory(category);
		if(list.isEmpty())
		{
			throw new Exception("ProductService.NO_PRODUCTS_AVAILABLE");
		}
		return list;
	}

	@Override
	public Integer updateStock(Integer id, Integer stock) throws Exception {
		Integer newStock=productDAO.updateStock(id, stock);
		if(newStock==0)
		{
			throw new Exception("ProductService.NO_PRODUCT_FOUND");
		}
		else if(newStock==-1)
		{
			throw new Exception("ProductService.STOCK_EFFICIENT");
		}
		return newStock;
	}

	@Override
	public ProductDTO findById(Integer id) throws Exception{
		ProductDTO productDTO = productDAO.findById(id);
		if(productDTO == null) {
			throw new Exception("ProductService.NO_PRODUCT_FOUND");
		}
		return productDTO;
	}
}
