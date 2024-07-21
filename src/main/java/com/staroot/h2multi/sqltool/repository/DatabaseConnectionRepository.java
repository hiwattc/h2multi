package com.staroot.h2multi.sqltool.repository;

import com.staroot.h2multi.sqltool.entity.DatabaseConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseConnectionRepository extends JpaRepository<DatabaseConnection, Long> {
}
