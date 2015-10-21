package cn.itcast.service;

import java.util.List;

import cn.itcast.entity.Orders;
import cn.itcast.utils.PageBean;

public interface IOrdersService {
	
	void update(Orders orders);

	List<Orders> query();

	void add(Orders orders);
	
	int getCount();
	
	public void getAll(PageBean<Orders> pb);
}
