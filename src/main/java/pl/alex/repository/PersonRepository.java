package pl.alex.repository;



import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.alex.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

	Optional<Person> findByUuId(UUID personUuId);

	Optional<Person> findByEmail(final String email);

}
