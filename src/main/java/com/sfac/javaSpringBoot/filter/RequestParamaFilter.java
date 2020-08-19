package com.sfac.javaSpringBoot.filter;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.persistence.OrderBy;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "RequestParamaFilter",urlPatterns = "/**")//urlPatterns 将所有请求纳入/**
@Order(1)//表示多个过 器先执行  数值越小 越靠前
public class RequestParamaFilter implements Filter {
    private  final static Logger LOGGER= LoggerFactory.getLogger(RequestParamaFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    LOGGER.debug("===================init parama Filter ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      //把ServletRequest强转HttpServletRequest
        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        /* Map<String,String[]> paramaMap= httpServletRequest.getParameterMap();
          paramaMap.put("paramKey",new String[]{"***"});*/
        HttpServletRequestWrapper wrapper=new HttpServletRequestWrapper(httpServletRequest){
              public  String getParameter(String name){
                String value=  httpServletRequest.getParameter(name);
                if (StringUtils.isNotBlank(value)){
                    return value.replaceAll("fuck","***");
                }
                return super.getParameter(name);
              }
            public String[] getParameterValues(String name) {
                String[] values = httpServletRequest.getParameterValues(name);
                if (values != null && values.length > 0) {
                    for (int i = 0; i < values.length; i ++) {
                        values[i] = values[i].replaceAll("fuck", "***");
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };
          filterChain.doFilter(wrapper,servletResponse);
       LOGGER.debug("==================do request parama Filter");

    }

    @Override
    public void destroy() {
     LOGGER.debug("==========================destory request Filter");
    }
}
