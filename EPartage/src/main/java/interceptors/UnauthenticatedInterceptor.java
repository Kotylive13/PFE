package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import domain.User;

/**
 * Classe permettant de vérifier si l'utilisateur est authentifié
 * avant l'appel des méthodes correspondantes aux URL décritent 
 * dans le fichier de configuration "springapp-servlet.xml".
 * 
 * @author Quentin Cheynet & Yoann Moisset
 *
 */
public class UnauthenticatedInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Appelée avant chaque appel des méthodes correspondantes aux URL décritent 
	 * dans le fichier de configuration "springapp-servlet.xml".
	 * Si l'utilisateur n'est pas authentifié, il est redirigé vers le
	 * le formulaire d'authentification.
	 * 
	 * @return True si l'utilisateur est authentifié, false sinon.
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
