
package acme.features.bookkeeper.accountingRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import acme.entities.accountingRecords.AccountingRecord;
import acme.framework.repositories.AbstractRepository;

public interface BookkeeperAccountingRecordRepository extends AbstractRepository {

	@Query("select n from AccountingRecord n")
	Collection<AccountingRecord> findAllAccountingRecords();

	@Query("select n from AccountingRecord n where n.id = ?1")
	AccountingRecord findAccountingRecordById(int id);

	@Query("SELECT a FROM AccountingRecord a WHERE a.bookkeeper.userAccount.id= :user AND a.investmentRound.id= :investment")
	Collection<AccountingRecord> findAllAccountingRecordsByInvestment(@Param("user") int accountId, @Param("investment") int investmentId);

}
