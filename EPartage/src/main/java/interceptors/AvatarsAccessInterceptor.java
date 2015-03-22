package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import domain.Admin;
import domain.User;

/**
 * Class allowing to check if administrator or user is authenticated before 
 * calling the URL corresponding methods described in the configuration 
 * file "servlet-context.xml".
 * 
 * @author
 *
 */
public class AvatarsAccessInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Called before each call to the URL corresponding methods described in
	 * the configuration file "servlet-context.xml".
	 * If administrator and user are unauthenticated, they are redirected to
	 * the user connection page.
	 * 
	 * @return True if administrator or user is authenticated, false otherwise
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {

		HttpSession session = request.getSession();
		User user = null;
		Admin admin = null;
		
		if (session != null) {
			user = (User) session.getAttribute("userSession");
			admin = (Admin) session.getAttribute("adminSession");
		}
		
		if (!request.getRequestURI().equals("/EPartage/authentication/connection.htm")) {
			if (user == null && admin == null) {
				response.sendRedirect("/EPartage/authentication/connection.htm");
				return false;
			}
		}
		return true;
	}
}
