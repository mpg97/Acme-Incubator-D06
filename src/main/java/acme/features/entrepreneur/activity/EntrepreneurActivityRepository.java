
package acme.features.entrepreneur.activity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.activity.Activity;
import acme.entities.investmentround.InvestmentRound;
import acme.entities.workProgramme.WorkProgramme;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurActivityRepository extends AbstractRepository {

	@Query("select n from InvestmentRound n")
	Collection<InvestmentRound> findAllInvestmentRounds();

	@Query("select n from WorkProgramme n where n.id = ?1")
	WorkProgramme findWorkProgrammeById(int id);

	@Query("select n from InvestmentRound n where n.entrepreneur.id = ?1")
	Collection<InvestmentRound> findInvestmentRoundByEntrepreneurId(int id);

	@Query("select n from Activity n where n.workProgramme.investmentRound.entrepreneur.id = ?1")
	Collection<Activity> findActivityByEntrepenurId(int id);

	@Query("select n from Activity n where n.id = ?1")
	Activity findActivityById(int id);

	@Query("select SUM(n.budget.amount) from Activity n where n.workProgramme.investmentRound.id = ?1")
	Double findActivityByInvestmentId(int investmentId);

}
