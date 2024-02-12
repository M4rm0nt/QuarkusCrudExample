package util;

import jakarta.enterprise.context.ApplicationScoped;
import dto.PersonDTO;
import model.Person;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonMapper {

    public Person mapToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setEmail(personDTO.getEmail());
        return person;
    }

    public PersonDTO mapToDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName());
        personDTO.setEmail(person.getEmail());
        return personDTO;
    }

    public List<PersonDTO> mapListToDTO(List<Person> persons) {
        return persons.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
