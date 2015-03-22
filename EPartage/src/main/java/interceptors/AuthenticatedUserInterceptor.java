package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import domain.User;

/**
 * Class allowing to check if user is authenticated before calling the
 * URL corresponding methods described in the configuration file 
 * "servlet-context.xml".
 * 
 * @author
 *
 */
public class AuthenticatedUserInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * Called before each call to the URL corresponding methods described in
	 * the configuration file "servlet-context.xml".
	 * If user is authenticated, he is redirected to his workspace page.
	 * 
	 * @return True if user is unauthenticated, false otherwise
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {

		HttpSession session = request.getSession();
		User user = null;
		if (session != null)
			user = (User) session.getAttribute("userSession");
		
		if (!request.getRequestURI().equals("/EPartage/workspace/index.htm")) {
			if (user != null) {
				response.sendRedirect("/EPartage/workspace/index.htm");
				return false;
			}
		}
		return true;
	}
}
