
package acme.features.administrator.inquiry;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiry.Inquiry;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorInquiryListService implements AbstractListService<Administrator, Inquiry> {

	@Autowired
	AdministratorInquiryRepository repository;


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

		Collection<Inquiry> result;

		result = this.repository.findAllInquiry();

		return result;
	}

}
