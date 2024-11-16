package com.staroot.h2multi.sqltool.controller;

import com.staroot.h2multi.sqltool.entity.DatabaseConnection;
import com.staroot.h2multi.sqltool.entity.QueryLog;
import com.staroot.h2multi.sqltool.repository.QueryLogRepository;
import com.staroot.h2multi.sqltool.service.DatabaseConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/sqltool")
public class DatabaseConnectionController {
    @Autowired
    private DatabaseConnectionService service;


    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("connections", service.getAllConnections());
        return "sqltool/index";
    }


    @GetMapping("/connection/{id}")
    public String connection(@PathVariable Long id, Model model) {
        model.addAttribute("connection", service.getConnectionById(id));
        return "sqltool/connection";
    }
    @GetMapping("/new")
    public String newConnection(Model model) {
        model.addAttribute("connection", new DatabaseConnection());
        return "sqltool/newConnection";
    }

    @PostMapping("/save")
    public String saveConnection(@ModelAttribute DatabaseConnection connection) {
        service.saveConnection(connection);
        return "redirect:/sqltool";
    }
    @GetMapping("/testdata")
    public String createTestDataConnection() {
        DatabaseConnection connection = new DatabaseConnection();
        connection.setName("sqltooldb");
        connection.setUrl("jdbc:h2:mem:sqltooldb");
        connection.setDriverClassName("org.h2.Driver");
        connection.setUsername("sa");
        connection.setPassword("");
        service.saveConnection(connection);

        connection = new DatabaseConnection();
        connection.setName("db1");
        connection.setUrl("jdbc:h2:mem:db1");
        connection.setDriverClassName("org.h2.Driver");
        connection.setUsername("sa");
        connection.setPassword("");
        service.saveConnection(connection);

        connection = new DatabaseConnection();
        connection.setName("db2");
        connection.setUrl("jdbc:h2:mem:db2");
        connection.setDriverClassName("org.h2.Driver");
        connection.setUsername("sa");
        connection.setPassword("");
        service.saveConnection(connection);

        connection = new DatabaseConnection();
        connection.setName("db3");
        connection.setUrl("jdbc:h2:mem:db3");
        connection.setDriverClassName("org.h2.Driver");
        connection.setUsername("sa");
        connection.setPassword("");
        service.saveConnection(connection);
        return "redirect:/sqltool";
    }

}
