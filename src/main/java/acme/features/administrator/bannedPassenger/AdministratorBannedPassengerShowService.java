/*
 * AdministratorBannedPassengerShowService.java
 *
 * Copyright (C) 2012-2025 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.bannedPassenger;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.principals.Administrator;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.bannedPassenger.BannedPassenger;

@GuiService
public class AdministratorBannedPassengerShowService extends AbstractGuiService<Administrator, BannedPassenger> {

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
		int id;

		id = super.getRequest().getData("id", int.class);
		bannedPassenger = this.repository.findBannedPassengerById(id);

		super.getBuffer().addData(bannedPassenger);
	}

	@Override
	public void unbind(final BannedPassenger bannedPassenger) {
		Dataset dataset;

		dataset = super.unbindObject(bannedPassenger, "name", "passport", "banDate", "liftDate", "birthDate", "nationality", "reason");

		super.getResponse().addData(dataset);
	}

}
