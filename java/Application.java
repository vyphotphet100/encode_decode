import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.encoding")
//@EnableJpaRepositories("com.encoding.repository")
//@EntityScan("com.window_programming_api.entity")
//@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Application {

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}


}