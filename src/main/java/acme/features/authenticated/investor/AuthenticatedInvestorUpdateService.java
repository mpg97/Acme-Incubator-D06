
package acme.features.authenticated.investor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Investor;
import acme.entities.sector.Sector;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedInvestorUpdateService implements AbstractUpdateService<Authenticated, Investor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedInvestorRepository repository;


	// AbstractUpdateService<Authenticated, Investor> interface -----------------

	@Override
	public boolean authorise(final Request<Investor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Investor> request, final Investor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Investor> request, final Investor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Investor> request, final Investor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "sector.sector", "profile");
		model.setAttribute("sectorName", entity.getSector().getSector());
		model.setAttribute("sectorsAvailable", this.repository.findAllSectorNamesAvailable());

	}

	@Override
	public Investor findOne(final Request<Investor> request) {
		assert request != null;

		Investor result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneInvestorByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Investor> request, final Investor entity) {
		assert request != null;
		assert entity != null;

		Sector sector = this.repository.findSectorByName(entity.getSector().getSector());
		entity.setSector(sector);

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Investor> request, final Response<Investor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
