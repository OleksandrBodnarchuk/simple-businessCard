package pl.alex.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.alex.exceptions.PersonAlreadyExistsException;
import pl.alex.exceptions.PersonNotFoundException;
import pl.alex.model.Person;
import pl.alex.repository.PersonRepository;

@Service
@AllArgsConstructor
public class PersonService {

	@Autowired
	private final PersonRepository repository;

	public Person getPerson(final UUID personUuId) {
		return repository.findByUuId(personUuId).orElseThrow(PersonNotFoundException::new);
	}

	public List<Person> getAllPerson() {
		return repository.findAll();
	}

	public void save(Person person) {
		if (repository.findByEmail(person.getEmail()).isPresent()) {
			throw new PersonAlreadyExistsException("Person with email: " + person.getEmail() + " is already exists!");
		}
		person.setPersonUUid(UUID.randomUUID());
		person.getAddress().setAddressUUid(UUID.randomUUID());
		repository.save(person);

	}

	public void edit(final UUID personUUid, final Person person) {
		final Person personFromDb = getPerson(personUUid);
		
		personFromDb.setName(person.getName());
		personFromDb.setSurname(person.getSurname());
		personFromDb.setEmail(person.getEmail());
		personFromDb.setPhone(person.getPhone());
		
		person.getAddress().setCity(person.getAddress().getCity());
		person.getAddress().setStreet(person.getAddress().getStreet());
		person.getAddress().setStreetNumber(person.getAddress().getStreetNumber());
		person.getAddress().setHomeNumber(person.getAddress().getHomeNumber());
		
		repository.save(personFromDb);
	}

	
	public void delete(UUID personUUid) {
		checkIfPersonExists(personUUid);
		final Person person = getPerson(personUUid);
		repository.delete(person);
		
	}
	private void checkIfPersonExists(final UUID personUUid) {
		if(!repository.findByUuId(personUUid).isPresent()) {
			throw new PersonNotFoundException();
		}
	}
}
