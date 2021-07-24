package pl.alex.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class Person {

	Long id;
	UUID personUUid;
	String name;
	String surname;
	String email;
	String phone;
	Address address;

}
