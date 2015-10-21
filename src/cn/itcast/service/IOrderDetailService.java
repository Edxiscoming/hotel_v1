package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.OrderDetail;

public interface IOrderDetailService {

	void add(OrderDetail od);
	
	List<OrderDetail> query();
	
	List<OrderDetail> findByOrderId(int id);
}
