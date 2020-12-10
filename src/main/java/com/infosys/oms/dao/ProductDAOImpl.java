package com.infosys.oms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infosys.oms.dto.ProductDTO;
import com.infosys.oms.entity.Product;

@Repository(value="productDAO")
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<ProductDTO> getAllProducts() {
		Query q=entityManager.createQuery("select p from Product p");
		List<Product> prodList=q.getResultList();
		List<ProductDTO> pList=new ArrayList<ProductDTO>();
		if(!prodList.isEmpty())
		{
			for(Product p:prodList)
			{
				ProductDTO product=new ProductDTO();
				product.setBrand(p.getBrand());
				product.setCategory(p.getCategory());
				product.setDescription(p.getDescription());
				product.setImage(p.getImage());
				product.setPrice(p.getPrice());
				product.setProdid(p.getProdid());
				product.setProductname(p.getProductname());
				product.setRating(p.getRating());
				product.setSellerid(p.getSellerid());
				product.setStock(p.getStock());
				product.setSubcategory(p.getSubcategory());
				pList.add(product);
			}
		}
		return pList;
	}

	@Override
	public Integer addProduct(ProductDTO p) {
		Integer n;
		Product prod=new Product();
		prod.setBrand(p.getBrand());
		prod.setCategory(p.getCategory());
		prod.setDescription(p.getDescription());
		prod.setImage(p.getImage());
		prod.setPrice(p.getPrice());
		prod.setProductname(p.getProductname());
		prod.setRating(p.getRating());
		prod.setSellerid(p.getSellerid());
		prod.setStock(p.getStock());
		prod.setSubcategory(p.getSubcategory());
		entityManager.persist(prod);
		n=prod.getProdid();
		return n;
	}

	@Override
	public Integer removeProduct(Integer id) {
		Integer n;
		Product p=entityManager.find(Product.class, id);
		n=p.getProdid();
		entityManager.remove(p);
		return n;
	}

	@Override
	public List<ProductDTO> getAllProductsByName(String name) {
		Query q=entityManager.createQuery("select p from Product p where p.productname = ?1");
		q.setParameter(1, name);
		List<Product> prodList=q.getResultList();
		List<ProductDTO> pList=new ArrayList<ProductDTO>();
		if(!prodList.isEmpty())
		{
			for(Product p:prodList)
			{
				ProductDTO product=new ProductDTO();
				product.setBrand(p.getBrand());
				product.setCategory(p.getCategory());
				product.setDescription(p.getDescription());
				product.setImage(p.getImage());
				product.setPrice(p.getPrice());
				product.setProdid(p.getProdid());
				product.setProductname(p.getProductname());
				product.setRating(p.getRating());
				product.setSellerid(p.getSellerid());
				product.setStock(p.getStock());
				product.setSubcategory(p.getSubcategory());
				pList.add(product);
			}
		}
		return pList;
		
	}

	@Override
	public List<ProductDTO> getAllProductsByCategory(String category) {
		
		Query q=entityManager.createQuery("select p from Product p where p.category = ?1");
		q.setParameter(1, category);
		List<Product> prodList=q.getResultList();
		List<ProductDTO> pList=new ArrayList<ProductDTO>();
		if(!prodList.isEmpty())
		{
			for(Product p:prodList)
			{
				ProductDTO product=new ProductDTO();
				product.setBrand(p.getBrand());
				product.setCategory(p.getCategory());
				product.setDescription(p.getDescription());
				product.setImage(p.getImage());
				product.setPrice(p.getPrice());
				product.setProdid(p.getProdid());
				product.setProductname(p.getProductname());
				product.setRating(p.getRating());
				product.setSellerid(p.getSellerid());
				product.setStock(p.getStock());
				product.setSubcategory(p.getSubcategory());
				pList.add(product);
			}
		}
		return pList;
		
	}

	@Override
	public Integer updateStock(Integer id, Integer stock) {
		Query q=entityManager.createQuery("select p from Product p where p.prodid = ?1");
		q.setParameter(1, id);
		List<Product> list=q.getResultList();
		Integer stock1=0;
		for(Product p:list)
		{
			if(p.getStock()<=10)
			{
				Integer newStock = p.getStock() + stock;
				p.setStock(newStock);
				entityManager.persist(p);
				stock1=p.getStock();
			}
			else
			{
				stock1=-1;
			}
		}
		return stock1;
	}
	
	@Override
	public ProductDTO findById(Integer id) {
		Query q=entityManager.createQuery("select p from Product p where p.prodid = ?1");
		q.setParameter(1, id);
		//List<Product> prodList=q.getResultList();
		Product p = (Product)q.getSingleResult();
		ProductDTO productDTO = ProductDTO.valueOf(p);
		
		return productDTO;
	}

}
