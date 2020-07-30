package com.chelkatrao.service;

import com.chelkatrao.Utils;
import com.chelkatrao.bot.NamazBot;
import com.chelkatrao.model.Groups;
import com.chelkatrao.model.Time;
import com.chelkatrao.service.timer.CustomTimerTask;
import com.chelkatrao.service.timer.TimerExecutor;

import java.util.List;

public class SetPrayerTimes {

    private GroupService groupService;
    private TimerExecutor instance = new TimerExecutor();

    public SetPrayerTimes(GroupService groupService) {
        this.groupService = groupService;
    }

    public void executeTongQuyoshPrayerTime(NamazBot currentNamazBot, Time time) {
        setTime("TONG-QUYOSH",
                "ting vaqti",
                currentNamazBot,
                Utils.getHour(time.getTongTime()),
                Utils.getMinute(time.getTongTime()),
                Utils.getSecond(time.getTongTime())
        );
    }

    public void executeQuyoshPrayerTime(NamazBot currentNamazBot, Time time) {
        setTime("QUYOSH",
                "quyosh vaqti",
                currentNamazBot,
                Utils.getHour(time.getQuyoshTime()),
                Utils.getMinute(time.getQuyoshTime()),
                Utils.getSecond(time.getQuyoshTime())
        );
    }

    public void executePeshinPrayerTime(NamazBot currentNamazBot, Time time) {
        setTime("PESHIN",
                "peshin namozining vaqti",
                currentNamazBot,
                Utils.getHour(time.getPeshinTime()),
                Utils.getMinute(time.getPeshinTime()),
                Utils.getSecond(time.getPeshinTime())
        );
    }

    public void executeAsrPrayerTime(NamazBot currentNamazBot, Time time) {
        setTime("ASR",
                "asr namozining vaqti",
                currentNamazBot,
                Utils.getHour(time.getAsrTime()),
                Utils.getMinute(time.getAsrTime()),
                Utils.getSecond(time.getAsrTime())
        );
    }

    public void executeShomPrayerTime(NamazBot currentNamazBot, Time time) {
        setTime("SHOM",
                "shom namozining vaqti",
                currentNamazBot,
                Utils.getHour(time.getShomTime()),
                Utils.getMinute(time.getShomTime()),
                Utils.getSecond(time.getShomTime())
        );
    }

    public void executeXuftonPrayerTime(NamazBot currentNamazBot, Time time) {
        setTime("XUFTON",
                "hufton namozining vaqti",
                currentNamazBot,
                Utils.getHour(time.getXuftonTime()),
                Utils.getMinute(time.getXuftonTime()),
                Utils.getSecond(time.getXuftonTime())
        );
    }

    private void setTime(String name, String msg, NamazBot namazBot, int hour, int minute, int second) {

        instance.startExecutionEveryDayAt(
                new CustomTimerTask(msg, -1) {
                    @Override
                    public void execute() {
                        List<Groups> groups = groupService.findAllGroups();
                        displayByOne(groups, namazBot, name);
                        if (name.equals("XUFTON"))
                            instance.finalize();
                    }
                }, hour, minute, second);
    }

    private void displayByOne(List<Groups> groups, NamazBot namazBot, String name) {
        StringBuffer msg = new StringBuffer();

        if (name.equals("TONG-QUYOSH")) {
            msg.append(String.format(" ••••••••••••••• \uD83C\uDF3F TONG •••••••••••••••  \n                          %s", Times.getTongTime())).append("\n");
            msg.append(String.format(" ————— \uD83C\uDF3F QUYOSH —————\n                          %s", Times.getQuyoshTime()));
        }

        if (name.equals("PESHIN"))
            msg.append(String.format("••••••••••••••• \uD83C\uDF3F PESHIN ••••••••••••  \n          %s", Times.getPeshinTime())).append(" — ").append(Times.getAsrTime());

        if (name.equals("ASR"))
            msg.append(String.format("••••••••••••••• \uD83C\uDF3F ASR ••••••••••••••••  \n          %s", Times.getAsrTime())).append(" — ").append(Times.getShomTime());

        if (name.equals("SHOM"))
            msg.append(String.format("••••••••••••••• \uD83C\uDF3F SHOM ••••••••••••••  \n          %s", Times.getShomTime())).append(" — ").append(Times.getXuftonTime());

        if (name.equals("XUFTON")) {
            msg.append(String.format("••••••••••••••• \uD83C\uDF3F XUFTON ••••••••••••  \n                          %s", Times.getXuftonTime()));
            Times.cleanCache();
        }

        groups.forEach(x -> {
            namazBot.sendMsg(String.valueOf(x.getChatId()), msg.toString(), name);
        });

    }

}
