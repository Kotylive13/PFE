package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import domain.Admin;

public class GlobalExceptionHandler implements HandlerExceptionResolver {

	@ExceptionHandler(Throwable.class)
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {

		ModelAndView result = new ModelAndView("redirect:/workspace/index.htm");

		Admin admin = (Admin) request.getSession().getAttribute("adminSession");

		if (admin != null) {
			result = new ModelAndView("redirect:/login_staff/index.htm"); //?login="+ admin.getLogin() + "&password="+ password);
		}

		if (exception instanceof MaxUploadSizeExceededException) {
			result.addObject("type", "error");
			result.addObject("message",
					"La taille du fichier joint ne doit pas dépasser 5 Mo !");
		} else {
			result.addObject("type", "error");
			result.addObject("message", "Oops ! Une erreur s'est produite !");
			System.out.println("Coucou");
		}
		return result;
	}
}
