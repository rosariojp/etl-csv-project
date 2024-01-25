package com.jeipz.etl.service;

import com.jeipz.etl.model.dto.CsvData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvHelperService {

    boolean isCsvFile(MultipartFile file);

    List<CsvData> extractCsvContent(MultipartFile file);

}
