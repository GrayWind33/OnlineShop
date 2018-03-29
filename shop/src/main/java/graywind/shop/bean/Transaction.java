package graywind.shop.bean;

import java.sql.Timestamp;

public class Transaction {
    private long id;
    private long buyerId;
    private long sellerId;
    private long commoditId;
    private int volumn;
    private double price;
    private Timestamp produceTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getCommoditId() {
        return commoditId;
    }

    public void setCommoditId(long commoditId) {
        this.commoditId = commoditId;
    }

    public int getVolumn() {
        return volumn;
    }

    public void setVolumn(int volumn) {
        this.volumn = volumn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Timestamp produceTime) {
        this.produceTime = produceTime;
    }

}
