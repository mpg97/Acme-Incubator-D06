
package acme.features.anonymous.gonzalezBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.GonzalezBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousGonzalezBulletinCreateService implements AbstractCreateService<Anonymous, GonzalezBulletin> {

	@Autowired
	AnonymousGonzalezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<GonzalezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<GonzalezBulletin> request, final GonzalezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<GonzalezBulletin> request, final GonzalezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "title", "description");

	}

	@Override
	public GonzalezBulletin instantiate(final Request<GonzalezBulletin> request) {
		assert request != null;

		GonzalezBulletin result;

		result = new GonzalezBulletin();
		result.setCreationDate(new Date(System.currentTimeMillis() - 1));

		return result;
	}

	@Override
	public void validate(final Request<GonzalezBulletin> request, final GonzalezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<GonzalezBulletin> request, final GonzalezBulletin entity) {
		assert request != null;
		assert entity != null;

		entity.setCreationDate(new Date(System.currentTimeMillis() - 1));

		this.repository.save(entity);

	}

}
