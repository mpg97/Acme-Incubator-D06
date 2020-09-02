
package acme.features.bookkeeper.investmentRound;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.investmentround.InvestmentRound;
import acme.framework.repositories.AbstractRepository;

public interface BookkeeperInvestmentRoundRepository extends AbstractRepository {

	@Query("select n from InvestmentRound n")
	Collection<InvestmentRound> findAllInvestmentRounds();

	@Query("select n from InvestmentRound n where n.id = ?1")
	InvestmentRound findInvestmentRoundById(int id);

	@Query("SELECT DISTINCT i FROM InvestmentRound i INNER JOIN AccountingRecord as a ON a.investmentRound.id = i.id WHERE a.bookkeeper.userAccount.id !=?1")
	Collection<InvestmentRound> findAllNotInvolvedInvestmentRounds(int accountId);

	@Query("SELECT DISTINCT i FROM InvestmentRound i INNER JOIN AccountingRecord as a ON a.investmentRound.id = i.id WHERE a.bookkeeper.userAccount.id =?1")
	Collection<InvestmentRound> findAllInvolvedInvestmentRounds(int accountId);

}
