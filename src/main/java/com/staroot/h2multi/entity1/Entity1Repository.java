package com.staroot.h2multi.entity1;

import com.staroot.h2multi.entity1.Entity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Entity1Repository extends JpaRepository<Entity1, Long> {
}
