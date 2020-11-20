package org.example;

public final class JSONProcessor {

    private JSONProcessor() {
    }

    public static String addTimeStamp(String messageText, long ts) {
        if (messageText.contains("}")) {
            StringBuilder stringBuilder = new StringBuilder(messageText);
            stringBuilder.replace(stringBuilder.indexOf("}"), stringBuilder.indexOf("}"), ", \"handledTimestamp\" : " + ts);
            System.out.println(stringBuilder);
            return stringBuilder.toString();
        }
        return messageText;
    }
}
