
package acme.features.entrepreneur.workProgramme;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investmentround.InvestmentRound;
import acme.entities.workProgramme.WorkProgramme;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EntrepreneurWorkProgrammeRepository extends AbstractRepository {

	@Query("select n from InvestmentRound n")
	Collection<InvestmentRound> findAllInvestmentRounds();

	@Query("select n from InvestmentRound n where n.entrepreneur.id = ?1")
	Collection<InvestmentRound> findInvestmentRoundByEntrepreneurId(int id);

	@Query("select n from WorkProgramme n where n.investmentRound.entrepreneur.id  = ?1")
	Collection<WorkProgramme> findWorkProgrammeByEntrepenurId(int id);

	@Query("select n from WorkProgramme n where n.id = ?1")
	WorkProgramme findWorkProgrammeById(int id);

	@Query("select n from InvestmentRound n where n.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select count(n) from Activity n where n.workProgramme.id = ?1")
	Integer countActivityByInvestmentRoundId(int id);
}
