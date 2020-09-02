
package acme.features.administrator.notice;

import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notice.Notice;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNoticeCreateService implements AbstractCreateService<Administrator, Notice> {

	@Autowired
	AdministratorNoticeRepository repository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Notice> request, final Notice entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "creation", "deadline", "header", "link");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("sure", "false");
		} else {
			request.transfer(model, "sure");
		}

	}

	@Override
	public Notice instantiate(final Request<Notice> request) {
		assert request != null;

		Notice result;

		result = new Notice();

		result.setCreation(new Date(System.currentTimeMillis() - 1));

		return result;
	}

	@Override
	public void validate(final Request<Notice> request, final Notice entity, final Errors errors) {

		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {

			Boolean isAfter = entity.getDeadline().after(new Date(System.currentTimeMillis() - 1));
			errors.state(request, isAfter, "deadline", "administrator.notice.deadline.before");
		}

		if (!entity.getLink().isEmpty()) {
			if (!errors.hasErrors("link")) {

				Boolean validUrl = true;

				String[] links = entity.getLink().split(";");

				for (String s : links) {
					validUrl &= this.isValid(s);
				}

				errors.state(request, validUrl, "link", "administrator.notice.link.valid");
			}
		}

		Boolean isAccepted = request.getModel().getBoolean("sure");
		errors.state(request, isAccepted, "sure", "administrator.notice.sure.must-accept");
	}

	@Override
	public void create(final Request<Notice> request, final Notice entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	private boolean isValid(final String url) {
		/* Try creating a valid URL */
		try {
			new URL(url).toURI();
			return true;
		}

		// If there was an Exception
		// while creating URL object
		catch (Exception e) {
			return false;
		}
	}

}
