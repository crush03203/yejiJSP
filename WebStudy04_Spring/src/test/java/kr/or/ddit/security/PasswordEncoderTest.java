package kr.or.ddit.security;

import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class PasswordEncoderTest {
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	
	String password = "java"; //클라이언트 저장된 암호
	String mem_pass = "{bcrypt}$2a$10$8k3myotvQumiCynzvwJDru4qhXxc7cEZ5jEPU0BTOQxNRsfDA1KUC";
	
	public void encodeTest() {
		String encoded = encoder.encode(password);
		log.info("mem_pass:{}" , encoded);
	}
	
	@Test
	public void matchTest() {
		log.info("match result : {}",encoder.matches(password, mem_pass));
		
		
	}
}
