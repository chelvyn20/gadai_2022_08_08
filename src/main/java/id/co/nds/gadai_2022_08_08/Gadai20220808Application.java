package id.co.nds.gadai_2022_08_08;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import id.co.nds.gadai_2022_08_08.schedules.ActivityScheduler;

@SpringBootApplication
@EnableScheduling
public class Gadai20220808Application {

	static final Logger logger = LogManager.getLogger(Gadai20220808Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Gadai20220808Application.class, args);
	}
	
	@PreDestroy
	public void destroy(){
		logger.info("");
		logger.info("Stopping Configuration for System...");
		logger.info("Stopping custom DB Scheduler.....");
		ActivityScheduler.shutdownScheduler();
		logger.info("");
		logger.info("Finish Stopping Configuration for System....");
	}
}
