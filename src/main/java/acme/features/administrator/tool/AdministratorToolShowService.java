
package acme.features.administrator.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tool.Tool;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorToolShowService implements AbstractShowService<Administrator, Tool> {

	@Autowired
	private AdministratorToolRepository repository;


	@Override
	public boolean authorise(final Request<Tool> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Tool> request, final Tool entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investorName", "description", "web", "email", "openSource", "stars", "sector.sector");
		model.setAttribute("sectorName", entity.getSector().getSector());
		model.setAttribute("sectorsAvailable", this.repository.findAllSectorNamesAvailable());

	}

	@Override
	public Tool findOne(final Request<Tool> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");

		return this.repository.findToolById(id);
	}

}
