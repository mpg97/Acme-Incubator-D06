
package acme.features.administrator.overture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.overture.Overture;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorOvertureShowService implements AbstractShowService<Administrator, Overture> {

	@Autowired
	private AdministratorOvertureRepository repository;


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

		request.unbind(entity, model, "title", "paragraph", "creation", "deadline", "email", "minMoney", "maxMoney");

	}

	@Override
	public Overture findOne(final Request<Overture> request) {
		System.out.println("hola");
		assert request != null;

		Overture result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOvertureById(id);

		System.out.println(result);

		return result;
	}

}
