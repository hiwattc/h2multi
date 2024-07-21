package com.staroot.h2multi;

import com.staroot.h2multi.entity1.Entity1;
import com.staroot.h2multi.entity1.Entity1Repository;
import com.staroot.h2multi.entity2.Entity2;
import com.staroot.h2multi.entity2.Entity2Repository;
import com.staroot.h2multi.entity3.Entity3;
import com.staroot.h2multi.entity3.Entity3Repository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleDataService {

    @Autowired
    private Entity1Repository entity1Repository;
    @Autowired
    private Entity2Repository entity2Repository;
    @Autowired
    private Entity3Repository entity3Repository;

    @PostConstruct
    public void init() {
        // 첫 번째 데이터 소스에 샘플 데이터 입력
        Entity1 entity1 = new Entity1();
        entity1.setName("Sample Entity 1-1");
        entity1Repository.save(entity1);

        // 두 번째 데이터 소스에 샘플 데이터 입력
        Entity2 entity2 = new Entity2();
        entity2.setDescription("Sample Entity 2-1");
        entity2Repository.save(entity2);

        // 두 번째 데이터 소스에 샘플 데이터 입력
        Entity3 entity3 = new Entity3();
        entity3.setDescription("Sample Entity 3-1");
        entity3Repository.save(entity3);
    }
}
