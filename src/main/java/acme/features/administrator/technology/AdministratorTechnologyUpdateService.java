
package acme.features.administrator.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.sector.Sector;
import acme.entities.technology.Technology;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorTechnologyUpdateService implements AbstractUpdateService<Administrator, Technology> {

	@Autowired
	AdministratorTechnologyRepository repository;


	@Override
	public boolean authorise(final Request<Technology> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Technology> request, final Technology entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Technology> request, final Technology entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investorName", "description", "web", "email", "openSource", "stars", "sector.sector");
		model.setAttribute("sectorName", entity.getSector().getSector());
		model.setAttribute("sectorsAvailable", this.repository.findAllSectorNamesAvailable());

	}

	@Override
	public Technology findOne(final Request<Technology> request) {
		assert request != null;
		request.getModel().setAttribute("sectorsAvailable", this.repository.findAllSectorNamesAvailable());

		Technology result;

		int id = request.getModel().getInteger("id");
		result = this.repository.findTechnologyById(id);

		return result;
	}

	@Override
	public void validate(final Request<Technology> request, final Technology entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Technology> request, final Technology entity) {
		assert request != null;
		assert entity != null;

		Sector sector = this.repository.findSectorByName(entity.getSector().getSector());
		entity.setSector(sector);
		this.repository.save(entity);
	}

}
