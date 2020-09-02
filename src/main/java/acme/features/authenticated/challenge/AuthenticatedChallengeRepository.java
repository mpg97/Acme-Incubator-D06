
package acme.features.authenticated.challenge;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import acme.entities.challenge.Challenge;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedChallengeRepository extends AbstractRepository {

	@Query("select c from Challenge c")
	Collection<Challenge> findAllChallenge();

	@Query("select c from Challenge c where c.id = ?1")
	Challenge findChallengeById(int id);

	@Query("SELECT c FROM Challenge c WHERE c.deadline > ?1")
	Collection<Challenge> findAllActivesChallenges(Date d);

}
