package com.example.manage_platform.utils;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *   获取session 2019年7月8日11:21:44
 *   RequestContextHolder类中有两个ThreadLocal保存当前线程下的request，
 *   每次我们调用getRequestAttributes()的时候就会获取到当前的request，
 *   调用springmvc方法的时候没有传入request，那么这个request是在哪里获取到的呢？(官网：www.fhadmin.org) 看源码可以知道，
 *   是在一个processRequest(HttpServletRequest request,HttpServletResponse response)这个方法里面，
 *   每次挑用doget（），dopost（）的时候都会条用这个方法，将我们的request，response传进去。
 */
public class SessionUtil {
    public static Integer getRoleId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.getAttribute("user");
        return 1;
    }
}
