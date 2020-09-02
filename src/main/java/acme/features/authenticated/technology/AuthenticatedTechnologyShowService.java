
package acme.features.authenticated.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.technology.Technology;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedTechnologyShowService implements AbstractShowService<Authenticated, Technology> {

	@Autowired
	private AuthenticatedTechnologyRepository repository;


	@Override
	public boolean authorise(final Request<Technology> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Technology> request, final Technology entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investorName", "description", "web", "email", "openSource", "stars");
		model.setAttribute("sectorName", entity.getSector().getSector());

	}

	@Override
	public Technology findOne(final Request<Technology> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");

		return this.repository.findTechnologyById(id);
	}

}
