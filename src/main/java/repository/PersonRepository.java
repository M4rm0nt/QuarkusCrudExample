package repository;

import jakarta.enterprise.context.ApplicationScoped;
import model.Person;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.PersonService;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@ApplicationScoped
public class PersonRepository {

    private static final Logger logger = LogManager.getLogger(PersonRepository.class);

    public Person addPerson(Person person) {
        String sql = "INSERT INTO person (name, email) VALUES (?, ?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getEmail());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                person.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
           logger.error("Fehler beim Hinzufügen der Person: " + e.getMessage());
        }
        return person;
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT id, name, email FROM person";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setEmail(rs.getString("email"));
                persons.add(person);
            }
        } catch (SQLException e) {
            logger.error("Fehler beim Abrufen aller Personen: " + e.getMessage());
        }
        return persons;
    }
}
