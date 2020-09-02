
package acme.features.entrepreneur.workProgramme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Entrepreneur;
import acme.entities.workProgramme.WorkProgramme;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EntrepreneurWorkProgrammeDeleteService implements AbstractDeleteService<Entrepreneur, WorkProgramme> {

	@Autowired
	EntrepreneurWorkProgrammeRepository repository;


	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<WorkProgramme> request, final WorkProgramme entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "id");
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
	@Override
	public void validate(final Request<WorkProgramme> request, final WorkProgramme entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Integer activities;
		activities = this.repository.countActivityByInvestmentRoundId(entity.getId());
		Boolean hasActivities;
		hasActivities = activities != 0;

		if (!errors.hasErrors()) {
			errors.state(request, !hasActivities, "*", "employer.job.error.activity");
		}

	}

	@Override
	public void delete(final Request<WorkProgramme> request, final WorkProgramme entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
