package com.tajawal.record.exporter;

import com.tajawal.entity.CommonOrder;

import java.util.List;

public interface Exporter {
    void exportRecords(List<CommonOrder> commonOrders);
}
