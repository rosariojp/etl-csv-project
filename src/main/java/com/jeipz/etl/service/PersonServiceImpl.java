package com.jeipz.etl.service;

import com.jeipz.etl.model.Person;
import com.jeipz.etl.model.dto.CsvData;
import com.jeipz.etl.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> transformCsvContent(List<CsvData> csvDataList) {
        return csvDataList.stream()
                .map(csvData -> Person.builder()
                        .name(formatPersonName(csvData))
                        .birthday(csvData.getBirthday())
                        .build())
                .toList();
    }

    @Override
    public void loadContent(List<Person> personList) {
        for (Person person : personList) {
            if (!personRepository.existsByName(person.getName())) {
                personRepository.save(person);
            } else {
                logger.info("Skipping data as it already exists: {},{}", person.getName(), person.getBirthday());
            }
        }
    }

    private String formatPersonName(CsvData csvData) {
        return String.format("%s, %s %s", csvData.getLastName(), csvData.getFirstName(), csvData.getMiddleName());
    }
}
