package com.chelkatrao.service.timer;

import org.telegram.telegrambots.logging.BotLogger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerExecutor {
    private static final String LOGTAG = "TIMEREXECUTOR";
    private static volatile TimerExecutor instance;
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public void startExecutionEveryDayAt(CustomTimerTask task,
                                         int targetHour,
                                         int targetMin,
                                         int targetSec) {

        BotLogger.warn(LOGTAG, "Posting new task" + task.getTaskName());
        final Runnable taskWrapper = () -> {
            try {
                task.execute();
                task.reduceTimes();
                startExecutionEveryDayAt(task, targetHour, targetMin, targetSec);
            } catch (Exception e) {
                BotLogger.severe(LOGTAG, "Bot threw an unexpected exception at TimerExecutor", e);
            }
        };
        if (task.getTimes() != 0) {
            final long delay = computNextDilay(targetHour, targetMin, targetSec);
            executorService.schedule(taskWrapper, delay, TimeUnit.SECONDS);
        }
    }

    private long computNextDilay(int targetHour, int targetMin, int targetSec) {
        final LocalDateTime localNow = LocalDateTime.now();
        LocalDateTime localNextTarget = localNow.withHour(targetHour).withMinute(targetMin).withSecond(targetSec);
        while (localNow.compareTo(localNextTarget.minusSeconds(1)) > 0) {
            localNextTarget = localNextTarget.plusDays(1);
        }

        final Duration duration = Duration.between(localNow, localNextTarget);
        return duration.getSeconds();
    }

    @Override
    public void finalize() {
        this.stop();
    }

    private void stop() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            BotLogger.severe(LOGTAG, ex);
        } catch (Exception e) {
            BotLogger.severe(LOGTAG, "Bot threw an unexpected exception at TimerExecutor", e);
        }
    }

}

