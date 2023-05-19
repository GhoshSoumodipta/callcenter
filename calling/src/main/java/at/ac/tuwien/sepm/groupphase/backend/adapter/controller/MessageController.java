package at.ac.tuwien.sepm.groupphase.backend.adapter.controller;

import at.ac.tuwien.sepm.groupphase.backend.domain.model.Contact;
import at.ac.tuwien.sepm.groupphase.backend.domain.model.Message;
import at.ac.tuwien.sepm.groupphase.backend.domain.model.SyncMsgs;
import at.ac.tuwien.sepm.groupphase.backend.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/rest/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/findMsgs")
    public Flux<Message> getFindMessages(@RequestBody SyncMsgs syncMsgs){
        return messageService.findMessage(syncMsgs);
    }

    @PostMapping("/receivedMsgs")
    public Flux<Message> getReceivedMessages(@RequestBody Contact contact){
        return messageService.receivedMessages(contact);
    }

    @PostMapping("/storeMsgs")
    public ResponseEntity<Flux<Message>> putStoreMessages(@RequestBody SyncMsgs syncMsgs){
        return messageService.storeMessages(syncMsgs);
    }
}
