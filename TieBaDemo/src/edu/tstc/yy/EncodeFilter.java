package edu.tstc.yy;

/**
 * Created by w_2 on 2016-09-06.
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 过滤请求编码的Filter
 * @author zhangjp 2015-12-04 10:33
 */
public class EncodeFilter implements Filter {

    private FilterConfig config;

    @Override
    public void destroy() {
        this.config = null;
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;
        request.setCharacterEncoding(config.getInitParameter("encoding"));
        arg2.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        this.config = arg0;
    }
}