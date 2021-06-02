package com.nicomincuzzi.frameworkless.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {

    public void load() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }

            Properties properties = new Properties();
            properties.load(input);


            System.out.println("------------>" + properties.getProperty("maze.map"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
