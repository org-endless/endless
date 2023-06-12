package org.endless.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Indexed;

/**
 * erp应用程序
 *
 * <p>create 2023/05/25 01:03
 *
 * @author Deng Haozhi
 * @since 0.0.1
 */
@Indexed
@SpringBootApplication
public class EndlessErpApplication {

  public static void main(String[] args) {
    SpringApplication.run(EndlessErpApplication.class, args);
  }
}
