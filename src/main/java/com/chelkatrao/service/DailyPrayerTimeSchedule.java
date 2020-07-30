package com.chelkatrao.service;

import com.chelkatrao.bot.NamazBot;
import com.chelkatrao.model.Time;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@Component
public class DailyPrayerTimeSchedule {

    private GroupService groupService;
    private TelegramBotsApi telegramBotsApi;

    @Scheduled(cron = "10 30 1,16 * * *")
    public void takeNewPrayerTimes() throws TelegramApiRequestException {

        SetPrayerTimes setPrayerTimes = new SetPrayerTimes(groupService);

        String tongTime = Times.getTongTime();
        String quyoshTime = Times.getQuyoshTime();
        String peshinTime = Times.getPeshinTime();
        String asrTime = Times.getAsrTime();
        String shomTime = Times.getShomTime();
        String xuftonTime = Times.getXuftonTime();

        Time time = new Time(tongTime, quyoshTime, peshinTime, asrTime, shomTime, xuftonTime);

        NamazBot namazBot = new NamazBot(groupService, setPrayerTimes, time);
        telegramBotsApi.registerBot(namazBot);
    }

    public void setTelegramBotsApi(TelegramBotsApi telegramBotsApi) {
        this.telegramBotsApi = telegramBotsApi;
    }


    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

}
