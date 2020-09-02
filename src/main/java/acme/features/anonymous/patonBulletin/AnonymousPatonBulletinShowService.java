
package acme.features.anonymous.patonBulletin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PatonBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousPatonBulletinShowService implements AbstractShowService<Anonymous, PatonBulletin> {

	@Autowired
	AnonymousPatonBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PatonBulletin> request) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void unbind(final Request<PatonBulletin> request, final PatonBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "title", "description", "creationDate");

	}

	@Override
	public PatonBulletin findOne(final Request<PatonBulletin> request) {
		assert request != null;

		PatonBulletin result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPatonBulletinById(id);

		return result;
	}

}
