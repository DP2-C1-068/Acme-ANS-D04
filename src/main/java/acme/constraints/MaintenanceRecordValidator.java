
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

		Date minimumInspectionDueDate;
		boolean correctInspectionDueDate;

		if (maintenanceRecord.isDraftMode() && maintenanceRecord.getMoment() != null) {
			minimumInspectionDueDate = MomentHelper.deltaFromMoment(maintenanceRecord.getMoment(), 1, ChronoUnit.MINUTES);
			correctInspectionDueDate = MomentHelper.isAfterOrEqual(maintenanceRecord.getInspectionDueDate(), minimumInspectionDueDate);

			super.state(context, correctInspectionDueDate, "inspectionDueDate", "acme.validation.maintenance-record.inspection-due-date.message");
		}

		result = !super.hasErrors(context);

		return result;
	}

}
