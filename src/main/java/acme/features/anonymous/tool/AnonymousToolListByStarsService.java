
package acme.features.anonymous.tool;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tool.Tool;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousToolListByStarsService implements AbstractListService<Anonymous, Tool> {

	@Autowired
	AnonymousToolRepository repository;


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

		request.unbind(entity, model, "title", "investorName", "stars");
		model.setAttribute("sectorName", entity.getSector().getSector());

	}
	@Override
	public Collection<Tool> findMany(final Request<Tool> request) {
		assert request != null;

		return this.repository.findAllToolsOrderedByStars();

	}

}
