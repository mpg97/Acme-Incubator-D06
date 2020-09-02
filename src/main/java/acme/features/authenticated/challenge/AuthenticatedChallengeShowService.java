
package acme.features.authenticated.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenge.Challenge;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedChallengeShowService implements AbstractShowService<Authenticated, Challenge> {

	@Autowired
	private AuthenticatedChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		int challengeId = request.getModel().getInteger("id");
		Challenge challenge = this.repository.findChallengeById(challengeId);
		boolean res = challenge.getDeadline().compareTo(new Date(System.currentTimeMillis() - 1)) > 0;

		return res;
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description");

		model.setAttribute("rookieGoal", entity.getRookie().getGoal());
		model.setAttribute("rookieReward", entity.getRookie().getReward());

		model.setAttribute("averageGoal", entity.getAverage().getGoal());
		model.setAttribute("averageReward", entity.getAverage().getReward());

		model.setAttribute("expertGoal", entity.getExpert().getGoal());
		model.setAttribute("expertReward", entity.getExpert().getReward());

	}

	@Override
	public Challenge findOne(final Request<Challenge> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");

		return this.repository.findChallengeById(id);

	}

}
