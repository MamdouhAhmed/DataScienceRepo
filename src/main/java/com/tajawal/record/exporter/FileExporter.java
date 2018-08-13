package com.tajawal.record.exporter;

import com.tajawal.entity.CommonOrder;
import com.tajawal.helpers.OutputFormattingHelper;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileExporter implements Exporter {

    public void writeToFile(List<CommonOrder> commonOrders) throws IOException {
        BufferedOutputStream bufferedWriter = new BufferedOutputStream(new FileOutputStream("result.tsv"));
        for (CommonOrder commonOrder : commonOrders) {
            bufferedWriter.write(OutputFormattingHelper.getFormattedCommonOrder(commonOrder).getBytes());
        }
        bufferedWriter.flush();
    }

    @Override
    public void exportRecords(List<CommonOrder> commonOrders) {
        try {
            writeToFile(commonOrders);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
    }
}
