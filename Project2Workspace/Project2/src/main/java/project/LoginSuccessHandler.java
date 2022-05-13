package project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import project.model.AccountModel;
import project.service.AccountService;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private RedirectStrategy redirect;
	
	private String homePage = "/html/home.html";
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String username = authentication.getName();
		AccountModel user = service.getAccountByUsername(username);
		request.getSession().setAttribute("currentUser", user);
		redirect.sendRedirect(request, response, homePage);
		clearAuthenticationAttributes(request);
		
	}
	
}