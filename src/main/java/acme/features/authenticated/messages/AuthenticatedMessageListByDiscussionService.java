
package acme.features.authenticated.messages;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListByDiscussionService implements AbstractListService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		int discussionId = request.getModel().getInteger("discussionId");
		int accountId = request.getPrincipal().getAccountId();
		Boolean authorization = this.repository.checkDiscussionInvolved(discussionId, accountId);
		return authorization;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "tags");
		model.setAttribute("authorName", entity.getAuthor().getUserAccount().getUsername());

	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		int discussionId = request.getModel().getInteger("discussionId");
		return this.repository.findMessagesByDiscussion(discussionId);
	}
}
