package graywind.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graywind.shop.bean.Commodit;
import graywind.shop.dao.CommoditMapper;
import graywind.shop.service.CommoditService;

@Service
public class CommoditServiceImpl implements CommoditService {

	@Autowired
	private CommoditMapper commoditMapper;
	
	@Override
	public List<Commodit> getCommodit() {
		return commoditMapper.getCommodit();
	}

}
