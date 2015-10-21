package cn.itcast.dao;

import java.util.List;

import cn.itcast.entity.Orders;
import cn.itcast.utils.PageBean;

public interface IOrdersDao {

	void update(Orders orders);
	
	List<Orders> query();

	void add(Orders orders);
	
	int getCount();

	void getAll(PageBean<Orders> pb);

	int getTotalCount();
}
