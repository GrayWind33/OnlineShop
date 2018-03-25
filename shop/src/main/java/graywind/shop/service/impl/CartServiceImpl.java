package graywind.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graywind.shop.bean.Cart;
import graywind.shop.dao.CartMapper;
import graywind.shop.dao.CommoditMapper;
import graywind.shop.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private CommoditMapper commoditMapper;

	@Override
	public void addCart(long userId, long commoditId) {
		Cart cart = cartMapper.getSingleCart(userId, commoditId);
		if (cart == null) {
			cart = new Cart();
			cart.setUserId(userId);
			cart.setVolumn(1);
			cart.setCommoditId(commoditId);
			cartMapper.addCart(cart);
			return;
		}
		cart.setVolumn(cart.getVolumn() + 1);
		cartMapper.updateCart(userId, commoditId);
	}

	@Override
	public List<Cart> getCarts(long userId) {
		return cartMapper.getCarts(userId);
	}

}
