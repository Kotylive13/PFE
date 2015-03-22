package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import domain.Admin;


public class AuthenticatedAdminInterceptor extends HandlerInterceptorAdapter {
	
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
