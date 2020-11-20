package com.hansheng.springboot.filter;

import com.hansheng.springboot.entity.Accounts;
import com.hansheng.springboot.entity.TblPermission;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
public final String URL_ENUM[] = {"/account/login", "/account/validataAccount", "/css/","/js/", "/images","/index","/errorPage", "/account/logOut"};
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("login filter init ......");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        System.out.println(uri);
        //判断URL是否可以通过
        boolean  uriCurrenct = canPassIgnore(uri);
        if(uriCurrenct){
             filterChain.doFilter(req, resp);
        }else {
            /**
             * 获取session，检查是否有登录，如果没登录，返回到登录界面
             */
            Accounts obj = (Accounts) session.getAttribute("account");
            if (obj == null) {
                resp.sendRedirect("/account/login");
                return;
            } else {//允许访问首页
             //   List<TblPermission> list = obj.getPermissions();
                //已登录用户是否有权限访问当前页面
              //  boolean flag = validateUrl(list, uri);
                //if (flag) {
                    filterChain.doFilter(req, resp);
                //} else {
                  //  request.setAttribute("msg", "您无权访问当前页面:" + uri);
                   // request.getRequestDispatcher("/errorPage").forward(request, response);
                    return;

              //  }
            }

        }

    }
        public boolean canPassIgnore(String uri){
         for (String url: URL_ENUM) {
           if(uri.startsWith(url))
             return true;
         }
         return  false;
        }
        public boolean validateUrl(List<TblPermission> list, String url){
            if(list.size()==0)
            return false;
            for (TblPermission tb: list) {
                if(url.indexOf(tb.getUri())>-1){//  url.endsWith(tb.getUri())
                    return true;
                }
            }
            return false;
        }
    @Override
    public void destroy() {

    }
}
