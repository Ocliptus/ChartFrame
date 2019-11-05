package com.r00t.chart.repositories;

import com.r00t.chart.models.DataModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends MongoRepository<DataModel, String> {
    DataModel findByTime(String time);
}
