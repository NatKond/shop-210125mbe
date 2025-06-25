package de.telran.shop210125mbe.scheduled;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

import static de.telran.shop210125mbe.textFormatting.*;

@Service
@Slf4j
@EnableAsync
public class ScheduledService {

    //@Scheduled(fixedDelay = 3000) // старт отсчета времени начинается после окончания метода работы
    public void scheduledTaskFixedDelay() {
        log.info(BLUE + "--- Fixed delay task ---");// Time = " + (LocalTime.now()) + RESET);
        try {
            Thread.sleep(10000); // имитируем
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    //@Async // Этот метод будет запускаться в отдельном потоке
    //@Scheduled(fixedRate = 2000) // старт отсчета времени начинается с времени старта метода
    public void scheduledTaskFixedRate() {
        LocalTime startTime = LocalTime.now();
        log.info(GREEN + "--- Fixed rate task --- " + RESET);//Time = " + startTime + " Thread = " + Thread.currentThread().getName() + RESET);
        try {
            Thread.sleep(10000); // имитируем
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //@Scheduled(initialDelay = 5000, fixedDelay = 2500) // старт отсчета времени начинается с времени старта метода
    public void scheduledTaskFixedDelayWithInitialDelay() {
        LocalTime startTime = LocalTime.now();
        log.info("--- Fixed delay with initial delay task --- "  + RESET);//Time = " + startTime);
    }

    // задаем интервал времени через настройки (application.properties)
    //@Scheduled(fixedDelayString = "$test.fixed.delayed.iso") //"PT03S")
    // @Scheduled(fixedDelayString = "$test.fixed.delayed") //fixedDelay = 5000)
    //@SchedulerLock(name = "scheduledTaskFixedDelayIso", lockAtMostFor = "15m")
    public void scheduledTaskFixedDelayIso() {
        LocalTime startTime = LocalTime.now();
        log.info(PURPLE + "--- Fixed delay iso task --- " + RESET); //Time = " + startTime + RESET);
    }

    //@Scheduled(cron = "0 15 10 * * *") // каждый день в 10:15
    //@Scheduled(cron = "0 0 1 * * 7") // каждую неделю в воскресенье
    //@Scheduled(cron = "${cron.expression}")
    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "scheduledTaskCron", lockAtMostFor = "15m")
    public void scheduledTaskCron() {
        log.info(PURPLE + "--- Scheduled task with cron --- " + RESET); //Time = " + startTime + RESET);
    }
}
