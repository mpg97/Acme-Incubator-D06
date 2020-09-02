
package acme.features.authenticated.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		int accountId = request.getPrincipal().getAccountId();
		int messageId = request.getModel().getInteger("id");
		Boolean authorization = this.repository.checkMessageInvolved(messageId, accountId);
		return authorization;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		//		Authenticated author = this.repository.findAuthenticatedByUsername(request.getPrincipal().getUsername());

		request.unbind(entity, model, "title", "creationMoment", "tags", "body", "discussionForum");
		model.setAttribute("discussionId", entity.getDiscussionForum().getId());
		model.setAttribute("authorName", entity.getAuthor().getUserAccount().getUsername());
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMessageById(id);

		return result;
	}

}
