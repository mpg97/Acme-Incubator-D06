
package acme.features.authenticated.discussionForum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import acme.entities.discussionForum.DiscussionForum;
import acme.entities.messages.Message;
import acme.framework.repositories.AbstractRepository;

public interface AuthenticatedDiscussionForumRepository extends AbstractRepository {

	@Query("select n from DiscussionForum n")
	Collection<DiscussionForum> findAllDiscussionForums();

	@Query("select n from DiscussionForum n where n.id=?1")
	DiscussionForum findDiscussionForumById(int id);

	//SELECT distinct * FROM `acme-incubator`.discussion_forum d inner join investment_round i on d.investment_round_id = i.id inner join application a on a.investment_round_id = i.id
	//and a.statement = 'ACCEPTED' where i.entrepreneur_id = 364;
	@Query("SELECT DISTINCT d FROM DiscussionForum d INNER JOIN InvestmentRound i ON d.investmentRound.id = i.id INNER JOIN Application a on a.investmentRound.id = i.id WHERE"
		+ " (a.investor.userAccount.id=?1 AND a.statement = 'ACCEPTED') OR i.entrepreneur.userAccount.id=?1")
	Collection<DiscussionForum> findAllInvolvedDiscussionForums(int accountId);

	@Query("SELECT COUNT(d)>0 FROM DiscussionForum d INNER JOIN InvestmentRound i ON d.investmentRound.id = i.id INNER JOIN Application a on a.investmentRound.id = i.id WHERE"
		+ " d.id = :discussion AND ((a.investor.userAccount.id= :user AND a.statement = 'ACCEPTED') OR i.entrepreneur.userAccount.id= :user)")
	Boolean checkInvolved(@Param("discussion") int discussionId, @Param("user") int accountId);

	@Query("SELECT m FROM Message m WHERE m.discussionForum.id=?1")
	Collection<Message> findMessagesByDiscussion(int discussionId);

	@Query("SELECT COUNT(m)>0 FROM Message m WHERE m.discussionForum.id=?1")
	Boolean hasMessages(int discussionId);

}
