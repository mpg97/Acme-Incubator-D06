
package acme.features.authenticated.entrepreneur;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Entrepreneur;
import acme.entities.sector.Sector;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedEntrepreneurRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select c from Entrepreneur c where c.userAccount.id = ?1")
	Entrepreneur findOneEntrepreneurByUserAccountId(int id);

	@Query("SELECT s FROM Sector s WHERE s.sector = ?1")
	Sector findSectorByName(String sector);

	@Query("SELECT s.sector FROM Sector s")
	Collection<String> findAllSectorNamesAvailable();

	@Query("SELECT s FROM Sector s")
	Collection<Sector> findAllSectorsAvailable();

}
