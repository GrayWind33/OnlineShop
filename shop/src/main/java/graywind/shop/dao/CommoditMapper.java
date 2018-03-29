package graywind.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import graywind.shop.bean.Commodit;

public interface CommoditMapper {
    public List<Commodit> getCommodit(@Param("userId") long userId);

    public Commodit getSingleCommodit(@Param("commoditId") long commoditId);

    public void delete(@Param("commoditId") long commoditId);

    public void update(@Param("commoditId") long commoditId,@Param("volumn") int volumn);
}
