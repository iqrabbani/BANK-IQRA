package com.assesment.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Statement;

@RestController
public class TestController {

    private final DataSource dataSource;

    public TestController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/sqli")
    public String test(@RequestParam String id) throws Exception {
        Statement stmt = dataSource.getConnection().createStatement();
        stmt.executeQuery("SELECT * FROM users WHERE id=" + id);
        return "OK";
    }
}
