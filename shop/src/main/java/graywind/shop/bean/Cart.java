package graywind.shop.bean;

import java.sql.Timestamp;

public class Cart {
	private long userId;
	private long commoditId;
	private int volumn;
	private Timestamp add_time;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public Timestamp getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Timestamp add_time) {
		this.add_time = add_time;
	}

}
