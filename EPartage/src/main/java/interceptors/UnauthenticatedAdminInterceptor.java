package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import domain.Admin;

/**
 * Class allowing to check if administrator is unauthenticated before 
 * calling the URL corresponding methods described in the configuration 
 * file "servlet-context.xml".
 * 
 * @author
 *
 */
public class UnauthenticatedAdminInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * Called before each call to the URL corresponding methods described in
	 * the configuration file "servlet-context.xml".
	 * If administrator is unauthenticated, he is redirected to the connection page.
	 * 
	 * @return True if administrator is authenticated, false otherwise
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {

		HttpSession session = request.getSession();
		Admin admin = null;
		if (session != null)
			admin = (Admin) session.getAttribute("adminSession");
		
		if (!request.getRequestURI().equals("/EPartage/login_staff/authentication/connection.htm")) {
			if (admin == null) {
				response.sendRedirect("/EPartage/login_staff/authentication/connection.htm");
				return false;
			}
		}
		return true;
	}
}
