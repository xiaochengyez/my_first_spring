package com.gongxc.dao;

import com.gongxc.model.Category;

import java.util.List;

public interface CategoryDAO {
    //查询所有分类
    public List<Category> getAllCategories();
    //根据id查询分类
    public List<Category> getCategoriesById(int cid);
    //根据id查询单个分类
    public Category getCategoryById(int cid);
    //添加分类
    public int addCategory(String cname);
    //更新分类
    public int updateCategoryById(int cid,String cname);
    //根据id删除分类
    public int deleteCategoryById(int cid);
}
