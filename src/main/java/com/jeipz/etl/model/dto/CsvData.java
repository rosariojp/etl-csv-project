package com.jeipz.etl.model.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CsvData {

    @CsvBindByName(column = "First Name")
    private String firstName;

    @CsvBindByName(column = "Middle Name")
    private String middleName;

    @CsvBindByName(column = "Last Name")
    private String lastName;

    @CsvBindByName(column = "Birthday")
    @CsvDate("M/d/yyyy")
    private LocalDate birthday;

}
