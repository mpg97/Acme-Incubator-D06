
package acme.features.entrepreneur.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configurations.Configuration;
import acme.entities.discussionForum.DiscussionForum;
import acme.entities.messages.Message;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurMessagePostService implements AbstractCreateService<Entrepreneur, Message> {

	@Autowired
	EntrepreneurMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		int discussion = request.getModel().getInteger("discussionId");
		Boolean checkAccess = this.repository.checkAccess(discussion, request.getPrincipal().getAccountId());
		return checkAccess;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "discussionForum", "author");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tags", "body", "creationMoment");
		model.setAttribute("discussionId", entity.getDiscussionForum().getId());
		model.setAttribute("authorName", entity.getAuthor().getUserAccount().getUsername());

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Message result = new Message();
		Authenticated author = this.repository.findAuthenticatedByUsername(request.getPrincipal().getUsername());
		DiscussionForum discussion = this.repository.findDiscussionById(request.getModel().getInteger("discussionId"));

		result.setCreationMoment(new Date(System.currentTimeMillis() - 1));
		result.setAuthor(author);
		result.setDiscussionForum(discussion);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		errors.state(request, request.getModel().getBoolean("confirm"), "confirm", "entrepreneur.message.form.label.error.must-confirm");

		if (request.getModel().getBoolean("confirm") && !errors.hasErrors()) {
			Double totalSum = 0d;

			List<String> spamList = new ArrayList<String>();

			String language = request.getLocale().getLanguage();
			Configuration config = this.repository.findConfigurationByLanguage(language);

			spamList.addAll(Arrays.asList(config.getSpamwords().toLowerCase().replace(", ", ",").split(",")));

			String formContent = entity.getTitle().toLowerCase() + entity.getBody().toLowerCase();

			if (entity.getTags() != null) {
				formContent += entity.getTags().toLowerCase();
			}

			for (String spam : spamList) {
				int sum = 0;
				sum += formContent.split(spam, -1).length - 1;
				totalSum += sum * spam.length();
			}

			Double sizeContent = formContent.length() * 1.0;

			Boolean checkIsSpam = totalSum / sizeContent < config.getSpamThreshold();

			errors.state(request, checkIsSpam, "confirm", "investor.message.form.error.is-spam");
		}

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
