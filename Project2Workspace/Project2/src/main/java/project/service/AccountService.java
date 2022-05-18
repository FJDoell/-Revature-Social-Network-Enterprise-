package project.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import project.dao.AccountDao;
import project.model.AccountModel;

@Service
@NoArgsConstructor
public class AccountService {

	private AccountDao dao;
	HttpSession sess;
	Pbkdf2PasswordEncoder encoder;
	

	@Autowired
	public AccountService(AccountDao dao, Pbkdf2PasswordEncoder encoder, HttpSession sess) {
		super();
		this.dao = dao;
		this.encoder = encoder;
		this.sess = sess;

	}

	public AccountModel getAccountByUsername(String user) {
		return dao.findByUsername(user);
	}

	public void registerAccount(String username, String password, String firstName, String lastName, String email) {


		String securePassword = encoder.encode(password);

		AccountModel newUser = new AccountModel(username, securePassword, firstName, lastName, email);

		dao.save(newUser);
		sess.setAttribute("currentUser", newUser);
		

	}

	// Takes in the entered email and finds the account associated, then sets the
	// new password accordingly.
	public void updatePassword(String email, String tempPassword, String password) {

		AccountModel resetPasswordUser = dao.findByEmail(email);
		String encodedPassword = resetPasswordUser.getPassword();

		if (encoder.matches(tempPassword, encodedPassword)) {
			String securePassword = encoder.encode(password);

			resetPasswordUser.setPassword(securePassword);
			dao.save(resetPasswordUser);
		} else {
			System.out.println("Reseting password failed");
		}

	}
	
	
	public void updateImage(int id, String url) {
		AccountModel account = dao.getById(id);
		account.setAccountImage(url);
		dao.save(account);
	}
	
	
	public AccountModel updateProfile (AccountModel User, AccountModel currentUser) {

        System.out.println(User);
        if (!(User.getFirstName()=="")) {
            System.out.println("changing fn");
            currentUser.setFirstName((User.getFirstName()));
         
        } 

        if (!(User.getLastName()=="")) {
            System.out.println("changing ln");
            currentUser.setLastName((User.getLastName()));
            
        } 


        dao.save(currentUser);
        return currentUser;
    }

	public void updateBending (String bendingStyle) {
		
		AccountModel addBending = (AccountModel) sess.getAttribute("currentUser");
		addBending.setBendingType(bendingStyle);
		System.out.println(addBending);
		
		dao.save(addBending);
		
	}

}
