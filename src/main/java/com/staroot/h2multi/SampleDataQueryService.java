package com.staroot.h2multi;

import com.staroot.h2multi.entity1.Entity1;
import com.staroot.h2multi.entity1.Entity1Repository;
import com.staroot.h2multi.entity2.Entity2;
import com.staroot.h2multi.entity2.Entity2Repository;
import com.staroot.h2multi.entity3.Entity3;
import com.staroot.h2multi.entity3.Entity3Repository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

@Service
public class SampleDataQueryService {

    private final Entity1Repository entity1Repository;
    private final Entity2Repository entity2Repository;
    private final Entity3Repository entity3Repository;

    @Autowired
    public JpaTransactionManager transactionManager1;
    @Autowired
    public JpaTransactionManager transactionManager2;
    @Autowired
    public JpaTransactionManager transactionManager3;

    public SampleDataQueryService(Entity1Repository entity1Repository, Entity2Repository entity2Repository, Entity3Repository entity3Repository) {
        this.entity1Repository = entity1Repository;
        this.entity2Repository = entity2Repository;
        this.entity3Repository = entity3Repository;
    }


    public void queryData() {
        try{
            Entity1 e1 = new Entity1();
            e1.setName("Sample Entity 1-2");
            entity1Repository.save(e1);

            Entity2 e2 = new Entity2();
            e2.setDescription("Sample Entity 2-2");
            entity2Repository.save(e2);

            Entity3 e3 = new Entity3();
            e3.setDescription("Sample Entity 3-2");
            entity3Repository.save(e3);
            //throw new Exception();

        }catch(Exception e){
            // 예외 발생 시 트랜잭션 롤백
            //e.printStackTrace();

        }
        // 첫 번째 데이터 소스에서 데이터 조회
        List<Entity1> entity1List = entity1Repository.findAll();
        System.out.println("Entities from DataSource 1:");
        entity1List.forEach(entity1 -> System.out.println("ID: " + entity1.getId() + ", Name: " + entity1.getName()));

        // 두 번째 데이터 소스에서 데이터 조회
        List<Entity2> entity2List = entity2Repository.findAll();
        System.out.println("Entities from DataSource 2:");
        entity2List.forEach(entity2 -> System.out.println("ID: " + entity2.getId() + ", Description: " + entity2.getDescription()));

        // 세 번째 데이터 소스에서 데이터 조회
        List<Entity3> entity3List = entity3Repository.findAll();
        System.out.println("Entities from DataSource 3:");
        entity3List.forEach(entity3 -> System.out.println("ID: " + entity3.getId() + ", Description: " + entity3.getDescription()));

    }
}
