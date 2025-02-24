
package acme.realms;

import javax.persistence.Column;
import javax.persistence.Entity;

import acme.client.components.basis.AbstractRole;
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
public class Technician extends AbstractRole {

	// Serializaition version ----------------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@Mandatory
	@ValidString(pattern = "^[A-Z]{2,3}\\d{6}$", message = "{acme.validation.technician.licenseNumber.message}")
	@Column(unique = true)
	private String				licenseNumber;

	@Mandatory
	@ValidString(pattern = "^\\+?\\d{6,15}$", message = "{acme.validation.technician.phone.message}")
	@Automapped
	private String				phoneNumber;

	@Mandatory
	@ValidString(min = 1, max = 50, message = "{acme.validation.technician.specialisation.message}")
	@Automapped
	private String				specialisation;

	@Mandatory
	@Automapped
	private boolean				annualHealthTest;

	@Mandatory
	@ValidNumber(min = 0, max = 60, integer = 2)
	@Automapped
	private int					yearsOfExperience;

	@Optional
	@ValidString(message = "{acme.validation.technician.certifications.message}")
	@Automapped
	private String				certifications;

}
