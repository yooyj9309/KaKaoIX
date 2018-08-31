package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import com.example.demo.domain.ProductVO;

public interface MainMapper {
	
	List<ProductVO> selectProductList();
	
	List<ProductVO> selectMyProductList(String userId);
	
	void insertProductInformation(ProductVO productInformation);
	
	ProductVO selectProductById(int productId);
	
	void insertTransaction(Map<String, Object> map);
	
	String selectAmount(Map<String, Object> map);
	
	void updateAmount(Map<String, Object> map);
}
