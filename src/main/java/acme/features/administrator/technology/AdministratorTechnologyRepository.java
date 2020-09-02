
package acme.features.administrator.technology;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.sector.Sector;
import acme.entities.technology.Technology;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorTechnologyRepository extends AbstractRepository {

	@Query("select t from Technology t")
	Collection<Technology> findAllTechnologies();

	@Query("select t from Technology t where t.id = ?1")
	Technology findTechnologyById(int id);

	@Query("SELECT s.sector FROM Sector s")
	Collection<String> findAllSectorNamesAvailable();

	@Query("SELECT s FROM Sector s")
	Collection<Sector> findAllSectorsAvailable();

	@Query("SELECT s FROM Sector s WHERE s.sector = ?1")
	Sector findSectorByName(String name);

}
