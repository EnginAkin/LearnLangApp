package tr.com.english.learnlang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LearnlangApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(LearnlangApplication.class, args);
	}


}
