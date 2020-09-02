
package acme.features.entrepreneur.workProgramme;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Entrepreneur;
import acme.entities.workProgramme.WorkProgramme;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EntrepreneurWorkProgrammeListService implements AbstractListService<Entrepreneur, WorkProgramme> {

	@Autowired
	EntrepreneurWorkProgrammeRepository repository;


	@Override
	public boolean authorise(final Request<WorkProgramme> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<WorkProgramme> request, final WorkProgramme entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "investmentRound.ticker", "investmentRound.title");
	}
	@Override

	public Collection<WorkProgramme> findMany(final Request<WorkProgramme> request) {
		assert request != null;

		Collection<WorkProgramme> result;
		int id;
		Principal principal;
		principal = request.getPrincipal();
		id = principal.getActiveRoleId();
		result = this.repository.findWorkProgrammeByEntrepenurId(id);

		return result;
	}

}
