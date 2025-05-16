
package acme.features.administrator.bannedPassenger;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.bannedPassenger.BannedPassenger;

@Repository
public interface AdministratorBannedPassengerRepository extends AbstractRepository {

	@Query("select bp from BannedPassenger bp where bp.id = :id")
	BannedPassenger findBannedPassengerById(int id);

	@Query("select bp from BannedPassenger bp where bp.liftDate = null or bp.liftDate < bp.banDate")
	Collection<BannedPassenger> findBannedPassengersNotLiftedBan();

	@Query("select bp from BannedPassenger bp where bp.liftDate >= bp.banDate")
	Collection<BannedPassenger> findBannedPassengersLiftedBan();

	@Query("select bp from BannedPassenger bp where bp.banDate >= :oneMonthAgo")
	Collection<BannedPassenger> findBannedPassengersLastMonth(Date oneMonthAgo);

	@Query("select bp from BannedPassenger bp where bp.passport = :passport")
	BannedPassenger findBannedPassengerByPassport(String passport);

}
