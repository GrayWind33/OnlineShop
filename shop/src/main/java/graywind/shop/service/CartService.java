package graywind.shop.service;

import java.util.List;

import graywind.shop.bean.Cart;

public interface CartService {
	public void addCart(long userId,long commoditId);
	
	public List<Cart> getCarts(long userId);
}
