package org.tustcs.photov.utils;






import org.tustcs.photov.config.Config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by L.key on 2017/6/2.
 */
public class EncodeFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        httpServletRequest.setCharacterEncoding(Config.CHARSET);

        httpServletResponse.setCharacterEncoding(Config.CHARSET);

        httpServletResponse.setContentType("text/html;charset="+Config.CHARSET);

        filterChain.doFilter(httpServletRequest,httpServletResponse);
}

    public void destroy() {

    }
}
