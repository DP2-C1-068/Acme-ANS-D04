
package acme.features.administrator.bannedPassenger;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.principals.Administrator;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.bannedPassenger.BannedPassenger;

@GuiService
public class AdministratorBannedPassengerUpdateService extends AbstractGuiService<Administrator, BannedPassenger> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBannedPassengerRepository repository;

	// AbstractGuiService interface -------------------------------------------


	@Override
	public void authorise() {
		BannedPassenger bannedPassenger;
		int bannedPassengerId;
		boolean status;

		if (super.getRequest().hasData("id", int.class)) {
			bannedPassengerId = super.getRequest().getData("id", int.class);
			bannedPassenger = this.repository.findBannedPassengerById(bannedPassengerId);
			status = bannedPassenger != null;
		} else
			status = false;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		BannedPassenger bannedPassenger;
		int bannedPassengerId;

		bannedPassengerId = super.getRequest().getData("id", int.class);
		bannedPassenger = this.repository.findBannedPassengerById(bannedPassengerId);

		super.getBuffer().addData(bannedPassenger);
	}

	@Override
	public void bind(final BannedPassenger bannedPassenger) {

		super.bindObject(bannedPassenger, "name", "passport", "banDate", "liftDate", "birthDate", "nationality", "reason");

	}

	@Override
	public void validate(final BannedPassenger bannedPassenger) {
		boolean confirmationCreate;

		confirmationCreate = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmationCreate, "confirmation", "acme.validation.confirmation.message");
	}

	@Override
	public void perform(final BannedPassenger bannedPassenger) {
		this.repository.save(bannedPassenger);
	}

	@Override
	public void unbind(final BannedPassenger bannedPassenger) {
		Dataset dataset;

		dataset = super.unbindObject(bannedPassenger, "name", "passport", "banDate", "liftDate", "birthDate", "nationality", "reason");

		super.getResponse().addData(dataset);
	}

}
