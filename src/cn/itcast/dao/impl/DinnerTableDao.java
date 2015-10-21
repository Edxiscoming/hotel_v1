package cn.itcast.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.dao.IDinnerTableDao;
import cn.itcast.entity.DinnerTable;
import cn.itcast.utils.JdbcUtils;

public class DinnerTableDao implements IDinnerTableDao{
	private QueryRunner qr = JdbcUtils.getQuerrRunner();
	
	@Override
	public void add(DinnerTable dt) {
		try {
			String sql = "INSERT dinnertable(tableName) VALUES(?)";
			qr.update(sql, dt.getTableName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public DinnerTable findById(int id){
		String sql = "select * from dinnertable where id=?";
		try {
			return qr.query(sql, new BeanHandler<DinnerTable>(DinnerTable.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from dinnertable where id=?";
		try {
			qr.update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updata(DinnerTable dt) {
		String sql = "UPDATE dinnertable SET tableStatus=?,orderDate=? WHERE id=?";
		Date date = dt.getOrderDate();
		try {
			JdbcUtils.getQuerrRunner().update(sql, dt.getTableStatus(),date,dt.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> query(String keyword) {
		String sql = "SELECT * FROM dinnertable WHERE tableName LIKE ?";
		try {
			return qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class),"%"+keyword +"%");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> query() {
		String sql = "select * from dinnertable";
		try {
			return qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void quitTable(int id) {
		String sql = "UPDATE dinnertable SET tableStatus=?,orderDate=? WHERE id=?";
	
		try {
			JdbcUtils.getQuerrRunner().update(sql,0,null,id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
