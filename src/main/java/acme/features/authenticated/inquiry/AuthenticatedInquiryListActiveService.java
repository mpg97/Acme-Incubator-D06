
package acme.features.authenticated.inquiry;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiry.Inquiry;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedInquiryListActiveService implements AbstractListService<Authenticated, Inquiry> {

	@Autowired
	AuthenticatedInquiryRepository repository;


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline");
		model.setAttribute("range", entity.getMinMoney().getAmount() + entity.getMinMoney().getCurrency() + "  -  " + entity.getMaxMoney().getAmount() + entity.getMaxMoney().getCurrency());

	}
	@Override
	public Collection<Inquiry> findMany(final Request<Inquiry> request) {
		assert request != null;

		return this.repository.findAllActivesInquiries(new Date(System.currentTimeMillis() - 1));

	}

}
