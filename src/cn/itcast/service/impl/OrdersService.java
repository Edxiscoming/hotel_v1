package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.IOrdersDao;
import cn.itcast.entity.Orders;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IOrdersService;
import cn.itcast.utils.PageBean;

public class OrdersService implements IOrdersService{

	IOrdersDao dao = BeanFactory.getInstance("ordersDao", IOrdersDao.class);
	@Override
	public void update(Orders orders) {
		dao.update(orders);
	}

	@Override
	public List<Orders> query() {
		return dao.query();
	}

	@Override
	public void add(Orders orders) {
		dao.add(orders);
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}
	
	@Override
	public void getAll(PageBean<Orders> pb) {
		try {
			dao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
