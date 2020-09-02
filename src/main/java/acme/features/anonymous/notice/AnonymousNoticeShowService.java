
package acme.features.anonymous.notice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notice.Notice;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousNoticeShowService implements AbstractShowService<Anonymous, Notice> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousNoticeRepository repository;


	// AbstractShowService<Administrator, Notice> interface --------------

	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;

		int noticeId = request.getModel().getInteger("id");
		Notice notice = this.repository.findNoticeById(noticeId);
		boolean res = notice.getDeadline().compareTo(new Date(System.currentTimeMillis() - 1)) > 0;

		return res;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "creation", "deadline", "header", "link");

	}

	@Override
	public Notice findOne(final Request<Notice> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");

		return this.repository.findNoticeById(id);
	}

}
