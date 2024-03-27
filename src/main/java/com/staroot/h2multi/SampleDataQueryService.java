package com.staroot.h2multi;

import com.staroot.h2multi.entity1.Entity1;
import com.staroot.h2multi.entity1.Entity1Repository;
import com.staroot.h2multi.entity2.Entity2;
import com.staroot.h2multi.entity2.Entity2Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleDataQueryService {

    private final Entity1Repository entity1Repository;
    private final Entity2Repository entity2Repository;

    public SampleDataQueryService(Entity1Repository entity1Repository, Entity2Repository entity2Repository) {
        this.entity1Repository = entity1Repository;
        this.entity2Repository = entity2Repository;
    }

    public void queryData() {
        // 첫 번째 데이터 소스에서 데이터 조회
        List<Entity1> entity1List = entity1Repository.findAll();
        System.out.println("Entities from DataSource 1:");
        entity1List.forEach(entity1 -> System.out.println("ID: " + entity1.getId() + ", Name: " + entity1.getName()));

        // 두 번째 데이터 소스에서 데이터 조회
        List<Entity2> entity2List = entity2Repository.findAll();
        System.out.println("Entities from DataSource 2:");
        entity2List.forEach(entity2 -> System.out.println("ID: " + entity2.getId() + ", Description: " + entity2.getDescription()));
    }
}
