
package acme.features.anonymous.patonBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.PatonBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousPatonBulletinRepository extends AbstractRepository {

	@Query("select p from PatonBulletin p")
	Collection<PatonBulletin> findAllPatonBulletin();

	@Query("select n from PatonBulletin n where n.id = ?1")
	PatonBulletin findPatonBulletinById(int id);

}
