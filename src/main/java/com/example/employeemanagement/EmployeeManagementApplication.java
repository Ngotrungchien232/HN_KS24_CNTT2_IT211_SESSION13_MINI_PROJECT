package com.example.employeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementApplication {

    public static void main(String[] args) {
        // Dat config bang code de dam bao luon duoc ap dung
        System.setProperty("spring.datasource.url",
                "jdbc:h2:mem:employeedb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        System.setProperty("spring.datasource.driver-class-name", "org.h2.Driver");
        System.setProperty("spring.datasource.username", "sa");
        System.setProperty("spring.datasource.password", "");
        System.setProperty("spring.jpa.database-platform",
                "org.hibernate.dialect.H2Dialect");
        System.setProperty("spring.jpa.hibernate.ddl-auto", "create-drop");
        System.setProperty("spring.jpa.defer-datasource-initialization", "true");
        System.setProperty("spring.sql.init.mode", "always");
        System.setProperty("spring.h2.console.enabled", "true");
        System.setProperty("spring.h2.console.path", "/h2-console");
        System.setProperty("spring.jpa.open-in-view", "false");

        SpringApplication.run(EmployeeManagementApplication.class, args);
    }
}
