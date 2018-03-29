package graywind.shop.service;

import java.sql.SQLException;
import java.util.List;

import graywind.shop.bean.Cart;

public interface CartService {
	public void addCart(long userId,long commoditId) throws SQLException;
	
	public void deleteCart(long userId,long commoditId) throws SQLException;
	
	/**
	 * 下单购买，检查库存数量，检查余额，添加订单，更改余额，清除购物车
	 * @param userId
	 * @param commoditId
	 * @throws SQLException
	 */
	public void buy(long userId,long commoditId) throws SQLException;
	
	/**
	 * 获取某个用户的购物车信息
	 * @param userId
	 * @return
	 */
	public List<Cart> getCarts(long userId);
}
