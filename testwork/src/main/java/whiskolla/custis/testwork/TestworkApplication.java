package whiskolla.custis.testwork;

import org.slf4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@SpringBootApplication
public class TestworkApplication {
    static Logger logger = LoggerFactory.getLogger(TestworkApplication.class);
    private static final LocalDateTime startTime = LocalDateTime.of(2024,10,06,10,00);
    private static final LocalDateTime endTime = LocalDateTime.of(2024,10,06,17,00);
    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        if (currentTime.isBefore(startTime) || currentTime.isAfter(endTime)) {
            logger.info("Ошибка. Окно: [" + startTime + " - " + endTime + "]. Сейчас - " + currentTime);
        } else {
            SpringApplication.run(TestworkApplication.class, args);
        }
    }
}
