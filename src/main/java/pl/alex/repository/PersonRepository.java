package pl.alex.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import pl.alex.model.Person;

@Repository
public class PersonRepository {

	private Map<UUID, Person> persons = new ConcurrentHashMap<>();

	public Person getPerson(final UUID personId) {
		return persons.get(personId);
	}

	public List<Person> findAll() {
		return persons.values().stream().collect(Collectors.toList());
	}

	public void save(Person person) {
		if (person.getPersonUUid() == null) {
			final UUID randomUuid = UUID.randomUUID();
			person.setPersonUUid(randomUuid);
		}
		persons.put(person.getPersonUUid(), person);
	}

	public Optional<Person> findByEmail(String email) {
		return persons.values().stream().filter(x -> x.getEmail().equalsIgnoreCase(email)).findAny();

	}

	public Optional<Person> findByUuId( UUID personUUid) {
		return persons.values().stream().filter(x -> x.getPersonUUid().equals(personUUid)).findAny();
	}

	public void delete( UUID personUUid) {
		persons.remove(personUUid);
				
	}
}
