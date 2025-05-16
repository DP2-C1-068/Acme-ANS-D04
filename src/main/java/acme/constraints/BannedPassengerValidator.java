
package acme.constraints;

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

		if (bannedPassenger.getLiftDate() != null && bannedPassenger.getBanDate() != null) {

			correctLiftedDate = MomentHelper.isAfterOrEqual(bannedPassenger.getLiftDate(), bannedPassenger.getBanDate());

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
