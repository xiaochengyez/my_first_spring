package com.gongxc.controller;

import com.gongxc.model.Menu;
import com.gongxc.service.CategoryManager;
import com.gongxc.service.MenuManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
public class OrderEntryControllor {
    @Autowired
    MenuManager menuManager;

    @Autowired
    CategoryManager categoryManager;

    Logger logger = Logger.getLogger(OrderEntryControllor.class);

    @RequestMapping(value = "/showMenus")
    public  String showMenus(Model model,
                             @RequestParam(value = "mid",required = false) String mid,
                             @RequestParam(value = "cid",required = false) String cid,
                             @RequestParam(value = "useCookie",required = false) String useCookie,
                             HttpServletRequest request,
                             HttpServletResponse response){
        logger.info("");
        logger.info("");
        logger.info("");

        if (mid == null || mid.equalsIgnoreCase("") || mid.equalsIgnoreCase("all")){
            mid = "%";
        }
        if (cid == null || cid.equalsIgnoreCase("") || cid.equalsIgnoreCase("")){
            cid = "%";
        }

        HttpSession session = request.getSession();
        String sessionId = session.getId();
        model.addAttribute("menus",menuManager.getMenusByMidCid(mid,cid));
        session.setAttribute("categoryManager",categoryManager);

        if(useCookie != null && useCookie.equalsIgnoreCase("on")){

        }else {

        }
        logger.info("");
        return "jsp/menuList.jsp";
    }
/**
 * 展示编辑菜品页
 */
    @RequestMapping(value = "/editMenu/{mid}",method = RequestMethod.GET)
    public String editMenu(Model model,
                           @PathVariable String mid,
                           HttpServletRequest request){
        logger.info("Start editMenu");
        Menu menu = menuManager.getMenuByMid(mid);
        HttpSession session = request.getSession();

        model.addAttribute("menu",menu);
        session.setAttribute("categoryManager",categoryManager);
        return "jsp/menuEdit.jsp";
    }

    /**
     * 展示添加菜品页
     */
    @RequestMapping(value = "/addMenu",method = RequestMethod.GET)
    public String addMenu(Model model,HttpServletRequest request){
        logger.info("Strart addMenu");
        HttpSession session = request.getSession();

        session.setAttribute("categoryManager",categoryManager);
        return "jsp/menuAdd.jsp";
    }

    /**
     * 保存菜品
     */
    @RequestMapping(value = "/saveMenu",method = RequestMethod.POST)
    public String saveMenu(Model model,
                           HttpServletRequest request,
                           @RequestParam(value = "mid",required = true) int mid,
                           @RequestParam(value = "new_cid",required = true) int cid,
                           @RequestParam(value = "mname",required = true) String mname,
                           @RequestParam(value = "price",required = true) float price) throws UnsupportedEncodingException {
        if (mname != null && !mname.equalsIgnoreCase("")){
            mname = new String(mname.getBytes("ISO-8859-1"),"UTF-8");
        }
        if (mid>=1){
            logger.info("save menu");
            logger.info(mid);
            logger.info(cid);
            logger.info(mname);
            logger.info(price);
            menuManager.updateMenuByMid(mid,cid,mname,price);
        }else if (mid == -1){
            logger.info("add menu");
            logger.info(cid);
            logger.info(mname);
            logger.info(price);
            menuManager.addMenu(cid,mname,price);
        }else {
            logger.error("error");
        }

        HttpSession session = request.getSession();
        model.addAttribute("menus",menuManager.getAllMenus());
        session.setAttribute("categoryManager",categoryManager);
        return "jsp/menuList.jsp";
    }

    /**
     * del
     */
    @RequestMapping(value = "delete/{mid}",method = RequestMethod.GET)
    public  String  deleteMenu(Model model,
                               @PathVariable int mid,
                               HttpServletRequest request,
                               HttpServletResponse response){
        menuManager.deleteMenuByMid(mid);

        HttpSession session = request.getSession();
        model.addAttribute("menus" ,menuManager.getAllMenus());
        session.setAttribute("categoryManager",categoryManager);
        return "/showMenus";
    }

    @RequestMapping(value = "/addCategory",method = RequestMethod.GET)
    public  String addCategory(Model model){
        logger.info("add category");
        return "jsp/categoryAdd.jsp";
    }

    @RequestMapping(value = "/editCategory/{cid}")
    public String editCategory(Model model, @PathVariable int cid){
        model.addAttribute("category",categoryManager.getCategoryById(cid));
        return "jsp/categoryEdit.jsp";
    }

    @RequestMapping(value = "/saveCategory",method = RequestMethod.POST)
    public String saveCategory(Model model,
                               @RequestParam(value = "cid",required = true)int cid,
                               @RequestParam(value = "cname",required = true)String cname) throws UnsupportedEncodingException {
        if (cname != null && !cname.equalsIgnoreCase("")){
            cname = new String(cname.getBytes("ISO-8859-1"),"utf-8");
        }
        if (cid >=1){
            logger.info("update menu");
            logger.info(cid);
            logger.info(cname);
            categoryManager.updateCategoryById(cid,cname);
        }else if (cid == -1){
            logger.info("add");
            logger.info(cname);
            categoryManager.addCategory(cname);
        }else {
            logger.info("error");
        }
        model.addAttribute("category",categoryManager.getAllCategories());
        return "jsp/categoryList.jsp";
    }

    @RequestMapping(value = "deleteCategory/{cid}")
    public String deleteCategoryById(Model model,
                                     @PathVariable int cid,
                                     HttpServletRequest request){
        try {
            categoryManager.deleteCategoryById(cid);
        }catch (Exception ex){
            String errMsg = ex.getMessage();
            logger.info("error");
            logger.info(errMsg);
            if (errMsg.contains("")){
                logger.info("");
                String cname = categoryManager.getCategoryById(cid).getCname();
                HttpSession session = request.getSession();
                session.setAttribute("errMsg","");

            }
        }
        model.addAttribute("category",categoryManager.getAllCategories());
        return "jsp/categoryList.jsp";

    }

}
