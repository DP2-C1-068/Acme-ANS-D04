
package acme.constraints;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.validation.ConstraintValidatorContext;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.maintenance.MaintenanceRecord;

@Validator
public class MaintenanceRecordValidator extends AbstractValidator<ValidMaintenanceRecord, MaintenanceRecord> {

	// Internal state ---------------------------------------------------------

	// ConstraintValidator interface ------------------------------------------

	@Override
	protected void initialise(final ValidMaintenanceRecord maintenanceRecord) {
		assert maintenanceRecord != null;
	}

	@Override
	public boolean isValid(final MaintenanceRecord maintenanceRecord, final ConstraintValidatorContext context) {

		assert context != null;

		boolean result;

		if (maintenanceRecord == null)
			super.state(context, false, "*", "javax.validation.constraints.NotNull.message");
		else {
			Date minimumNextInspection;
			boolean correctNextInspection;

			if (maintenanceRecord.isDraftMode() && maintenanceRecord.getStartedAt() != null) {
				minimumNextInspection = MomentHelper.deltaFromCurrentMoment(1, ChronoUnit.MINUTES);
				correctNextInspection = MomentHelper.isAfterOrEqual(maintenanceRecord.getNextInspection(), minimumNextInspection);

				super.state(context, correctNextInspection, "nextInspection", "acme.validation.maintenanceRecord.next-inspection.message");
			}
		}

		result = !super.hasErrors(context);

		return result;
	}

}
