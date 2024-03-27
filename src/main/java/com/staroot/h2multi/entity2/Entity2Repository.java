package com.staroot.h2multi.entity2;

import com.staroot.h2multi.entity1.Entity1;
import com.staroot.h2multi.entity2.Entity2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface Entity2Repository extends JpaRepository<Entity2, Long> {
}
