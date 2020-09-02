
package acme.features.authenticated.overture;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overture.Overture;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedOvertureShowService implements AbstractShowService<Authenticated, Overture> {

	@Autowired
	private AuthenticatedOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		int overtureId = request.getModel().getInteger("id");
		Overture overture = this.repository.findOvertureById(overtureId);
		boolean res = overture.getDeadline().compareTo(new Date(System.currentTimeMillis() - 1)) > 0;

		return res;
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creation", "deadline", "paragraph", "minMoney", "maxMoney", "email");

	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");

		return this.repository.findOvertureById(id);

	}

}
