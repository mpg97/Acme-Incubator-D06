
package acme.features.authenticated.discussionForum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.discussionForum.DiscussionForum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedDiscussionForumShowService implements AbstractShowService<Authenticated, DiscussionForum> {

	@Autowired
	private AuthenticatedDiscussionForumRepository repository;


	@Override
	public boolean authorise(final Request<DiscussionForum> request) {
		assert request != null;
		int discussionId = request.getModel().getInteger("id");
		int accountId = request.getPrincipal().getAccountId();
		Boolean authorization = this.repository.checkInvolved(discussionId, accountId);
		return authorization;
	}

	@Override
	public void unbind(final Request<DiscussionForum> request, final DiscussionForum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Boolean hasMessage = this.repository.hasMessages(request.getModel().getInteger("id"));
		request.unbind(entity, model, "investmentRound.ticker", "investmentRound.title", "investmentRound.kindRound", "investmentRound.entrepreneur.startUpName");
		model.setAttribute("hasMessages", hasMessage);

	}

	@Override
	public DiscussionForum findOne(final Request<DiscussionForum> request) {
		assert request != null;

		DiscussionForum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findDiscussionForumById(id);

		return result;
	}

}
