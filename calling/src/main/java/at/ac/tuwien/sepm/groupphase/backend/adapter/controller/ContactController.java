package at.ac.tuwien.sepm.groupphase.backend.adapter.controller;

import at.ac.tuwien.sepm.groupphase.backend.domain.model.Contact;
import at.ac.tuwien.sepm.groupphase.backend.service.ContactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/rest/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/findcontacts")
    public Flux<Contact> getFindContacts(@RequestBody Contact contact){
        return contactService.findContacts(contact);
    }
}
