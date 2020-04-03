package com.gongxc.dao;

import com.gongxc.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuDAOLmpl implements MenuDAO {
    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<Menu>();

        Menu menu1 = new Menu();
        menu1.setCid(1);
        menu1.setMid(1);
        menu1.setMname("gong");
        menu1.setPrice(11.5f);
        menus.add(menu1);

        Menu menu2 = new Menu();
        menu2.setCid(2);
        menu2.setMid(2);
        menu2.setMname("zha");
        menu2.setPrice(18.0f);
        menus.add(menu2);

        return menus;
    }

    public List<Menu> getMenuByMidCid(String mid,String cid) {
        List<Menu> menus = new ArrayList<Menu>();

        Menu menu1 = new Menu();
        menu1.setCid(1);
        menu1.setMid(1);
        menu1.setMname("gong");
        menu1.setPrice(11.5f);
        menus.add(menu1);

        if(cid != null && mid.equalsIgnoreCase("1")){
            menus.add(menu1);
        }else {
            menus.add(menu1);
        }
        return menus;
    }

    public int addMenu(int cid, String mname, float price) {
        return 0;
    }

    public int updateMenuByMid(int mid, int cid, String name, float price) {
        return 0;
    }

    public int deleteMenuByMid(int mid) {
        return 0;
    }
}
