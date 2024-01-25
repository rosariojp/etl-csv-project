package com.jeipz.etl.service;

import com.jeipz.etl.model.dto.CsvData;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CsvHelperServiceImpl implements CsvHelperService {

    private static final Logger logger = LoggerFactory.getLogger(CsvHelperServiceImpl.class);

    @Override
    public boolean isCsvFile(MultipartFile file) {
        String csvContentType = "text/csv";
        return Objects.equals(file.getContentType(), csvContentType);
    }

    @Override
    public List<CsvData> extractCsvContent(MultipartFile file) {
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            return new CsvToBeanBuilder<CsvData>(reader)
                    .withType(CsvData.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage());
        }
        return new ArrayList<>();
    }

}
