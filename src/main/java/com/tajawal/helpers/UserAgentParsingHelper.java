package com.tajawal.helpers;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

public class UserAgentParsingHelper {
    public static String getBrowserName(String userAgent) {
        UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
        ReadableUserAgent agent = parser.parse(userAgent);
        return agent.getName();
    }
}
