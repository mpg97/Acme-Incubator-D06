
package acme.features.anonymous.gonzalezBulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.GonzalezBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousGonzalezBulletinShowService implements AbstractShowService<Anonymous, GonzalezBulletin> {

	@Autowired
	AnonymousGonzalezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<GonzalezBulletin> request) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void unbind(final Request<GonzalezBulletin> request, final GonzalezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "title", "description", "creationDate");

	}

	@Override
	public GonzalezBulletin findOne(final Request<GonzalezBulletin> request) {
		assert request != null;

		GonzalezBulletin result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findGonzalezBulletinById(id);

		return result;
	}

}
