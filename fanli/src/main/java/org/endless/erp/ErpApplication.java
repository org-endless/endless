package org.endless.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * erp应用程序
 *
 * <p>create 2023/05/25 01:03
 *
 * @author Deng Haozhi
 * @since 0.0.1
 */

@EnableAsync
@SpringBootApplication
public class ErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
    }
}
