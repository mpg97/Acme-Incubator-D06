
package acme.features.investor.message;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.discussionForum.DiscussionForum;
import acme.entities.messages.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InvestorMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findMessageById(int id);

	@Query("SELECT COUNT(d)>0 FROM DiscussionForum d INNER JOIN InvestmentRound i ON d.investmentRound.id = i.id INNER JOIN Application a on a.investmentRound.id = i.id WHERE a.investor.userAccount.id= :user AND d.id= :discussion AND a.statement = 'ACCEPTED'")
	Boolean checkAccess(@Param("discussion") int discussionId, @Param("user") int accountId);

	@Query("SELECT d FROM DiscussionForum d WHERE d.id =?1")
	DiscussionForum findDiscussionById(int discussionId);

}
