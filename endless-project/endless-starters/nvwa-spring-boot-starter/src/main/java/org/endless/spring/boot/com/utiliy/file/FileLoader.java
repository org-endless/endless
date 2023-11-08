package org.endless.spring.boot.com.utiliy.file;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.FileSystemResource;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * FileLoader
 *
 * @author Deng Haozhi
 * @date 2023/3/19 6:40
 * @since 0.0.1
 */
@Log4j2
public class FileLoader {
    public static Scanner getScanner(String file, Pattern pattern) {
        try {
            Scanner scanner = new Scanner(new FileSystemResource(file).getInputStream());
            scanner.useDelimiter(pattern);
            return scanner;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
