package spring;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Member{
	private Long id;
	private String email;
	private String password;
	private String name;
	private Date registerDate;
	
	public Member(String email, String password, String name, Date registerDate){
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDate = registerDate;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	
	//암호변경 기능을 구현하는 메서드
	public void changePassword(String oldPassword, String newPassword){
//		if(!password.equals(oldPassword))
//			throw new IdPasswordMatchingException();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(newPassword);
		if(!encoder.matches(oldPassword, password)) {
			throw new IdPasswordMatchingException();
		}
		this.password = newPassword;
	}
}