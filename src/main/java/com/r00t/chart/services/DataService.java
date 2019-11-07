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

    public DataModel saveData(DataModel dataModel) throws Exception {
        dataModel.setTime(dataModel.getTime().trim());
        if (!_checkDataModel(dataModel))
            throw new Exception("Wrong time or value format");

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

    public void deleteDateWithTime(String time) {
        DataModel temp = findData(time);
        deleteDataWithId(temp.getId());
    }

    public void deleteDataWithId(String id) {
        repository.deleteById(id);
    }

    private boolean _checkDataModel(DataModel dataModel) {
        if (!_checkTime(dataModel.getTime()))
            return false;
        if (dataModel.getValue() == null)
            return false;
        return true;
    }

    private boolean _checkTime(String time) {
        if (time == null)
            return false;
        if (time.trim().isEmpty())
            return false;
        if (time.length() != 10)
            return false;

        String[] text = time.split("-");
        if (text.length != 3)
            return false;
        if (text[0].length() != 4 || text[1].length() != 2 || text[2].length() != 2)
            return false;

        try {
            for (int x = 0; x < 3; x++)
                Integer.valueOf(text[x]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
