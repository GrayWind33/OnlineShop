package graywind.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import graywind.shop.bean.Commodit;

public interface CommoditMapper {
	public List<Commodit> getCommodit();
	
	public Commodit getSingleCommodit(@Param("commoditId") long commoditId);
}
