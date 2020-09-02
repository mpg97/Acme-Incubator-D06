
package acme.features.entrepreneur.activity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.activity.Activity;
import acme.entities.roles.Entrepreneur;
import acme.entities.workProgramme.WorkProgramme;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurActivityCreateService implements AbstractCreateService<Entrepreneur, Activity> {

	@Autowired
	EntrepreneurActivityRepository repository;


	@Override
	public boolean authorise(final Request<Activity> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "workProgramme");
	}

	@Override
	public void unbind(final Request<Activity> request, final Activity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("workProgrammeId", entity.getWorkProgramme().getId());
		request.unbind(entity, model, "title", "start", "end", "budget");
	}

	@Override
	public Activity instantiate(final Request<Activity> request) {

		Activity result;
		result = new Activity();

		WorkProgramme workProgramme;
		int workProgrammeId;
		workProgrammeId = request.getModel().getInteger("workProgrammeId");
		workProgramme = this.repository.findWorkProgrammeById(workProgrammeId);
		result.setWorkProgramme(workProgramme);

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setStart(moment);

		return result;
	}

	@Override
	public void validate(final Request<Activity> request, final Activity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("budget")) {
			String currency = entity.getBudget().getCurrency();
			Boolean isEUR = currency.equals("€");
			errors.state(request, isEUR, "budget", "administrator.activity.currency.budget");
		}

		if (!errors.hasErrors("budget")) {
			Double amount = entity.getBudget().getAmount();
			int investmentId = entity.getWorkProgramme().getInvestmentRound().getId();
			Double activities = this.repository.findActivityByInvestmentId(investmentId);

			Double investmentBudget = entity.getWorkProgramme().getInvestmentRound().getAmount().getAmount();
			Boolean isEUR = amount + activities < investmentBudget;
			errors.state(request, isEUR, "budget", "administrator.activity.currency.much");
		}

	}

	@Override
	public void create(final Request<Activity> request, final Activity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
