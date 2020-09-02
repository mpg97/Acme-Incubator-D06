
package acme.features.entrepreneur.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.application.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurApplicationRepository extends AbstractRepository {

	@Query("select t from Application t")
	Collection<Application> findAllApplication();

	@Query("select t from Application t where t.id = ?1")
	Application findApplicationById(int id);

	@Query("select a from Application a where a.investmentRound.entrepreneur.id = ?1")
	Collection<Application> findApplicationByEntrepenurId(int id);

	@Query("select a from Application a where a.investmentRound.entrepreneur.id = ?1 order by a.ticker asc")
	Collection<Application> findApplicationByEntrepenurIdGroupedByTicker(int id);

	@Query("select a from Application a where a.investmentRound.entrepreneur.id = ?1 order by a.creation desc")
	Collection<Application> findApplicationByEntrepenurIdGroupedByCreationDate(int id);

}
