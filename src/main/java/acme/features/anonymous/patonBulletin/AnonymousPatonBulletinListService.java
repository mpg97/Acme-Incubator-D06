
package acme.features.anonymous.patonBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PatonBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousPatonBulletinListService implements AbstractListService<Anonymous, PatonBulletin> {

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
	public Collection<PatonBulletin> findMany(final Request<PatonBulletin> request) {
		assert request != null;

		Collection<PatonBulletin> result;

		result = this.repository.findAllPatonBulletin();

		return result;
	}

}
