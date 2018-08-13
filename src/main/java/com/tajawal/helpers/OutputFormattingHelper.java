package com.tajawal.helpers;

import com.tajawal.entity.CommonOrder;

public class OutputFormattingHelper {

    public static String getFormattedCommonOrder(CommonOrder commonOrder) {
        return commonOrder.getOrderId() + "\t" + commonOrder.getOrderType() + "\t" + commonOrder.getBookingDate() + "\t" + commonOrder.getTotal() + "\t" + commonOrder.getTotalUSD() + "\t" + commonOrder.getDeviceType()
                + "\t" + commonOrder.getBrowserType() + "\t" + commonOrder.getStatusId() + "\n";
    }
}
