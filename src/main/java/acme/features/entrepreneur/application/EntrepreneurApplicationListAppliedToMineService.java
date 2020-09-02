
package acme.features.entrepreneur.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurApplicationListAppliedToMineService implements AbstractListService<Entrepreneur, Application> {

	@Autowired
	EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creation", "statement", "moneyOffer", "investmentRound.ticker", "investor.firmName");

	}
	@Override
	public Collection<Application> findMany(final Request<Application> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		return this.repository.findApplicationByEntrepenurId(id);
	}

}
