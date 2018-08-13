package com.tajawal;


import com.tajawal.entity.CommonOrder;
import com.tajawal.mappers.CommonOrderMapper;
import com.tajawal.record.exporter.Exporter;
import com.tajawal.record.exporter.FileExporter;
import com.tajawal.record.fetcher.JsonFetcher;
import com.tajawal.record.fetcher.JsonRecordFileReader;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {

        JsonFetcher jsonFetcher = new JsonRecordFileReader();
        List<JSONObject> allJsonRecords = new ArrayList<>();
        try {
            allJsonRecords = jsonFetcher.getAllJsonRecords("src/main/resources/json/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        CommonOrderMapper commonOrderMapper = new CommonOrderMapper();
        List<CommonOrder> commonOrders = new ArrayList<>();

        for (JSONObject allJsonRecord : allJsonRecords) {
            CommonOrder commonOrder = new CommonOrder();
            try {
                commonOrder = commonOrderMapper.mapFromJsonObject(commonOrder, allJsonRecord);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(2);
            }
            commonOrders.add(commonOrder);
        }

        Exporter exporter = new FileExporter();
        exporter.exportRecords(commonOrders);

    }
}
