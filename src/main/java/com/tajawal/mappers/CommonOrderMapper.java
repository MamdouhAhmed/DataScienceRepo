package com.tajawal.mappers;

import com.sun.javafx.binding.StringFormatter;
import com.tajawal.entity.CommonOrder;
import com.tajawal.exceptions.JsonFormatException;
import com.tajawal.helpers.UserAgentParsingHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonOrderMapper {

    public CommonOrder mapFromJsonObject(CommonOrder commonOrder, JSONObject jsonObject) throws JsonFormatException {

        if (commonOrder == null) {
            commonOrder = new CommonOrder();
        }

        commonOrder.setOrderId(jsonObject.getString("orderNumber"));
        commonOrder.setOrderType(jsonObject.getString("type"));

        JSONObject coreOrder = getCoreProduct(jsonObject, commonOrder);

        commonOrder.setBookingDate(coreOrder.getJSONObject("createdAt").getString("$date").split("T")[0]);
        commonOrder.setStatusId("" + jsonObject.getInt("status"));

        setBrowserAndDeviceInfo(jsonObject, commonOrder);

        setTotalsAndTotalsUsd(jsonObject, commonOrder, coreOrder);

        return commonOrder;
    }

    private void setTotalsAndTotalsUsd(JSONObject jsonObject, CommonOrder commonOrder, JSONObject coreOrder) {
        double total = jsonObject.getJSONObject("totals").getDouble("total");
        double UsdRate;

        try {
            UsdRate = getUsdRate(coreOrder.getJSONObject("additionalData").getJSONObject("exchange").getJSONArray("Equivalent"));
        } catch (JSONException je) {
            UsdRate = 0.0;
        }

        UsdRate *= total;
        commonOrder.setTotalUSD((UsdRate == 0.0) ? "N/A" : StringFormatter.format("%.02f", UsdRate).get());
        commonOrder.setTotal(StringFormatter.format("%.02f", total).get());
    }

    private void setBrowserAndDeviceInfo(JSONObject jsonObject, CommonOrder commonOrder) {
        try {
            commonOrder.setDeviceType(jsonObject.getJSONObject("additionalData").getJSONObject("headers").getString("x-router"));
            if (commonOrder.getDeviceType().toLowerCase().equals("mobile")) {
                commonOrder.setBrowserType("MobileApp");
            } else {
                commonOrder.setBrowserType(UserAgentParsingHelper.getBrowserName(jsonObject.getJSONObject("additionalData").getJSONObject("headers").getString("user-agent")));
            }
        } catch (JSONException je) {
            if (commonOrder.getDeviceType() == null) {
                commonOrder.setDeviceType("unknown");
            }
            commonOrder.setBrowserType("unknown");

        }
    }

    private double getUsdRate(JSONArray jsonArray) {
        JSONObject temp;
        for (Object obj : jsonArray) {
            temp = (JSONObject) obj;
            if (temp.getString("code").toUpperCase().equals("USD")) {
                return temp.getDouble("rate");
            }
        }
        return 0.0;
    }

    private JSONObject getCoreProduct(JSONObject jsonObject, CommonOrder commonOrder) throws JsonFormatException {
        JSONObject coreOrder = null;
        for (Object jsonObject1 : jsonObject.getJSONArray("products")) {
            coreOrder = (JSONObject) jsonObject1;
            if (coreOrder.getString("type").toLowerCase().equals(commonOrder.getOrderType().toLowerCase())) {
                break;
            }
        }
        if (coreOrder == null) {
            throw new JsonFormatException("error in JSON file format");
        }
        return coreOrder;
    }
}
