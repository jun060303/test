package spring;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import encrypt.Sha256Util;

public class ChangePasswordService {
	private MemberDao memberDao;
	
	public ChangePasswordService(MemberDao memberDao){
		this.memberDao = memberDao;
	}
	
	private PasswordEncoder encoder;
	public void setPasswordEncoder(PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd){
		Member member = memberDao.selectByEmail(email);
		if(member == null){
			throw new MemberNotFoundException();
		}
//		String dbPass = member.getPassword(); //해시값$솔트
//		String oldSalt = dbPass.split("\\$")[1];
//		
//		//사용자가 입력한 평문을 위에서 가져온 salt값으로 해싱
//		StringBuffer eop = new StringBuffer();
//		eop.append(Sha256Util.getEncrypt(oldPwd, oldSalt));	
//		eop.append("\\$").append(oldSalt);	//기존 비밀번호와 비교할 값
//		oldPwd = eop.toString();
//
//		//사용자가 입력한 새로운 비밀번호를 해싱
//		StringBuffer encryptPassword = new StringBuffer();
//		String newSalt = Sha256Util.genSalt();		
//		encryptPassword.append(Sha256Util.getEncrypt(newPwd, newSalt));
//		encryptPassword.append("\\$").append(newSalt);
//		newPwd = encryptPassword.toString();
//		System.out.println("기존 비밀번호(db):" + member.getPassword());
//		System.out.println("기존 비밀번호(u):" + oldPwd);
//		System.out.println("새로운 비밀번호 : " + newPwd);
		
		newPwd = encoder.encode(newPwd);
		
		member.changePassword(oldPwd,  newPwd);
		memberDao.update(member);
	}
}
