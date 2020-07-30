package com.chelkatrao;

import com.chelkatrao.service.DailyPrayerTimeSchedule;
import com.chelkatrao.service.GroupService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@EnableScheduling
@SpringBootApplication
public class App {
    public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();

        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        GroupService groupService = run.getBean(GroupService.class);


        DailyPrayerTimeSchedule dailyPrayerTimeSchedule = run.getBean(DailyPrayerTimeSchedule.class);
        dailyPrayerTimeSchedule.setGroupService(groupService);

        TelegramBotsApi telegramBotApi = new TelegramBotsApi();
        dailyPrayerTimeSchedule.setTelegramBotsApi(telegramBotApi);
        dailyPrayerTimeSchedule.takeNewPrayerTimes();
    }
}
