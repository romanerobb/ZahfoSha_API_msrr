package com.revature.zahfosha.menu;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.revature.zahfosha.util.interfaces.Headable.addHeads;

public class MenuServlet extends HttpServlet {

    private final MenuDao mDao;
    private final ObjectMapper mapper;

    public MenuServlet(MenuDao mDao, ObjectMapper mapper) {
        this.mDao = mDao;
        this.mapper = mapper;
    }

//    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doOptions(req, resp);
//        addHeads(req, resp);
//    }

    //CREATE
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        addHeads(req, resp);
        MenuDTO pass = mapper.readValue(req.getInputStream(), MenuDTO.class);

        MenuModel addedItem = mDao.createMenu(pass.getMenuItem(), pass.getCost(), pass.getProtein(), pass.getIsSubstitutable());
        MenuModel theItemObject = mDao.followUpCreateMenu(pass.getMenuItem());

        String payload = mapper.writeValueAsString(theItemObject);

        resp.getWriter().write("Added the new menu item, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

    //UPDATE
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        addHeads(req, resp);
    }

    //DELETE
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        addHeads(req, resp);
    }

    //READ
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        addHeads(req, resp);
        MenuModel[] items = mDao.findAllMenuItems();

        String payload = mapper.writeValueAsString(items);

        resp.getWriter().write("Menu items populated, as seen below \n");
        resp.getWriter().write(payload);
        resp.setStatus(201);

    }

}
