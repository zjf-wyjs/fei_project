

package plus.feifei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author haha
 */
@SpringBootApplication
public class ApplicationLauncher {

	public static void main(String[] args) {
		System.out.println("测试");
		SpringApplication.run(ApplicationLauncher.class, args);
	}

}
