package com.staroot.h2multi.sqltool.service;

import com.staroot.h2multi.sqltool.entity.DatabaseConnection;
import com.staroot.h2multi.sqltool.repository.DatabaseConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseConnectionService {
    @Autowired
    private DatabaseConnectionRepository repository;

    public List<DatabaseConnection> getAllConnections() {
        return repository.findAll();
    }

    public DatabaseConnection getConnectionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public DatabaseConnection saveConnection(DatabaseConnection connection) {
        return repository.save(connection);
    }

    public void deleteConnection(Long id) {
        repository.deleteById(id);
    }
}
