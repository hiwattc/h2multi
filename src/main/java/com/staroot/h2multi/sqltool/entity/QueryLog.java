package com.staroot.h2multi.sqltool.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "query_log")
public class QueryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long connectionId;

    @Column(name = "query_text", nullable = false)
    private String queryText;

    @Column(name = "execution_time", nullable = false)
    private LocalDateTime executionTime;

    @Column(name = "execution_result")
    private String executionResult;

    public QueryLog() {
        this.executionTime = LocalDateTime.now();
    }
}