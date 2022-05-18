package project.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import project.service.AccountService;
import project.service.EmailService;


@Controller
public class PasswordResetController {

	/// Fields
	private EmailService emailService;
	private AccountService accService;

	/// Constructors
	// No Args
	public PasswordResetController() {

	}

	// All Args
	@Autowired
	public PasswordResetController(EmailService emailService, AccountService accService) {
		super();
		this.emailService = emailService;
		this.accService = accService;
	}

	// Used to take in their inputted email and send them the email with temporary password
	@PostMapping(value = "/resetpasswordemail")
	public String resetPasswordEmail(HttpServletRequest req, HttpServletResponse resp) {

		String email = req.getParameter("email");
		System.out.println(email);
		
		emailService.sendMessage(email);
	
		return "redirect:/html/reset-password.html";
		
	}
		// Then maybe send them to a separate html page where they will have to enter the temp password we make
		// 	then their new password and a confirmation field
		// We could technically store the email in the session but I would rather have them re-input their email for security sake
	@PostMapping(value="/resetpassword")
	public String resetPassword(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String tempPassword = req.getParameter("tempPassword");
		String password = req.getParameter("password");
		
		accService.updatePassword(email, tempPassword, password);
		
		return "redirect:/html/login.html";
	}
	

}
