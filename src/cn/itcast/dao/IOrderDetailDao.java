package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.OrderDetail;

public interface IOrderDetailDao {

	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderid(int id);
}
