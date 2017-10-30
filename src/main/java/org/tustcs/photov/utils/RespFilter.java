package org.tustcs.photov.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by polykickshaw on 17-9-19.
 */
public class RespFilter implements Filter {

    private String defaultCharset = "utf-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //设置请求，和响应的格式
        request.setCharacterEncoding(defaultCharset);//request.setCharacterEncoding（）是设置从request中取得的值或从数据库中取出的值
        response.setCharacterEncoding(defaultCharset);

        response.setContentType("text/html;charset="+defaultCharset);
        //是设置页面中为中文编码前者是设置动态文字（参数，数据库），后者设置页面静态文字
        response.addHeader("Access-Control-Allow-Origin","*");
        //表明它允许“http://xxx”发起跨域请求

//        if(!TokenUntils.isLegal(request,response)){
//            System.out.println("not legal");
//            PrintWriter out = response.getWriter();
//            out.print(new JSObject().put("status",-1));
//            out.close();
//            return;
//        }
//
//        System.out.println("legal");
//        response.addHeader("auth",new JSObject().put("status",1).put("tokenData", TokenUntils.getToken(request,response)).toString());
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
