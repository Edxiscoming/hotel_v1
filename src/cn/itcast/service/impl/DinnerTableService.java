package cn.itcast.service.impl;

import java.util.Date;
import java.util.List;

import cn.itcast.dao.IDinnerTableDao;
import cn.itcast.entity.DinnerTable;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IDinnerTableService;

public class DinnerTableService implements IDinnerTableService{

	IDinnerTableDao dao = BeanFactory.getInstance("dinnerTableDao", IDinnerTableDao.class);
	@Override
	public void add(DinnerTable dt) {
		dao.add(dt);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public void updata(DinnerTable dt) {
		dao.updata(dt);
	}

	@Override
	public List<DinnerTable> query(String keyword) {
		return dao.query(keyword);
	}
	
	@Override
	public List<DinnerTable> query() {
		return dao.query();
	}
	
	@Override
	public DinnerTable changeState(int id) {
		DinnerTable table = dao.findById(id);
		
		int status = table.getTableStatus();
		if(status==0){
			status=1;
			Date date = new Date();
			table.setOrderDate(date);
		}else if(status==1){
			status=0;
			table.setOrderDate(null);
		}
		table.setTableStatus(status);
		dao.updata(table);
		return table;
	}
	
	@Override
	public DinnerTable findById(int id) {
		return dao.findById(id);
	}
	@Override
	public void quitTable(int id) {
		dao.quitTable(id);
	}
}
