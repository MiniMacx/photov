package org.tustcs.photov.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author L.key.
 * @Date 2017/7/24 19:52
 */
public class TokenFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (!TokenUtils.illegal(httpServletRequest,httpServletResponse))
            return;
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    public void destroy() {

    }
}
