
package acme.features.administrator.overture;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.overture.Overture;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorOvertureRepository extends AbstractRepository {

	@Query("select t from Overture t")
	Collection<Overture> findAllOverture();

	@Query("select n from Overture n where n.id = ?1")
	Overture findOvertureById(int id);

}
