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
public final class Address {

	Long id;
	UUID addressUUid;
	String city;
	String street;
	String streetNumber;
	String homeNumber;

}
