package com.chelkatrao.bot;

import com.chelkatrao.BotConfig;
import com.chelkatrao.model.Time;
import com.chelkatrao.service.GroupService;
import com.chelkatrao.service.SetPrayerTimes;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class NamazBot extends TelegramLongPollingBot {

    private GroupService groupService;
    private SetPrayerTimes setPrayerTimes;

    public NamazBot(GroupService groupService,
                    SetPrayerTimes setPrayerTimes,
                    Time time) {
        super();
        this.groupService = groupService;
        this.setPrayerTimes = setPrayerTimes;
        if (time != null) {
            everyTime(time);
        }
    }


    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        if (groupService.findGroupByChatId(chatId))
            groupService.saveGroup(chatId);
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME.getValue();
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN.getValue();
    }

    private void everyTime(Time time) {
        NamazBot currentNamazBot = this;
        setPrayerTimes.executeTongQuyoshPrayerTime(currentNamazBot, time);
        setPrayerTimes.executeQuyoshPrayerTime(currentNamazBot, time);
        setPrayerTimes.executePeshinPrayerTime(currentNamazBot, time);
        setPrayerTimes.executeAsrPrayerTime(currentNamazBot, time);
        setPrayerTimes.executeShomPrayerTime(currentNamazBot, time);
        setPrayerTimes.executeXuftonPrayerTime(currentNamazBot, time);
    }

    public synchronized void sendMsg(String chatId, String msg, String name) {
        try {
//            String imgUrl = "";
//            if (name.equals("TONG-QUYOSH"))
//                imgUrl = "/img/6.png";
//            if (name.equals("PESHIN"))
//                imgUrl = "/img/4.png";
//            if (name.equals("ASR"))
//                imgUrl = "/img/7.png";
//            if (name.equals("SHOM"))
//                imgUrl = "/img/5.png";
//            if (name.equals("XUFTON"))
//                imgUrl = "/img/10.png";

//            File file = new File(imgUrl);
//            SendPhoto message = new SendPhoto();
//            message.setNewPhoto(file);
//            message.setCaption(s);
//            message.setChatId(chatId);
//            sendPhoto(message);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(msg);
            execute(sendMessage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
