package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import domain.Admin;

/**
 * Class allowing to check if administrator is authenticated before 
 * calling the URL corresponding methods described in the configuration 
 * file "servlet-context.xml".
 * 
 * @author
 *
 */
public class AuthenticatedAdminInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * Called before each call to the URL corresponding methods described in
	 * the configuration file "servlet-context.xml".
	 * If administrator is authenticated, he is redirected to his workspace page.
	 * 
	 * @return True if administrator is unauthenticated, false otherwise
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {

		HttpSession session = request.getSession();
		Admin admin = null;
		if (session != null)
			admin = (Admin) session.getAttribute("adminSession");
		
		if (!request.getRequestURI().equals("/EPartage/login_staff/index.htm")) {
			if (admin != null) {
				response.sendRedirect("/EPartage/login_staff/index.htm");
				return false;
			}
		}
		return true;
	}
}
