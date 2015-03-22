package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import domain.Admin;
import domain.User;

public class AvatarsAccessInterceptor extends HandlerInterceptorAdapter {

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
