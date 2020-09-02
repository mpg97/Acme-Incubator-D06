
package acme.features.authenticated.inquiry;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiry.Inquiry;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInquiryShowService implements AbstractShowService<Authenticated, Inquiry> {

	@Autowired
	private AuthenticatedInquiryRepository repository;


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		int inquiryId = request.getModel().getInteger("id");
		Inquiry inquiry = this.repository.findInquiryById(inquiryId);
		boolean res = inquiry.getDeadline().compareTo(new Date(System.currentTimeMillis() - 1)) > 0;

		return res;
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creation", "deadline", "paragraph", "minMoney", "maxMoney", "email");

	}

	@Override
	public Inquiry findOne(final Request<Inquiry> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");

		return this.repository.findInquiryById(id);

	}

}
