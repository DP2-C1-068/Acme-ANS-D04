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

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.principals.Administrator;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.bannedPassenger.BannedPassenger;

@GuiService
public class AdministratorBannedPassengerListService extends AbstractGuiService<Administrator, BannedPassenger> {

	@Autowired
	AdministratorBannedPassengerRepository repository;
	// AbstractGuiService interface -------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<BannedPassenger> passengers;
		Date oneMonthAgo = MomentHelper.deltaFromMoment(MomentHelper.getCurrentMoment(), -1, ChronoUnit.MONTHS);
		Date currentMoment = MomentHelper.getCurrentMoment();

		if (super.getRequest().hasData("liftedBan", boolean.class))
			passengers = this.repository.findBannedPassengersLiftedBan(currentMoment);
		else if (super.getRequest().hasData("lastMonth", boolean.class))
			passengers = this.repository.findBannedPassengersLastMonth(oneMonthAgo);
		else
			passengers = this.repository.findBannedPassengersNotLiftedBan(currentMoment);

		super.getBuffer().addData(passengers);
	}

	@Override
	public void unbind(final BannedPassenger bannedPassenger) {
		Dataset dataset;

		dataset = super.unbindObject(bannedPassenger, "name", "passport", "banDate", "liftDate");
		super.addPayload(dataset, bannedPassenger, //
			"birthDate", "nationality", "reason");

		super.getResponse().addData(dataset);
	}

}
