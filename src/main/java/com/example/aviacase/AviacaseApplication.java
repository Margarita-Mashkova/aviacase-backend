package com.example.aviacase;

import com.example.aviacase.model.User;
import com.example.aviacase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AviacaseApplication implements CommandLineRunner {
	public static User authenticateUser;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AviacaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setLogin("admin");
		user.setPassword(encoder.encode("admin"));
		user.setName("Админ");
		user.setSurname("Админ");
		if(userRepository.findByLogin("admin")==null) userRepository.save(user);
	}
}
