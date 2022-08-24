package id.co.nds.gadai_2022_08_08.schedulers;

import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import id.co.nds.gadai_2022_08_08.entities.TrxDendaEntity;
import id.co.nds.gadai_2022_08_08.services.TrxCicilanService;
import id.co.nds.gadai_2022_08_08.services.TrxDendaService;

@Component
public class CicilanDendaScheduler {
    @Autowired
    TrxCicilanService trxCicilanService;

    @Autowired
    TrxDendaService trxDendaService;
    
    static final Logger logger = LogManager.getLogger(CicilanDendaScheduler.class);

    @Scheduled(cron = "1 0 0 * * ?")
    public void cronSchedule() throws Exception {
        logger.debug("Start CicilanDendaScheduler at " + Calendar.getInstance().getTime());
        
        //status update below
        List<String> updated = trxCicilanService.doUpdateStatusCicilan();
        if(updated == null || updated.size() == 0) {
            logger.info("No cicilan status got updated");
        }
        else {
            updated.forEach((String s) -> {
                logger.info(s);
            });
        }
        //status update above

        //denda below
        List<TrxDendaEntity> dendaUpdates = trxDendaService.doCheckDenda();
        if(dendaUpdates == null || dendaUpdates.size() == 0) {
            logger.info("No denda got added");
        }
        else {
            dendaUpdates.forEach((TrxDendaEntity dendas) -> {
                logger.info("Added denda for {" + dendas.getNoTransaksi() + " - " + dendas.getCicilanKe() + "}: " + dendas.getBiayaDenda());
            });
        }
        //denda above

        logger.debug("End CicilanDendaScheduler at " + Calendar.getInstance().getTime());
    }
}
