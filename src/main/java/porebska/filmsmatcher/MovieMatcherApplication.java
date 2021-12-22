package porebska.filmsmatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import porebska.filmsmatcher.repository.UserRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class MovieMatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieMatcherApplication.class, args);
	}

}
