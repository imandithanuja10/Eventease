package klu.model;
import klu.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager { /* created for the purpose of service */
@Autowired
	UserRepository UR;
	
@Autowired
EmailManager EM;



	public String adduser(Users u)
	{
		if(UR.validateEmail(u.getEmail())>0)
			return "401::Email already exist";
		UR.save(u);
		return "200::User registered successfully";
	}
	
	
	public String recoverPassword(String email)
	{
		Users U = UR.findById(email).get();
		String message = String.format("Dear %s \n\n Your password is : %s",U.getFullname(),U.getPassword());
		return EM.sendEmail(U.getEmail(), "Job Portal: Password Recovery",message);
			
		
		
	}
	
	@Autowired
	JWTManager JWT;
	public String validateCredentials(String email, String password)
	{
		if(UR.validateCredentials(email, password) > 0)
		{
			String token = JWT.generateToken(email);
			return "200::"+token;
		}
		return "401::Invalid Credentials";
	}
	

    public String getFullname(String token) {
		String email = JWT.validateToken(token);
		if(email.compareTo("401") == 0)
			return "401::Token Expired!";
		Users U = UR.findById(email).get();
		return U.getFullname();
    }

	
	
}