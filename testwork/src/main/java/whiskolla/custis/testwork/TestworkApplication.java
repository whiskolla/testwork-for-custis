package whiskolla.custis.testwork;

import org.slf4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;

@SpringBootApplication
public class TestworkApplication {
    static Logger logger = LoggerFactory.getLogger(TestworkApplication.class);
    private static final LocalTime startTime = LocalTime.of(9, 0);
    private static final LocalTime endTime = LocalTime.of(15, 0);
    public static void main(String[] args) {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isBefore(startTime) || currentTime.isAfter(endTime)) {
            logger.info("Ошибка. Окно - " + startTime + " - " + endTime + ". Сейчас - " + currentTime);
        } else {
            SpringApplication.run(TestworkApplication.class, args);
        }
    }
}
