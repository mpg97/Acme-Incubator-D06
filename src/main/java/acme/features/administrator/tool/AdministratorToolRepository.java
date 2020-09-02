
package acme.features.administrator.tool;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.sector.Sector;
import acme.entities.tool.Tool;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorToolRepository extends AbstractRepository {

	@Query("select t from Tool t")
	Collection<Tool> findAllTools();

	@Query("select t from Tool t where t.id = ?1")
	Tool findToolById(int id);

	@Query("SELECT s.sector FROM Sector s")
	Collection<String> findAllSectorNamesAvailable();

	@Query("SELECT s FROM Sector s")
	Collection<Sector> findAllSectorsAvailable();

	@Query("SELECT s FROM Sector s WHERE s.sector = ?1")
	Sector findSectorByName(String name);

}
