package qit.tomcat;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CredentialDigest {
	
	private Logger log = LoggerFactory.getLogger(CredentialDigest.class);
	private String digest;
	private String credential;
	
	public CredentialDigest(String credential) {
		super();
		this.credential = credential;
	}

	public String getDigest() {
		if(digest == null) {
			digest = generateCredentialDigest();
		}
		return digest;
	}
	
	private String generateCredentialDigest() {
		try {
			log.info("generating credential digest using SHA");
			MessageDigest digest = (MessageDigest) MessageDigest.getInstance("SHA").clone();
			digest.update(credential.getBytes("UTF-8"));
			byte[] bytes = digest.digest();
			if(bytes.length > 0) {
				String hex = convertHex(bytes);
				log.info("generated digest:{}", hex);
				return hex;
			}
		} catch (NoSuchAlgorithmException e) {
			log.error("invalid algorithm SHA for message digest", e);
		} catch (CloneNotSupportedException e) {
			log.error("cannot clone message digest", e);
		} catch (UnsupportedEncodingException e) {
			log.error("character encoding UTF-8 not supported", e);
		}
		log.warn("failed to generate digest for credential");
		return null;
	}
	
	private String convertHex(byte[] digits) {
		char[] HEX = "0123456789abcdef".toCharArray();
		StringBuilder sb = new StringBuilder((2 * digits.length));
		for(byte digit : digits) {
			sb.append(HEX[((digit & 0xF0) >> 4)]).append(HEX[((digit & 0x0F))]);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		try {
			if(args.length != 1) {
				System.err.println("USAGE: " + CredentialDigest.class.getName() + " <credential>");
				return;
			}
			String credential = args[0];
			CredentialDigest cd = new CredentialDigest(credential);
			String digest = cd.getDigest();
			System.out.println("DIGEST:" + digest);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
