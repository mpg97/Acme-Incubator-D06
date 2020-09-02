
package acme.features.authenticated.messages;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import acme.entities.messages.Message;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select n from Message n")
	Collection<Message> findAllMessages();

	@Query("select n from Message n where n.id = ?1")
	Message findMessageById(int id);

	@Query("SELECT m FROM Message m INNER JOIN DiscussionForum d ON d.id = m.discussionForum.id INNER JOIN InvestmentRound i ON d.investmentRound.id = i.id INNER JOIN Application a on "
		+ "a.investmentRound.id = i.id WHERE (a.investor.userAccount.id=?1 AND a.statement = 'ACCEPTED') OR i.entrepreneur.userAccount.id=?1")
	Collection<Message> findAllInvolvedMessages(int accountId);

	@Query("SELECT COUNT(m)>0 FROM Message m INNER JOIN DiscussionForum d ON d.id = m.discussionForum.id INNER JOIN InvestmentRound i ON d.investmentRound.id = i.id INNER JOIN Application a on "
		+ "a.investmentRound.id = i.id WHERE m.id = :message and ((a.investor.userAccount.id= :user AND a.statement = 'ACCEPTED') OR i.entrepreneur.userAccount.id= :user)")
	Boolean checkMessageInvolved(@Param("message") int messageId, @Param("user") int accountId);

	@Query("SELECT COUNT(d)>0 FROM DiscussionForum d INNER JOIN InvestmentRound i ON d.investmentRound.id = i.id INNER JOIN Application a on a.investmentRound.id = i.id WHERE"
		+ " d.id = :discussion and ((a.investor.userAccount.id= :user AND a.statement = 'ACCEPTED') OR i.entrepreneur.userAccount.id= :user)")
	Boolean checkDiscussionInvolved(@Param("discussion") int discussionId, @Param("user") int accountId);

	@Query("SELECT m FROM Message m WHERE m.discussionForum.id = ?1")
	Collection<Message> findMessagesByDiscussion(int discussionId);
}
