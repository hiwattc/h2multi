package com.staroot.h2multi.sqltool.repository;

import com.staroot.h2multi.sqltool.entity.QueryLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryLogRepository extends JpaRepository<QueryLog, Long> {
}
