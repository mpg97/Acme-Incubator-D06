
package acme.features.entrepreneur.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.investmentround.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.repositories.AbstractRepository;

public interface EntrepreneurInvestmentRoundRepository extends AbstractRepository {

	@Query("select n from InvestmentRound n")
	Collection<InvestmentRound> findAllInvestmentRounds();

	@Query("select n from InvestmentRound n where n.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("select n from InvestmentRound n where n.entrepreneur.id = ?1")
	Collection<InvestmentRound> findInvestmentRoundByEntrepreneurId(int id);

	@Query("select n from InvestmentRound n where n.entrepreneur.id = ?1 ORDER BY n.ticker ASC")
	Collection<InvestmentRound> findInvestmentRoundByEntrepreneurIdOrderedByTicker(int id);

	@Query("select n from InvestmentRound n where n.entrepreneur.id = ?1 ORDER BY n.creationDate ASC")
	Collection<InvestmentRound> findInvestmentRoundByEntrepreneurIdOrderedByCreationDate(int id);

	@Query("select t from Entrepreneur t where t.userAccount.id = ?1")
	Entrepreneur findEntrepreneurByUserAccountId(int Id);

	@Query("select count(n) from Activity n where n.workProgramme.investmentRound.id = ?1")
	Integer countActivityByInvestmentRoundId(int id);

	@Query("select count(n) from WorkProgramme n where n.investmentRound.id = ?1")
	Integer countWorkProgrammeByInvestmentRoundId(int id);

}
