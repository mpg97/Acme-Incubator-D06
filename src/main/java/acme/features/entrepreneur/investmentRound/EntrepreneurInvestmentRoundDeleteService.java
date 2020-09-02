
package acme.features.entrepreneur.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentround.InvestmentRound;
import acme.entities.investmentround.KindOfRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurInvestmentRoundDeleteService implements AbstractDeleteService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		InvestmentRound investment = this.repository.findInvestmentRoundById(id);
		return investment.getEntrepreneur().getId() == request.getPrincipal().getActiveRoleId();

	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "title", "description", "amount", "moreInfo", "kindRound", "finalMode", "entrepreneur.startUpName");
		model.setAttribute("kinds", KindOfRound.values());
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;
		request.getModel().setAttribute("kinds", KindOfRound.values());

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findInvestmentRoundById(id);

		return result;
	}
	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Integer applications;
		applications = this.repository.countActivityByInvestmentRoundId(entity.getId());
		Boolean hasApplicattions;
		hasApplicattions = applications != 0;
		if (!errors.hasErrors()) {
			errors.state(request, !hasApplicattions, "*", "employer.job.error.applications");
		}

		if (!errors.hasErrors()) {
			errors.state(request, entity.isFinalMode() == false, "*", "employer.job.error.finalMode");
		}

		Integer workProgrammes;
		workProgrammes = this.repository.countWorkProgrammeByInvestmentRoundId(entity.getId());
		Boolean hasWorkProgrammes;
		hasWorkProgrammes = workProgrammes != 0;

		if (!errors.hasErrors()) {
			errors.state(request, !hasWorkProgrammes, "*", "employer.job.error.work");
		}

	}

	@Override
	public void delete(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
