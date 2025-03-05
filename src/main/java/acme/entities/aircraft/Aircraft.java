
package acme.entities.aircraft;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;
import acme.constraints.ValidAircraft;
import acme.constraints.ValidShortText;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidAircraft
public class Aircraft extends AbstractEntity {

	//Serialization -----------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes ---------------------------------------------

	@Mandatory
	@ValidShortText
	@Automapped
	private String				model;

	@Mandatory
	@ValidShortText
	@Column(unique = true)
	private String				registrationNumber;

	@Mandatory
	@ValidNumber(min = 0, max = 1000, integer = 3)
	@Automapped
	private int					capacity;

	@Mandatory
	@ValidNumber(min = 2000, max = 50000, integer = 5)
	@Automapped
	private int					cargoWeight;

	@Automapped
	@Valid
	private AircraftStatus		status;

	@Optional
	@ValidString
	@Automapped
	private String				details;

	// Relations ---------------------------------------

}
