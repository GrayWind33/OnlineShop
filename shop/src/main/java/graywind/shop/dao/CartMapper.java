package graywind.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import graywind.shop.bean.Cart;

public interface CartMapper {
	public void addCart(Cart cart);
	
	public void updateCart(@Param("userId") long userId,@Param("commoditId") long commoditId);
	
	public Cart getSingleCart(@Param("userId") long userId,@Param("commoditId") long commoditId);
	
	public List<Cart> getCarts(@Param("userId") long userId);
}
