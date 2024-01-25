package com.jeipz.etl.service;

import com.jeipz.etl.model.Person;
import com.jeipz.etl.model.dto.CsvData;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    List<Person> transformCsvContent(List<CsvData> csvDataList);

    void loadContent(List<Person> personList);
}
