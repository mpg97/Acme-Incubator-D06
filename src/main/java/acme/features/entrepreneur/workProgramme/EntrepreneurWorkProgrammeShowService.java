
package acme.features.entrepreneur.workProgramme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Entrepreneur;
import acme.entities.workProgramme.WorkProgramme;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EntrepreneurWorkProgrammeShowService implements AbstractShowService<Entrepreneur, WorkProgramme> {

	@Autowired
	private EntrepreneurWorkProgrammeRepository repository;


	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		WorkProgramme workProgramme = this.repository.findWorkProgrammeById(id);
		return workProgramme.getInvestmentRound().getEntrepreneur().getId() == request.getPrincipal().getActiveRoleId();

	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "id", "investmentRound.ticker");

	}

	@Override
	public WorkProgramme findOne(final Request<WorkProgramme> request) {
		assert request != null;

		WorkProgramme result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findWorkProgrammeById(id);

		return result;
	}

}
