
package acme.features.entrepreneur.investmentRound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentround.InvestmentRound;
import acme.entities.investmentround.KindOfRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurInvestmentRoundShowService implements AbstractShowService<Entrepreneur, InvestmentRound> {

	@Autowired
	private EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		InvestmentRound investment = this.repository.findInvestmentRoundById(id);
		return investment.getEntrepreneur().getId() == request.getPrincipal().getActiveRoleId();
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

}
