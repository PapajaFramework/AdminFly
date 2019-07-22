package org.papaja.adminfly.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.papaja.adminfly.data.AvailableLocales;
import org.papaja.adminfly.data.AvailableThemes;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@SuppressWarnings({"unused"})
@ControllerAdvice
public class GlobalController {

    @ExceptionHandler({AccessDeniedException.class})
    public String handleAccessDeniedException(Model model, HttpServletRequest request, Principal principal) {
        model.addAttribute("uri", request.getRequestURI());

        handleRequest(request, model);

        return "errors/accessDenied";
    }

    @ExceptionHandler({Exception.class})
    public String handleException(
            Exception exception, Model model, HttpServletRequest request, HttpServletResponse response, Principal principal
    ) {
        String template = "errors/exception";

        model.addAttribute("stack", ExceptionUtils.getStackTrace(exception));
        model.addAttribute("exceptionClass", exception.getClass().getName());
        model.addAttribute("rootMassage", exception.getMessage());

        handleRequest(request, model);

        return template;
    }

    @ModelAttribute
    public void handleRequest(HttpServletRequest request, Model view) {
        view.addAttribute("locale", LocaleContextHolder.getLocale().toString());
        view.addAttribute("languages", new AvailableLocales());
        view.addAttribute("themes", new AvailableThemes());
        view.addAttribute("principal", request.getUserPrincipal());
    }

}
