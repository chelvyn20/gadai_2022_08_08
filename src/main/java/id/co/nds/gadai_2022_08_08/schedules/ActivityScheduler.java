package id.co.nds.gadai_2022_08_08.schedules;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import id.co.nds.gadai_2022_08_08.repos.ActivityRepo;


@Component
public class ActivityScheduler implements SchedulingConfigurer{

    @Autowired
    private ActivityRepo activityRepo;

    private static final String PARAM_KEY = "CRON_10_Seconds";

    Integer counter =0;

    private static ScheduledTaskRegistrar scheduledTaskRegistrar;

    @SuppressWarnings("rawtypes")
    private static ScheduledFuture future;

    static final Logger logger = LogManager.getLogger(ActivityScheduler.class);
    private static String cronVal ="";
    public static boolean stopScheduler = false;

    @Bean
    public TaskScheduler poolSceduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ActivityScheduler-ThreadPoolTaskSchedule - ##");
        scheduler.setPoolSize(2);
        scheduler.initialize();
        return scheduler;        
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // TODO Auto-generated method stub
        if (scheduledTaskRegistrar == null){
            scheduledTaskRegistrar = taskRegistrar;
        }
        if (taskRegistrar.getScheduler() == null){
            // taskRegistrar.setScheduler(taskSceduler());
        }
        
        // CheckQuantityScheduler();

        CronTrigger cronTrigger = new CronTrigger(cronVal, TimeZone.getDefault());
        // future = taskRegistrar.getScheduler().schedule(() -> scheduleCronTask(), cronTrigger);
    }
        




    
    public void cancelTasks(boolean mayInterruptIfRunning){
        logger.info("###Cancelling all tasks...");
        future.cancel(mayInterruptIfRunning);
    }

    public void activateScheduler(){
        logger.info("###Reload Scheduler...");
        configureTasks(scheduledTaskRegistrar);
    }

    public static void shutdownScheduler(){
        logger.info("###ShuttingDown Scheduler...");
        stopScheduler = true;
    }    
}
