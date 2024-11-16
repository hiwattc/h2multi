package com.staroot.h2multi.sqltool.service;

import com.staroot.h2multi.sqltool.entity.QueryLog;
import com.staroot.h2multi.sqltool.repository.QueryLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueryLogService {
    private final QueryLogRepository queryLogRepository;

    public QueryLogService(QueryLogRepository queryLogRepository) {
        this.queryLogRepository = queryLogRepository;
    }

    public List<QueryLog> getQueryLogs() {
        return queryLogRepository.findAll();
    }
    public Optional<QueryLog> getQueryLogById(Long id) {
        return queryLogRepository.findById(id);
    }
}
