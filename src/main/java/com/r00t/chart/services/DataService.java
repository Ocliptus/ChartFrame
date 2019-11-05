package com.r00t.chart.services;

import com.r00t.chart.models.DataModel;
import com.r00t.chart.repositories.DataRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    private DataRepository repository;

    public DataService(DataRepository repository) {
        this.repository = repository;
    }

    public DataModel findData(String time) {
        return repository.findByTime(time);
    }

    public DataModel saveData(DataModel dataModel) {
        DataModel temp = findData(dataModel.getTime());
        if (temp == null)
            return repository.insert(dataModel);
        else {
            temp.setValue(dataModel.getValue());
            return repository.save(temp);
        }
    }

    public List<DataModel> getAllData() {
        return repository.findAll(
                Sort.by(Sort.Direction.ASC, "time"));
    }

    public void deleteDateWithDate(String time) {
        DataModel temp = findData(time);
        deleteDataWithId(temp.getId());
    }

    public void deleteDataWithId(String id) {
        repository.deleteById(id);
    }
}
