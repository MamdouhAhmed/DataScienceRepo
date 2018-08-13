package com.tajawal.record.fetcher;

import com.tajawal.helpers.FileSystemHelper;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonRecordFileReader implements JsonFetcher {
    /**
     * @param path
     * @return
     * @throws Exception
     */
    @Override
    public List<JSONObject> getAllJsonRecords(String path) throws Exception {
        List<String> allFiles = FileSystemHelper.getAllAvailableFiles(new File(path));
        List<String> records = new ArrayList<>();
        for (String fileName : allFiles) {
            try {
                records.addAll(getAllRecordsInFile(fileName));
            } catch (IOException e) {
                throw new Exception("Could not get records from " + fileName);
            }
        }
        return records.stream().map(JSONObject::new).collect(Collectors.toList());
    }

    /**
     * @param fileName
     * @return
     * @throws IOException
     */
    private List<String> getAllRecordsInFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        ArrayList<String> records = new ArrayList<>();
        String temp;

        while ((temp = bufferedReader.readLine()) != null) {
            records.add(temp);
        }

        return records;
    }
}
