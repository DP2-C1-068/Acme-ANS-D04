
package acme.constraints;

import java.util.Date;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.bannedPassenger.BannedPassenger;
import acme.features.administrator.bannedPassenger.AdministratorBannedPassengerRepository;

@Validator
public class BannedPassengerValidator extends AbstractValidator<ValidBannedPassenger, BannedPassenger> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AdministratorBannedPassengerRepository repository;
	// ConstraintValidator interface ------------------------------------------


	@Override
	protected void initialise(final ValidBannedPassenger bannedPassenger) {
		assert bannedPassenger != null;
	}

	@Override
	public boolean isValid(final BannedPassenger bannedPassenger, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		boolean correctLiftedDate;
		boolean correctBirthDate;
		Date currentMoment;

		if (bannedPassenger.getLiftDate() != null) {

			currentMoment = MomentHelper.getCurrentMoment();
			correctLiftedDate = MomentHelper.isBefore(currentMoment, bannedPassenger.getLiftDate());

			super.state(context, correctLiftedDate, "liftDate", "acme.validation.banned-passenger.lift-date.message");
		}

		if (bannedPassenger.getBirthDate() != null && bannedPassenger.getBanDate() != null) {
			correctBirthDate = MomentHelper.isBeforeOrEqual(bannedPassenger.getBirthDate(), bannedPassenger.getBanDate());
			super.state(context, correctBirthDate, "birthDate", "acme.validation.banned-passenger.birth-date.message");
		}

		// Validación de duplicado
		BannedPassenger existingBannedPassenger = this.repository.findBannedPassengerByPassport(bannedPassenger.getPassport());
		boolean uniqueBannedPassenger = existingBannedPassenger == null || existingBannedPassenger.equals(bannedPassenger);
		super.state(context, uniqueBannedPassenger, "passport", "acme.validation.banned-passenger.duplicated-passport.message");

		result = !super.hasErrors(context);

		return result;
	}

}
