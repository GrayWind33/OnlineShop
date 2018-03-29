package graywind.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import graywind.shop.bean.Transaction;

public interface TransactionMapper {
    public List<Transaction> getTransactionByBuyer(@Param("buyerId") long buyerId);
    
    public List<Transaction> getTransactionBySeller(@Param("sellerId") long sellerId);
    
    public void addTransaction(Transaction transaction);
}
