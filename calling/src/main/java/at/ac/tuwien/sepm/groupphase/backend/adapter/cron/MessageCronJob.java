package at.ac.tuwien.sepm.groupphase.backend.adapter.cron;

import at.ac.tuwien.sepm.groupphase.backend.service.MessageService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageCronJob {

    private static final Logger logger = LoggerFactory.getLogger(MessageCronJob.class);

    @Value("${cronjob.message.ttl.days}")
    private Long messageTtl;

    private final MessageService messageService;

    public MessageCronJob(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostConstruct
    public void init(){
        logger.info("Message ttl : {}", messageTtl);
    }

    @Scheduled(cron = "5 0 1 * * ?")
    @SchedulerLock(name = "MessageCleanup_TaskScheduled", lockAtLeastFor = "PT2H", lockAtMostFor = "PT3H")
    public void cleanUpOldMessages(){
        messageService.cleanUpMessages(messageTtl);
    }
}
