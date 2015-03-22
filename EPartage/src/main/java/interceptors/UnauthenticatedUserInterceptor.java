package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import domain.User;

/**
 * Class allowing to check if user is unauthenticated before calling the
 * URL corresponding methods described in the configuration file 
 * "servlet-context.xml".
 * 
 * @author
 *
 */
public class UnauthenticatedUserInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Called before each call to the URL corresponding methods described in
	 * the configuration file "servlet-context.xml".
	 * If user is unauthenticated, he is redirected to the connection page.
	 * 
	 * @return True if user is authenticated, false otherwise
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {

		HttpSession session = request.getSession();
		User user = null;
		if (session != null)
			user = (User) session.getAttribute("userSession");
		
		if (!request.getRequestURI().equals("/EPartage/authentication/connection.htm")) {
			if (user == null) {
				response.sendRedirect("/EPartage/authentication/connection.htm");
				return false;
			}
		}
		return true;
	}
}
