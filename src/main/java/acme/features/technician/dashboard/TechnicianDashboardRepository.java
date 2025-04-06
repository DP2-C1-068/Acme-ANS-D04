
package acme.features.technician.dashboard;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.aircraft.Aircraft;
import acme.entities.maintenance.MaintenanceRecord;

@Repository
public interface TechnicianDashboardRepository extends AbstractRepository {

	@Query("select count(mr) from MaintenanceRecord mr where mr.status = 'PENDING'")
	Integer numberOfMaintenanceRecordsPending();

	@Query("select count(mr) from MaintenanceRecord mr where mr.status = 'IN_PROGRESS'")
	Integer numberOfMaintenanceRecordsInProgress();

	@Query("select count(mr) from MaintenanceRecord mr where mr.status = 'COMPLETED'")
	Integer numberOfMaintenanceRecordsCompleted();

	@Query("select mr from MaintenanceRecord mr where mr.inspectionDueDate > current_date order by mr.inspectionDueDate asc")
	MaintenanceRecord nearestMaintenanceRecordByInspectionDueDate(PageRequest pageRequest);

	@Query("select a from Aircraft a join MaintenanceRecord mr on mr.aircraft = a join Involves i on i.maintenanceRecord = mr group by a order by count(i.task) desc")
	List<Aircraft> topFiveAircraftsWithMostTasks();

	@Query("select avg(mr.estimatedCost.amount) from MaintenanceRecord mr where mr.technician.id = :technicianId and mr.moment >= CURRENT_DATE - 365")
	Double averageMaintenanceRecordEstimatedCostLastYear(int technicianId);

	@Query("select min(mr.estimatedCost.amount) from MaintenanceRecord mr where mr.technician.id = :technicianId and mr.moment >= CURRENT_DATE - 365")
	Double minimumMaintenanceRecordEstimatedCostLastYear(int technicianId);

	@Query("select max(mr.estimatedCost.amount) from MaintenanceRecord mr where mr.technician.id = :technicianId and mr.moment >= CURRENT_DATE - 365")
	Double maximumMaintenanceRecordEstimatedCostLastYear(int technicianId);

	@Query("select stddev(mr.estimatedCost.amount) from MaintenanceRecord mr where mr.technician.id = :technicianId and mr.moment >= CURRENT_DATE - 365")
	Double deviationMaintenanceRecordEstimatedCostLastYear(int technicianId);

	@Query("select avg(t.estimatedDurationHours) from Task t where t.technician.id = :technicianId")
	Double averageTaskDuration(int technicianId);

	@Query("select min(t.estimatedDurationHours) from Task t where t.technician.id = :technicianId")
	Double minimumTaskDuration(int technicianId);

	@Query("select max(t.estimatedDurationHours) from Task t where t.technician.id = :technicianId")
	Double maximumTaskDuration(int technicianId);

	@Query("select stddev(t.estimatedDurationHours) from Task t where t.technician.id = :technicianId")
	Double deviationTaskDuration(int technicianId);

}
