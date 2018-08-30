package com.example.demo.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginIntercepter extends HandlerInterceptorAdapter{
	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        // �꽭�뀡 媛앹껜 �깮�꽦
	        HttpSession session = request.getSession();
	        // �꽭�뀡�뿉 id媛� null�씠硫�
	        if(session.getAttribute("userId") == null) {
	            // 濡쒓렇�씤 �럹�씠吏�濡� �씠�룞
	            response.sendRedirect(request.getContextPath()+"/member/login");
	            // 而⑦듃濡ㅻ윭瑜� �떎�뻾�븯吏� �븡�뒗�떎.(�슂泥��럹�씠吏�濡� �씠�룞�븯吏� �븡�뒗�떎)
	            return false;
	        // null�씠 �븘�땲硫�
	        } else {
	            // 而⑦듃濡ㅻ윭瑜� �떎�뻾(�슂泥��럹�씠吏�濡� �씠�룞�븳�떎.)
	            return true;
	        }
	    }
	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	        super.postHandle(request, response, handler, modelAndView);
	    }
}
