package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();


    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service查询
        List<Brand> brands = brandService.selectAll();

        //转为JSON
        String jsonString = JSON.toJSONString(brands);

        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service添加
        brandService.add(brand);

        //响应成功的标识
        response.getWriter().write("success");
    }
    public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service修改
        brandService.updateById(brand);

        //响应成功的标识
        response.getWriter().write("success");

    }
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收ID
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转为Int
        Integer id = JSON.parseObject(params, int.class);

        //调用service删除
        brandService.deleteById(id);

        //响应成功的标识
        response.getWriter().write("success");
    }
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收ID数组
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转为Int
        int[] ids = JSON.parseObject(params, int[].class);

        //调用service删除
        brandService.deleteByIds(ids);

        //响应成功的标识
        response.getWriter().write("success");
    }
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收当前页码 每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int parseSize = Integer.parseInt(_pageSize);
        int currentPage = Integer.parseInt(_currentPage);

        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, parseSize);

        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收当前页码 每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int parseSize = Integer.parseInt(_pageSize);
        int currentPage = Integer.parseInt(_currentPage);

        //获取查询条件对象
        //接收ID数组
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转为Brand
        Brand brand = JSON.parseObject(params, Brand.class);

        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, parseSize,brand);

        //转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
