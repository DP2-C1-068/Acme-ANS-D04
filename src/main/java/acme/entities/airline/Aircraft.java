
package acme.entities.airline;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aircraft extends AbstractEntity {

	//Serialization -----------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes ---------------------------------------------

	@Mandatory
	@ValidString(max = 50)
	@Automapped
	private String				model;

	@Mandatory
	@ValidString(max = 50)
	@Column(unique = true)
	private String				registrationNumber;

	@Mandatory
	@ValidNumber(min = 0, max = 200, integer = 3)
	@Automapped
	private int					capacity;

	@Mandatory
	@ValidNumber(min = 2000, max = 50000, integer = 5)
	@Automapped
	private int					cargoWeight;

	@Automapped
	private boolean				status;

	@Optional
	@ValidString
	@Automapped
	private String				details;

	// Relations ---------------------------------------

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Airline				airline;
}
