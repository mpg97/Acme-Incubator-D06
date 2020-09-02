
package acme.features.authenticated.overture;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import acme.entities.overture.Overture;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedOvertureRepository extends AbstractRepository {

	@Query("select t from Overture t")
	Collection<Overture> findAllOverture();

	@Query("select o from Overture o where o.id = ?1")
	Overture findOvertureById(int id);

	@Query("SELECT o FROM Overture o WHERE o.deadline > ?1")
	Collection<Overture> findAllActivesOvertures(Date d);

}
