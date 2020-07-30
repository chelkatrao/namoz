package com.chelkatrao.service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Times {

    public static List<Map<String, Object>> getAllTimesFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);


        LocalDate currentDate = LocalDate.now();
        String url = "http://api.aladhan.com/v1/calendarByAddress?address=%20Tashkent&method=2&month=" + currentDate.getMonthValue() + "&year=" + currentDate.getYear() + "&school=1";

        ResponseEntity<Object> data = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
        List<Map<String, Object>> timeList = (List<Map<String, Object>>) ((Map<Object, Object>) data.getBody()).get("data");

        List<Map<String, Object>> listPrayerTimes = timeList.stream()
                .filter(x -> Integer.valueOf((String) (((Map<String, Object>) ((Map<String, Object>) x.get("date")).get("gregorian")).get("day"))) == currentDate.getDayOfMonth()).collect(Collectors.toList());
        return listPrayerTimes;
    }

    private static Map<String, Object> allTimes = null;

    public static String getTongTime() {
        if (allTimes == null)
            allTimes = (Map<String, Object>) getAllTimesFromApi().get(0).get("timings");
        return (String) allTimes.get("Fajr");
    }

    public static String getQuyoshTime() {
        return (String) allTimes.get("Sunrise");
    }

    public static String getPeshinTime() {
        return (String) allTimes.get("Dhuhr");
    }

    public static String getAsrTime() {
        return (String) allTimes.get("Asr");
    }

    public static String getShomTime() {
        return (String) allTimes.get("Sunset");
    }

    public static String getXuftonTime() {
        return (String) allTimes.get("Isha");
    }

    public static void cleanCache() { // Kechagi dannini bugunga berib yubormasligi uchun
        allTimes = null;
    }

}
