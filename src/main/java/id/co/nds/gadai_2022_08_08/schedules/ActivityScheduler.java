package id.co.nds.gadai_2022_08_08.schedules;

import java.util.Calendar;
import java.util.List;
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

import id.co.nds.gadai_2022_08_08.entities.ActivityEntity;
import id.co.nds.gadai_2022_08_08.entities.CicilanEntity;
import id.co.nds.gadai_2022_08_08.models.ResponseModel;
import id.co.nds.gadai_2022_08_08.repos.ActivityRepo;


@Component
public class ActivityScheduler implements SchedulingConfigurer{

    @Autowired
    private ActivityRepo activityRepo;

    private static final String Log_by = "status_cicilan";

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
            taskRegistrar.setScheduler(poolSceduler());
        }
        
        CheckQuantityScheduler();

        CronTrigger cronTrigger = new CronTrigger(cronVal, TimeZone.getDefault());
        future = taskRegistrar.getScheduler().schedule(() -> scheduleCronTask(), cronTrigger);
    }
        
    public void scheduleCronTask() {
        logger.debug("scheduleCron: run scheduler with configuration -> {" +cronVal+ "}....");

        try{
            logger.debug("start scheduler at " +Calendar.getInstance().getTime());
            counter++;
            logger.info("executing task.....");
            logger.info("task " +counter);

            //here, put the business logic
            // ProductEntity p = new ProductEntity();
            List<CicilanEntity> product = cici.findProductLessThan();
            logger.info("Product Which less than 5 quantity = ");
            ResponseModel response = new ResponseModel();
            response.setData(product);
            logger.info(response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            if(stopScheduler){
                cancelTasks(true);
                scheduledTaskRegistrar = null;
                logger.debug("stopping scheduler Task....");
            }
        }
        CheckQuantityScheduler();
    }

    private void CheckQuantityScheduler() {
        if (cronVal.trim().equalsIgnoreCase("")){
            cronVal = paramRepo.findById(PARAM_KEY).orElse(null).getParamValue();
        }
        else{
            String newCronFromDb = "";
            newCronFromDb = paramRepo.findById(PARAM_KEY).orElse(null).getParamValue();

            if(!stopScheduler && !newCronFromDb.equalsIgnoreCase(cronVal)){
                cronVal = newCronFromDb;
                // reload new scheduler
                logger.info("scheduleCron: Next Execution time of this taken from cron expression -> {"
                            + newCronFromDb+ "}");
                cancelTasks(false);
                activateScheduler();
            }
        }
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
