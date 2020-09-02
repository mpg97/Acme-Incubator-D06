
package acme.features.authenticated.entrepreneur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Entrepreneur;
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
public class AuthenticatedEntrepreneurUpdateService implements AbstractUpdateService<Authenticated, Entrepreneur> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedEntrepreneurRepository repository;


	// AbstractUpdateService<Authenticated, Entrepreneur> interface -----------------

	@Override
	public boolean authorise(final Request<Entrepreneur> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Entrepreneur> request, final Entrepreneur entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Entrepreneur> request, final Entrepreneur entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Entrepreneur> request, final Entrepreneur entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "startUpName", "sector.sector", "qualificationRecord", "skillRecord");
		model.setAttribute("sectorName", entity.getSector().getSector());
		model.setAttribute("sectorsAvailable", this.repository.findAllSectorNamesAvailable());

	}

	@Override
	public Entrepreneur findOne(final Request<Entrepreneur> request) {
		assert request != null;

		Entrepreneur result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneEntrepreneurByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Entrepreneur> request, final Entrepreneur entity) {
		assert request != null;
		assert entity != null;

		Sector sector = this.repository.findSectorByName(entity.getSector().getSector());
		entity.setSector(sector);

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Entrepreneur> request, final Response<Entrepreneur> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
