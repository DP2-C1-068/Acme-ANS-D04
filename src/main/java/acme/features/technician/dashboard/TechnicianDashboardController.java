
package acme.features.technician.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.controllers.AbstractGuiController;
import acme.client.controllers.GuiController;
import acme.forms.Dashboard;
import acme.realms.Technician;

@GuiController
public class TechnicianDashboardController extends AbstractGuiController<Technician, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private TechnicianDashboardShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
	}

}
