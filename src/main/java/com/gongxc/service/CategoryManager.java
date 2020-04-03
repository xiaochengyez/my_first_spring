package com.gongxc.service;

import com.gongxc.model.Category;

import java.util.List;

public interface CategoryManager {
    public List<Category> getAllCategories();

    public Category getCategoryById(int cid);

    public int addCategory(String cname);

    public int updateCategoryById(int cid,String cname);

    public int deleteCategoryById(int cid);
}
