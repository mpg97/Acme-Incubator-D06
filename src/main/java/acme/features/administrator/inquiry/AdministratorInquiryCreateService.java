
package acme.features.administrator.inquiry;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.inquiry.Inquiry;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorInquiryCreateService implements AbstractCreateService<Administrator, Inquiry> {

	@Autowired
	AdministratorInquiryRepository repository;


	@Override
	public boolean authorise(final Request<Inquiry> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Inquiry> request, final Inquiry entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "paragraph", "creation", "deadline", "email", "minMoney", "maxMoney");

	}

	@Override
	public Inquiry instantiate(final Request<Inquiry> request) {
		assert request != null;

		Inquiry result;

		result = new Inquiry();
		result.setCreation(new Date(System.currentTimeMillis() - 1));

		return result;
	}

	@Override
	public void validate(final Request<Inquiry> request, final Inquiry entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			Boolean isAfter = entity.getDeadline().after(new Date(System.currentTimeMillis() - 1));
			errors.state(request, isAfter, "deadline", "administrator.inquiry.deadline.before");
		}

		if (!errors.hasErrors("minMoney")) {
			Boolean isPositive = entity.getMinMoney().getAmount() > 0;
			errors.state(request, isPositive, "minMoney", "administrator.inquiry.money.min");

			String currency = entity.getMinMoney().getCurrency();
			Boolean isEUR = !currency.isEmpty() && currency != null && currency.equals("€");
			errors.state(request, isEUR, "minMoney", "administrator.inquiry.currency.minMoney");

		}

		if (!errors.hasErrors("maxMoney")) {

			if (entity.getMinMoney() != null) {
				Boolean isBigger = entity.getMaxMoney().getAmount() > entity.getMinMoney().getAmount();
				errors.state(request, isBigger, "maxMoney", "administrator.inquiry.money.max");
			}

			Boolean isPositive = entity.getMaxMoney().getAmount() > 0;
			errors.state(request, isPositive, "maxMoney", "administrator.inquiry.money.min");

			String currency = entity.getMaxMoney().getCurrency();
			Boolean isEUR = !currency.isEmpty() && currency != null && currency.equals("€");
			errors.state(request, isEUR, "maxMoney", "administrator.inquiry.currency.maxMoney");
		}

	}

	@Override
	public void create(final Request<Inquiry> request, final Inquiry entity) {
		assert request != null;
		assert entity != null;

		entity.setCreation(new Date(System.currentTimeMillis() - 1));

		this.repository.save(entity);
	}

}
