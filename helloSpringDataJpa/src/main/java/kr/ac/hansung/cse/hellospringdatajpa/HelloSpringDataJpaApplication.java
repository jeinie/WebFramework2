package kr.ac.hansung.cse.hellospringdatajpa;

import ch.qos.logback.classic.Level;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class HelloSpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringDataJpaApplication.class, args);

        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("kr.ac.hansung.cse.hellospringdatajpa");
        logger.setLevel(Level.INFO);

        // This request is enabled, because WARN >= INFO
        logger.warn("Low fuel level.");

        // This request is disabled, because DEBUG < INFO
        logger.debug("Starting search for nearest gas station.");

    }

}
