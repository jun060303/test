package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Sha256Util {

	public static String getEncrypt(String source, String salt) {
		return getEncrypt(source, salt.getBytes());
	}
	
	public static String getEncrypt(String source, byte[] salt) {
		String resultValue = "";
		
		byte[] src = source.getBytes();
		byte[] bytes = new byte[src.length + salt.length];
		
		System.arraycopy(src, 0, bytes, 0, src.length);
		System.arraycopy(salt, 0, bytes, src.length, salt.length);
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytes);
			byte[] byteData = md.digest();	//해시 결과 가져오기
			
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
			}
			resultValue = sb.toString();	//결과			
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return resultValue;
	}
	
	public static String genSalt() {
		Random random = new Random();
		
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		//System.out.println("salt:" + salt);
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < salt.length; i++) {
			sb.append(String.format("%02x", salt[i]));
		}
//		System.out.println("salt ret :" + sb.toString());
		return sb.toString();
	}
}
