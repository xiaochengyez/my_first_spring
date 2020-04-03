package com.gongxc.service;

import com.gongxc.dao.MenuDAO;
import com.gongxc.dao.MenuDAOLmpl;
import com.gongxc.model.Menu;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuManagerImpl implements MenuManager {
    Logger logger = Logger.getLogger(MenuManagerImpl.class);

    MenuDAO menuDAO = new MenuDAOLmpl();
    public List<Menu> getAllMenus() {
        return menuDAO.getAllMenus();
    }

    public List<Menu> getMenusByMidCid(String mid, String cid) {
        return menuDAO.getMenuByMidCid(mid,cid);
    }

    public Menu getMenuByMid(String mid) {
        List<Menu> menus = menuDAO.getMenuByMidCid(mid,"%");
        if (menus == null){
            logger.info("查询menu无返回接口，请检查后台是否出错！");
            return null;
        }
        int size = menus.size();
        if (size == 0){
            logger.info("");
            return null;
        }else if (size > 1){
            logger.info("");
            return null;
        }
        return menus.get(0);
    }

    public int addMenu(int cid, String mname, float price) {
        logger.info("");
        return menuDAO.addMenu(cid,mname,price);
    }

    public int updateMenuByMid(int mid, int cid, String mname, float price) {
        logger.info("");
        return menuDAO.updateMenuByMid( mid, cid, mname, price);
    }

    public int deleteMenuByMid(int mid) {
        int inpactRowNum = menuDAO.deleteMenuByMid(mid);
        if (inpactRowNum == 1){
            logger.info("");
        }else {
            logger.info("");
        }
        return inpactRowNum;
    }
}
