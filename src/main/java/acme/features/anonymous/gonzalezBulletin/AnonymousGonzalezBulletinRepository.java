
package acme.features.anonymous.gonzalezBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.GonzalezBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousGonzalezBulletinRepository extends AbstractRepository {

	@Query("select p from GonzalezBulletin p")
	Collection<GonzalezBulletin> findAllGonzalezBulletin();

	@Query("select n from GonzalezBulletin n where n.id = ?1")
	GonzalezBulletin findGonzalezBulletinById(int id);

}
