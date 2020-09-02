
package acme.features.administrator.overture;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overture.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorOvertureUpdateService implements AbstractUpdateService<Administrator, Overture> {

	@Autowired
	private AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "paragraph", "creation", "deadline", "email", "minMoney", "maxMoney");

	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		assert request != null;

		Overture result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOvertureById(id);

		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			Boolean isAfter = entity.getDeadline().after(new Date(System.currentTimeMillis() - 1));
			errors.state(request, isAfter, "deadline", "administrator.overture.deadline.before");
		}

		if (!errors.hasErrors("minMoney")) {
			Boolean isPositive = entity.getMinMoney().getAmount() > 0;
			errors.state(request, isPositive, "minMoney", "administrator.overture.money.min");

			String currency = entity.getMinMoney().getCurrency();
			Boolean isEUR = !currency.isEmpty() && currency != null && currency.equals("€");
			errors.state(request, isEUR, "minMoney", "administrator.overture.currency.minMoney");

		}

		if (!errors.hasErrors("maxMoney")) {

			if (entity.getMinMoney() != null) {
				Boolean isBigger = entity.getMaxMoney().getAmount() > entity.getMinMoney().getAmount();
				errors.state(request, isBigger, "maxMoney", "administrator.overture.money.max");
			}

			Boolean isPositive = entity.getMaxMoney().getAmount() > 0;
			errors.state(request, isPositive, "maxMoney", "administrator.overture.money.min");

			String currency = entity.getMaxMoney().getCurrency();
			Boolean isEUR = !currency.isEmpty() && currency != null && currency.equals("€");
			errors.state(request, isEUR, "maxMoney", "administrator.overture.currency.maxMoney");
		}

	}

	@Override
	public void update(final Request<Overture> request, final Overture entity) {
		assert request != null;
		assert entity != null;

		entity.setCreation(new Date(System.currentTimeMillis() - 1));

		this.repository.save(entity);
	}

}
