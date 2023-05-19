package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.domain.model.Contact;
import at.ac.tuwien.sepm.groupphase.backend.domain.model.MsgUser;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@Service
public class ContactService {
    private final MyRepository myRepository;

    public ContactService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public Flux<Contact> findContacts(@Valid Contact contact){
        return myRepository.find(new Query().addCriteria(Criteria.where("username").regex(String.format(".*%s.*", contact.getName()))), MsgUser.class).take(50).map(myUser -> new Contact(myUser.getUsername(), myUser.getBase64Avatar(), myUser.getPublicKey(), myUser.getId().toString()));
    }
}
