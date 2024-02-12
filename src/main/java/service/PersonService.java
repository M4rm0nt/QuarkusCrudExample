package service;

import config.AppConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.PersonRepository;
import java.util.List;

@ApplicationScoped
public class PersonService {

    private static final Logger logger = LogManager.getLogger(PersonService.class);

    @Inject
    PersonRepository personRepository;

    public Person addPerson(Person person) {
        logger.info("Person hinzugefügt: " + person.getName());
        return personRepository.addPerson(person);
    }

    public List<Person> getAllPersons() {
        logger.info("Alle Personen abgerufen");
        return personRepository.getAllPersons();
    }
}
