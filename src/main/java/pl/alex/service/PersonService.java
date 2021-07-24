package pl.alex.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.alex.exceptions.PersonAlreadyExistsException;
import pl.alex.exceptions.PersonNotFoundException;
import pl.alex.model.Person;
import pl.alex.repository.PersonRepository;

@Service
@AllArgsConstructor
public class PersonService {

	private final PersonRepository repository;

	public Person getPerson(final UUID personUuId) {
		return repository.getPerson(personUuId);
	}

	public List<Person> getAllPerson() {
		return repository.findAll();
	}

	public void save(Person person) {
		if (repository.findByEmail(person.getEmail()).isPresent()) {
			throw new PersonAlreadyExistsException("Person with email: " + person.getEmail() + " is already exists!");
		}
		repository.save(person);

	}

	public void edit(final UUID personUUid, final Person person) {
		checkIfPersonExists(personUUid);
		person.setPersonUUid(personUUid);
		repository.save(person);
	}

	
	public void delete(UUID personUUid) {
		checkIfPersonExists(personUUid);
		repository.delete(personUUid);
		
	}
	private void checkIfPersonExists(final UUID personUUid) {
		if(!repository.findByUuId(personUUid).isPresent()) {
			throw new PersonNotFoundException("Person with this ID is already exists!");
		}
	}
}
