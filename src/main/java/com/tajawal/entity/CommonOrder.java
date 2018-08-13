package com.tajawal.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString()
public class CommonOrder {
    private String orderId;

    private String orderType;

    private String bookingDate;

    private String Total;

    private String totalUSD;

    private String browserType;

    private String deviceType;

    private String statusId;
}
