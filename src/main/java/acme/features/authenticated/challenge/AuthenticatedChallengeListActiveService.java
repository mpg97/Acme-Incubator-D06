
package acme.features.authenticated.challenge;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenge.Challenge;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedChallengeListActiveService implements AbstractListService<Authenticated, Challenge> {

	@Autowired
	AuthenticatedChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description");

	}
	@Override
	public Collection<Challenge> findMany(final Request<Challenge> request) {
		assert request != null;

		return this.repository.findAllActivesChallenges(new Date(System.currentTimeMillis() - 1));

	}

}
