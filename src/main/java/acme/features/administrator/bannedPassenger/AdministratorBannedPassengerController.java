
package acme.features.administrator.bannedPassenger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.principals.Administrator;
import acme.client.controllers.AbstractGuiController;
import acme.client.controllers.GuiController;
import acme.entities.bannedPassenger.BannedPassenger;

@GuiController
public class AdministratorBannedPassengerController extends AbstractGuiController<Administrator, BannedPassenger> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannedPassengerShowService		showService;

	@Autowired
	private AdministratorBannedPassengerListService		listService;

	@Autowired
	private AdministratorBannedPassengerCreateService	createService;

	@Autowired
	private AdministratorBannedPassengerUpdateService	updateService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);

	}
}
