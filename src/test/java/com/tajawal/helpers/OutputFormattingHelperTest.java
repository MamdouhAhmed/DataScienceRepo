package com.tajawal.helpers;

import com.tajawal.entity.CommonOrder;
import org.junit.Assert;
import org.junit.Test;

public class OutputFormattingHelperTest {


    private CommonOrder makeCommonOrder(){
        CommonOrder commonOrder = new CommonOrder();
        commonOrder.setBookingDate("2018-07-24");
        commonOrder.setTotalUSD("182.66");
        commonOrder.setTotal("685.00");
        commonOrder.setStatusId("58");
        commonOrder.setDeviceType("web");
        commonOrder.setBrowserType("Chrome Mobile");
        commonOrder.setOrderType("flight");
        commonOrder.setOrderId("5b570ea8423584e03f0e8ba5");
        return commonOrder;
    }

    @Test
    public void testCommonOrderFormatting(){
        Assert.assertEquals("5b570ea8423584e03f0e8ba5\tflight\t2018-07-24\t685.00\t182.66\tweb\tChrome Mobile\t58\n",
                OutputFormattingHelper.getFormattedCommonOrder(makeCommonOrder()));
    }
}
