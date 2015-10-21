package cn.itcast.service.impl;

import java.util.List;

import cn.itcast.dao.IFoodTypeDao;
import cn.itcast.entity.FoodType;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IFoodTypeService;

public class FoodTypeService implements IFoodTypeService{

	IFoodTypeDao dao = BeanFactory.getInstance("foodTypeDao", IFoodTypeDao.class) ;
	
	@Override
	public void add(FoodType foodtype) {
		dao.add(foodtype);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

	@Override
	public void updata(FoodType foodtype) {
		dao.updata(foodtype);
	}

	@Override
	public List<FoodType> query() {
		return dao.query();
	}

	@Override
	public FoodType findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<FoodType> query(String keyword) {
		return dao.query(keyword);
	}

	@Override
	public Integer getFirstType() {
		return dao.getFirstType();
	}
}
