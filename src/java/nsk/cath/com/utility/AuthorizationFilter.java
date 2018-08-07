package nsk.cath.com.utility;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrew
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

  public AuthorizationFilter() {
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) 
      throws IOException, ServletException {
    try {
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      HttpServletResponse response = (HttpServletResponse) servletResponse;
      HttpSession ses = request.getSession(false);

      String reqURI = request.getRequestURI();
      if (reqURI.contains("/index.xhtml")
          ||reqURI.contains("/login.xhtml")
          || reqURI.contains("/user_form.xhtml")
          || reqURI.contains("/error_page.xhtml")
          || (ses != null && ses.getAttribute("username") != null)
          || reqURI.contains("/public/")
          || reqURI.contains("javax.faces.resource")) {
        chain.doFilter(request, servletResponse);
      } else {
        response.sendRedirect(request.getContextPath() + "/faces/login.xhtml");
      }
    } catch (IOException | ServletException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void destroy() {

  }
}
