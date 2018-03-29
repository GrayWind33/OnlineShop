package graywind.shop.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graywind.shop.bean.Cart;
import graywind.shop.bean.Commodit;
import graywind.shop.bean.Transaction;
import graywind.shop.dao.TransactionMapper;
import graywind.shop.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;
    
    @Override
    public void addTransaction(Cart cart, Commodit commodit) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setBuyerId(cart.getUserId());
        transaction.setSellerId(commodit.getOwnerId());
        transaction.setCommoditId(commodit.getId());
        transaction.setPrice(commodit.getPrice());
        transaction.setVolumn(cart.getVolumn());
        transactionMapper.addTransaction(transaction);
    }

    @Override
    public List<Transaction> getBuy(long buyerId) {
        return transactionMapper.getTransactionByBuyer(buyerId);
    }

    @Override
    public List<Transaction> getSell(long sellerId) {
        return transactionMapper.getTransactionBySeller(sellerId);
    }

}
