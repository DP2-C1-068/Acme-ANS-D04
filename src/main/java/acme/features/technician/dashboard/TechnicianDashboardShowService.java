/*
 * TechnicianDashboardShowService.java
 *
 * Copyright (C) 2012-2025 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.technician.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import acme.client.components.models.Dataset;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.aircraft.Aircraft;
import acme.entities.maintenance.MaintenanceRecord;
import acme.forms.Dashboard;
import acme.realms.Technician;

@GuiService
public class TechnicianDashboardShowService extends AbstractGuiService<Technician, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private TechnicianDashboardRepository repository;

	// AbstractGuiService interface -------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Dashboard dashboard;
		int technicianId;

		technicianId = super.getRequest().getPrincipal().getActiveRealm().getId();

		Integer numberOfMaintenanceRecordsPending;
		Integer numberOfMaintenanceRecordsInProgress;
		Integer numberOfMaintenanceRecordsCompleted;

		MaintenanceRecord nearestMaintenanceRecordByInspectionDueDate;
		List<Aircraft> topFiveAircraftsWithMostTasks;

		Double averageMaintenanceRecordEstimatedCostLastYear;
		Double minimumMaintenanceRecordEstimatedCostLastYear;
		Double maximumMaintenanceRecordEstimatedCostLastYear;
		Double deviationMaintenanceRecordEstimatedCostLastYear;

		Double averageTaskDuration;
		Double minimumTaskDuration;
		Double maximumTaskDuration;
		Double deviationTaskDuration;

		numberOfMaintenanceRecordsPending = this.repository.numberOfMaintenanceRecordsPending(technicianId);
		numberOfMaintenanceRecordsInProgress = this.repository.numberOfMaintenanceRecordsInProgress(technicianId);
		numberOfMaintenanceRecordsCompleted = this.repository.numberOfMaintenanceRecordsCompleted(technicianId);

		nearestMaintenanceRecordByInspectionDueDate = this.repository.nearestMaintenanceRecordByInspectionDueDate(technicianId, PageRequest.of(0, 1));
		topFiveAircraftsWithMostTasks = this.repository.topFiveAircraftsWithMostTasks(PageRequest.of(0, 5));

		averageMaintenanceRecordEstimatedCostLastYear = this.repository.averageMaintenanceRecordEstimatedCostLastYear(technicianId);
		minimumMaintenanceRecordEstimatedCostLastYear = this.repository.minimumMaintenanceRecordEstimatedCostLastYear(technicianId);
		maximumMaintenanceRecordEstimatedCostLastYear = this.repository.maximumMaintenanceRecordEstimatedCostLastYear(technicianId);
		deviationMaintenanceRecordEstimatedCostLastYear = this.repository.deviationMaintenanceRecordEstimatedCostLastYear(technicianId);

		averageTaskDuration = this.repository.averageTaskDuration(technicianId);
		minimumTaskDuration = this.repository.minimumTaskDuration(technicianId);
		maximumTaskDuration = this.repository.maximumTaskDuration(technicianId);
		deviationTaskDuration = this.repository.deviationTaskDuration(technicianId);

		dashboard = new Dashboard();

		dashboard.setNumberOfMaintenanceRecordsPending(numberOfMaintenanceRecordsPending);
		dashboard.setNumberOfMaintenanceRecordsInProgress(numberOfMaintenanceRecordsInProgress);
		dashboard.setNumberOfMaintenanceRecordsCompleted(numberOfMaintenanceRecordsCompleted);

		dashboard.setNearestMaintenanceRecordByInspectionDueDate(nearestMaintenanceRecordByInspectionDueDate);
		dashboard.setTopFiveAircraftsWithMostTasks(topFiveAircraftsWithMostTasks);

		dashboard.setAverageMaintenanceRecordEstimatedCostLastYear(averageMaintenanceRecordEstimatedCostLastYear);
		dashboard.setMinimumMaintenanceRecordEstimatedCostLastYear(minimumMaintenanceRecordEstimatedCostLastYear);
		dashboard.setMaximumMaintenanceRecordEstimatedCostLastYear(maximumMaintenanceRecordEstimatedCostLastYear);
		dashboard.setDeviationMaintenanceRecordEstimatedCostLastYear(deviationMaintenanceRecordEstimatedCostLastYear);

		dashboard.setAverageTaskDuration(averageTaskDuration);
		dashboard.setMinimumTaskDuration(minimumTaskDuration);
		dashboard.setMaximumTaskDuration(maximumTaskDuration);
		dashboard.setDeviationTaskDuration(deviationTaskDuration);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final Dashboard dashboard) {
		Dataset dataset;

		System.out.println(dashboard.getTopFiveAircraftsWithMostTasks());
		dataset = super.unbindObject(dashboard, //
			"numberOfMaintenanceRecordsPending", "numberOfMaintenanceRecordsInProgress", //
			"numberOfMaintenanceRecordsCompleted", "nearestMaintenanceRecordByInspectionDueDate", // 
			"topFiveAircraftsWithMostTasks", "averageMaintenanceRecordEstimatedCostLastYear", //
			"minimumMaintenanceRecordEstimatedCostLastYear", "maximumMaintenanceRecordEstimatedCostLastYear", //
			"deviationMaintenanceRecordEstimatedCostLastYear", "averageTaskDuration", //
			"minimumTaskDuration", "maximumTaskDuration", "deviationTaskDuration");

		super.getResponse().addData(dataset);
	}

}
