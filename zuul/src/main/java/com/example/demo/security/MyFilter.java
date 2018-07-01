package com.example.demo.security;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    /**
     * return a string to indicate the type of the filter
     * (four types in zuul:
     * pre: before routing
     * routing: when routing
     * post: after routing
     * error: when error
     **/
    @Override
    public String filterType(){
        return "pre";
    }

    /**
     *
     * the order of the filter
     */
    @Override
    public int filterOrder(){
        return 0;
    }

    /**
     *
     * decide when to filter(if true,take filter forever )
     */
    @Override
    public boolean shouldFilter(){
        return true;
    }

    /**
     *
     * the content of the filter
     */
    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s",request.getMethod(),request.getRequestURI().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null){
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try{
                ctx.getResponse().getWriter().write("token is empty");
            }catch(Exception e){}
            return null;
        }
        log.info("OK");
        return null;
    }
}
