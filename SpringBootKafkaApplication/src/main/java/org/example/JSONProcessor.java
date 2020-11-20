package org.example;

import java.util.Date;

public class JSONProcessor {

    public static String addTimeStamp(String messageText, long ts) {
        if (messageText.contains("}")) {
            StringBuilder stringBuilder = new StringBuilder(messageText);
            stringBuilder.replace(stringBuilder.indexOf("}"), stringBuilder.indexOf("}"), ", \"handledTimestamp\" : \"" + new Date(ts) + "\"");
            System.out.println(stringBuilder);
            return stringBuilder.toString();
        }
        return messageText;
    }
}
