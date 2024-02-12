package boundary;

import dto.PersonDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Person;
import service.PersonService;
import util.PersonMapper;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonMapper personMapper;

    @Inject
    PersonService personService;

    @GET
    @Path("all")
    public List<PersonDTO> getAllPersons() {
        return personMapper.mapListToDTO(personService.getAllPersons());
    }

    @POST
    @Path("add")
    public PersonDTO addPerson(PersonDTO personDTO) {
        Person person = personMapper.mapToPerson(personDTO);
        return personMapper.mapToDTO(personService.addPerson(person));
    }
}
