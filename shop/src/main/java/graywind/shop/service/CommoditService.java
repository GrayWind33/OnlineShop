package graywind.shop.service;

import java.util.List;

import graywind.shop.bean.Commodit;

public interface CommoditService {
	
    /**
     * 获取某个用户可以购买的商品清单
     * @param userId
     * @return
     */
    public List<Commodit> getCommodit(long userId);
}
