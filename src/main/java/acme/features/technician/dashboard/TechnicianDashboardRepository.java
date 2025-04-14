
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

	@Query("select count(mr) from MaintenanceRecord mr where mr.status = acme.entities.maintenance.MaintenanceStatus.PENDING and mr.technician.id = :technicianId")
	Integer numberOfMaintenanceRecordsPending(int technicianId);

	@Query("select count(mr) from MaintenanceRecord mr where mr.status = acme.entities.maintenance.MaintenanceStatus.IN_PROGRESS and mr.technician.id = :technicianId")
	Integer numberOfMaintenanceRecordsInProgress(int technicianId);

	@Query("select count(mr) from MaintenanceRecord mr where mr.status = acme.entities.maintenance.MaintenanceStatus.COMPLETED and mr.technician.id = :technicianId")
	Integer numberOfMaintenanceRecordsCompleted(int technicianId);

	@Query("select mr from MaintenanceRecord mr where mr.technician.id = :technicianId order by abs(timestampdiff(second, current_timestamp, mr.inspectionDueDate))")
	MaintenanceRecord nearestMaintenanceRecordByInspectionDueDate(int technicianId, PageRequest pageRequest);

	@Query("select i.maintenanceRecord.aircraft from Involves i group by i.maintenanceRecord.aircraft order by count(i.task) desc")
	List<Aircraft> topFiveAircraftsWithMostTasks(PageRequest pageRequest);

	@Query("select avg(mr.estimatedCost.amount) from MaintenanceRecord mr where mr.technician.id = :technicianId and mr.moment >= CURRENT_DATE - 365")
	Double averageMaintenanceRecordEstimatedCostLastYear(int technicianId);

	@Query("select min(mr.estimatedCost.amount) from MaintenanceRecord mr where mr.technician.id = :technicianId and mr.moment >= CURRENT_DATE - 365")
	Double minimumMaintenanceRecordEstimatedCostLastYear(int technicianId);

	@Query("select max(mr.estimatedCost.amount) from MaintenanceRecord mr where mr.technician.id = :technicianId and mr.moment >= CURRENT_DATE - 365")
	Double maximumMaintenanceRecordEstimatedCostLastYear(int technicianId);

	@Query("select stddev(mr.estimatedCost.amount) from MaintenanceRecord mr where mr.technician.id = :technicianId and mr.moment >= CURRENT_DATE - 365")
	Double deviationMaintenanceRecordEstimatedCostLastYear(int technicianId);

	@Query("select avg(i.task.estimatedDurationHours) from Involves i where i.maintenanceRecord.technician.id = :technicianId")
	Double averageTaskDuration(int technicianId);

	@Query("select min(i.task.estimatedDurationHours) from Involves i where i.maintenanceRecord.technician.id = :technicianId")
	Double minimumTaskDuration(int technicianId);

	@Query("select max(i.task.estimatedDurationHours) from Involves i where i.maintenanceRecord.technician.id = :technicianId")
	Double maximumTaskDuration(int technicianId);

	@Query("select stddev(i.task.estimatedDurationHours) from Involves i where i.maintenanceRecord.technician.id = :technicianId")
	Double deviationTaskDuration(int technicianId);

}
