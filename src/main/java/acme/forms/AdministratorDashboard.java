
package acme.forms;

import acme.client.components.basis.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard extends AbstractForm {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	// Ratios of active and non-active aircrafts.	
	Double						ratioActiveAircrafts;
	Double						ratioNonActiveAircrafts;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
