package com.staroot.h2multi.sqltool.controller;
import com.staroot.h2multi.sqltool.entity.DatabaseConnection;
import com.staroot.h2multi.sqltool.entity.QueryLog;
import com.staroot.h2multi.sqltool.repository.QueryLogRepository;
import com.staroot.h2multi.sqltool.service.DatabaseConnectionService;
import com.staroot.h2multi.sqltool.service.QueryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/sqltool")
public class QueryController {

    @Autowired
    private final QueryLogService queryLogService;

    @Autowired
    private DatabaseConnectionService service;

    @Autowired
    private QueryLogRepository queryLogRepository;

    public QueryController(QueryLogService queryLogService) {
        this.queryLogService = queryLogService;
    }

    @GetMapping("/logs")
    public List<QueryLog> getQueryLogs() {
        return queryLogService.getQueryLogs();
    }
    //http://localhost:8080/sqltool/sqlapi/1
    @GetMapping("/sqlapi/{id}")
    public List<Map<String, Object>> executeBySavedSql(@PathVariable Long id) {
        Optional<QueryLog> queryLog = Optional.of(new QueryLog());
        queryLog = queryLogService.getQueryLogById(id);
        String queryText = "";
        Long connectionId = null;
        if (queryLog.isPresent()) {
            queryText = queryLog.get().getQueryText();
            connectionId = queryLog.get().getConnectionId();
            System.out.println("Query Text: " + queryText);
        } else {
            System.out.println("QueryLog not found.");
        }
        //String queryResults = "";
        List<Map<String, Object>> queryResults = execute(connectionId, queryText);

        return queryResults;
    }

    @PostMapping("/execute")
    //@ResponseBody
    public List<Map<String, Object>> execute(@RequestParam Long connectionId, @RequestParam String sql) {
        DatabaseConnection connection = service.getConnectionById(connectionId);
        List<Map<String, Object>> result = executeSql(connection, sql);
        // 쿼리 실행 이력 저장
        QueryLog log = new QueryLog();
        log.setQueryText(sql);
        log.setConnectionId(connectionId);
        queryLogRepository.save(log);
        return result;
    }

    private List<Map<String, Object>> executeSql(DatabaseConnection connection, String sql) {
        List<Map<String, Object>> results = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(connection.getUrl(), connection.getUsername(), connection.getPassword());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                results.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}