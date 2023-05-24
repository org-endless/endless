package org.endless.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;



import java.io.*;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Demo {
    public static void main(String[] args)  {
        var name = new Name();

        name.getEnName().setFirstName("123");

        System.out.println(name.getEnName().getFirstName());



    }

    public void test() throws UnsupportedEncodingException {

        String s = "\u6CE8\u518C";

        System.out.println("String:  " + URLDecoder.decode(s, "utf-8"));
    }


    public void test1(){
        Pattern pattern = Pattern.compile("^\\d{1,50}:$",Pattern.MULTILINE);
        InputStream stream = Demo.class.getClassLoader()
                .getResourceAsStream("categoryIDs.yaml");
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter(pattern);
        Yaml yaml = new Yaml();
        Map<String,Object> rat;
        Map<?, ?> temp;
        while (scanner.hasNext()) {
            // scanner.findInLine(pattern)+
            rat = yaml.load(scanner.next());
            temp = (Map<?, ?>) rat.get("name");
            System.out.println(temp);
        }
    }

    public void test2() throws  JsonProcessingException {
        Pattern pattern = Pattern.compile("^\\d{1,50}:$",Pattern.MULTILINE);
        ObjectMapper mapper = new ObjectMapper();
        Yaml yaml = new Yaml();
        Map<String,Object> rat;
        InputStream stream = Demo.class.getClassLoader()
                .getResourceAsStream("test.yaml");
        Scanner scanner = new Scanner(stream);
        scanner.useDelimiter(pattern);
        while (scanner.hasNext()) {
            rat = yaml.load(scanner.findInLine(pattern)+scanner.next());
            System.out.println(mapper.writeValueAsString(rat));
            System.out.println("--------------------------------------");
        }
    }
    public Map<String,Object> convert(Object obj) {

        Map<String,Object> map=new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (obj instanceof Class<?>)
                field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}