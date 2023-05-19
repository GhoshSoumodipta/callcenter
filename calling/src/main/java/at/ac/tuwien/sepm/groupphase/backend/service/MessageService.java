package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.domain.exception.TooManyMsgException;
import at.ac.tuwien.sepm.groupphase.backend.domain.model.Contact;
import at.ac.tuwien.sepm.groupphase.backend.domain.model.Message;
import at.ac.tuwien.sepm.groupphase.backend.domain.model.SyncMsgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private static final int MB = 1024 * 1024;
    private final MyRepository myRepository;

    public MessageService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public Flux<Message> findMessage(@Valid SyncMsgs syncMsgs){
        return myRepository.find(new Query().addCriteria(Criteria.where("fromId").in(syncMsgs.getContactIds()).orOperator(Criteria.where("toId").is(syncMsgs.getOwnId()).andOperator(Criteria.where("timestamp").gt(syncMsgs.getLastUpdate())))), Message.class).map(msg -> {
            msg.setReceived(true);
            return msg;
        }).flatMap(msg -> myRepository.save(msg));
    }

    public Flux<Message> receivedMessages(Contact contact){
        final List<Message> msgToDelete = new LinkedList<>();
        return myRepository.find(new Query().addCriteria(Criteria.where("fromId").is(contact.getUserId()).andOperator(Criteria.where("received").is(true))), Message.class).flatMap(msg -> {
            msgToDelete.add(msg);
            return myRepository.remove(msg);
        }).flatMapIterable(result -> msgToDelete);
    }

    public void cleanUpMessages(Long messageTtl){
        logger.info("cleanup of Old Messages started.");
        Date removeTimestamp = Date.from(LocalDateTime.now().minusDays(messageTtl).toInstant(ZoneOffset.systemDefault().getRules().getOffset(Instant.now())));
        myRepository.findAllAndRemove(new Query().addCriteria(Criteria.where("timestamp").lt(removeTimestamp)), Message.class).collectList().block();
        logger.info("cleanup of Old Messages finished.");
    }

    private Mono<Long> sizeOfMessages(List<Message> messages, String ownerId){
        long msgsSizeSum = messages.stream().flatMapToLong(message -> message.getText() == null ? LongStream.of(0L) : LongStream.of(Long.valueOf(message.getText().length()))).reduce(0, (collect, newLength) -> collect + newLength);
        if(msgsSizeSum > 15 * MB){
            throw new TooManyMsgException(String.format("MsgSizeSum for User %s is %d", ownerId, msgsSizeSum));
        }
        return Mono.just(msgsSizeSum);
    }

    public ResponseEntity<Flux<Message>> storeMessages(@Valid SyncMsgs syncMsgs){
        List<Message> msgs = syncMsgs.getMsgs().stream().map(msg -> {
            msg.setSend(true);
            msg.setTimestamp(new Date());
            return msg;
        }).filter(msg -> msg.getText() != null && !msg.getText().isBlank()).filter(msg -> msg.getFilename() == null || (msg.getFilename() != null && msg.getText().length() < 3 * MB)).collect(Collectors.toList());
        Flux<Message> msgsFlux = myRepository.find(new Query().addCriteria(Criteria.where("fromId").is(syncMsgs.getOwnId())), Message.class).collectList().flatMap(messages -> sizeOfMessages(messages, syncMsgs.getOwnId())).flux().flatMap(value -> myRepository.insertAll(msgs));
        return syncMsgs.getMsgs().size() > msgs.size() ? ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(msgsFlux) : ResponseEntity.ok(msgsFlux);
    }
}
