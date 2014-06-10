package ar.com.frba.utn.tacs.grupocuatro.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ar.com.frba.utn.tacs.grupocuatro.exceptions.BusinessException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
	
	// Total control - setup a model and return the view name
		@ExceptionHandler(BusinessException.class)
		public ModelAndView handleError(HttpServletRequest req, Exception exception)
				throws Exception {

			// Rethrow annotated exceptions or they will be processed here instead.
			if (AnnotationUtils.findAnnotation(exception.getClass(),
					ResponseStatus.class) != null)
				throw exception;

			ModelAndView mav = new ModelAndView();
//			mav.addObject("exception", exception);
//			mav.addObject("url", req.getRequestURL());
			mav.addObject("timestamp", new Date());
//			mav.addObject("status", 500);

			mav.setViewName("error");
			return mav;
		}

}
