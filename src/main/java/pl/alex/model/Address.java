package pl.alex.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public final class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	UUID addressUUid;
	String city;
	String street;
	String streetNumber;
	String homeNumber;

}
