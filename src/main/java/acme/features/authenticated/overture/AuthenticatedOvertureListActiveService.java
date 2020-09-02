
package acme.features.authenticated.overture;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overture.Overture;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedOvertureListActiveService implements AbstractListService<Authenticated, Overture> {

	@Autowired
	AuthenticatedOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline");
		model.setAttribute("range", entity.getMinMoney().getAmount() + entity.getMinMoney().getCurrency() + "  -  " + entity.getMaxMoney().getAmount() + entity.getMaxMoney().getCurrency());

	}
	@Override
	public Collection<Overture> findMany(final Request<Overture> request) {
		assert request != null;

		return this.repository.findAllActivesOvertures(new Date(System.currentTimeMillis() - 1));

	}

}
