package com.gongxc.dao;

import com.gongxc.model.Menu;

import java.util.List;

public interface MenuDAO {
    //获取所有菜单
    public List<Menu> getAllMenus();
    //根据id查询菜单
    public List<Menu> getMenuByMidCid(String mid,String cid);


    //添加菜单
    public int addMenu(int cid,String mname,float price);
    //更新菜单
    public int updateMenuByMid(int mid,int cid,String name,float price);
    //删除菜单
    public int deleteMenuByMid(int mid);

}
