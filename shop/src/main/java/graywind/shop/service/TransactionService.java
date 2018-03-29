package graywind.shop.service;

import java.sql.SQLException;
import java.util.List;

import graywind.shop.bean.Cart;
import graywind.shop.bean.Commodit;
import graywind.shop.bean.Transaction;

public interface TransactionService {
    public void addTransaction(Cart cart, Commodit commodit) throws SQLException;
    
    public List<Transaction> getBuy(long buyerId);
    
    public List<Transaction> getSell(long sellerId);
}
