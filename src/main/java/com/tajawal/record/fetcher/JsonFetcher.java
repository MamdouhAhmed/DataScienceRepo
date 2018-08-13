package com.tajawal.record.fetcher;

import org.json.JSONObject;

import java.util.List;

public interface JsonFetcher {
    List<JSONObject> getAllJsonRecords(String dir) throws Exception;
}
