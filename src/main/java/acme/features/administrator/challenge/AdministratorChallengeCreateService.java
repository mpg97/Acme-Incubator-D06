
package acme.features.administrator.challenge;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenge.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "rookie.goal", "rookie.reward", "average.goal", "average.reward", "expert.goal", "expert.reward");

	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		assert request != null;

		Challenge result;

		result = new Challenge();

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean dontLaunchError = true;
		boolean launchError = false;

		Money rookieReward = entity.getRookie().getReward();
		Money averageReward = entity.getAverage().getReward();
		Money expertReward = entity.getExpert().getReward();

		if (!errors.hasErrors("rookie.goal")) {
			Boolean isEmpty = !entity.getRookie().getGoal().isEmpty();
			errors.state(request, isEmpty, "rookie.goal", "administrator.challenge.goal.empty");
		}

		if (!errors.hasErrors("average.goal")) {
			Boolean isEmpty = !entity.getAverage().getGoal().isEmpty();
			errors.state(request, isEmpty, "average.goal", "administrator.challenge.goal.empty");
		}

		if (!errors.hasErrors("expert.goal")) {
			Boolean isEmpty = !entity.getExpert().getGoal().isEmpty();
			errors.state(request, isEmpty, "expert.goal", "administrator.challenge.goal.empty");
		}

		//		---- ROOKIE REWARD CHECKS ----

		if (!errors.hasErrors("rookie.reward")) {
			if (rookieReward != null) {
				Boolean lowerThanAverage = averageReward != null ? rookieReward.getAmount() < averageReward.getAmount() : dontLaunchError;
				errors.state(request, lowerThanAverage, "rookie.reward", "administrator.challenge.rookie.lowerThanAverage");

				Boolean lowerThanExpert = expertReward != null ? rookieReward.getAmount() < expertReward.getAmount() : dontLaunchError;
				errors.state(request, lowerThanExpert, "rookie.reward", "administrator.challenge.rookie.lowerThanExpert");

				String currency = rookieReward.getCurrency();

				Boolean isEUR = currency != null ? currency.equals("€") : launchError;
				errors.state(request, isEUR, "rookie.reward", "administrator.challenge.currency.money");
			}

			errors.state(request, rookieReward != null, "rookie.reward", "administrator.challenge.reward.null");
		}

		//		---- AVERAGE REWARD CHECKS ----

		if (!errors.hasErrors("average.reward")) {
			if (averageReward != null) {
				Boolean isLess = expertReward != null ? averageReward.getAmount() < expertReward.getAmount() : dontLaunchError;
				errors.state(request, isLess, "average.reward", "administrator.challenge.average.lowerThanExpert");

				Boolean isBigger = rookieReward != null ? averageReward.getAmount() > rookieReward.getAmount() : dontLaunchError;
				errors.state(request, isBigger, "average.reward", "administrator.challenge.average.higherThanRookie");

				String currency = averageReward.getCurrency();

				Boolean isEUR = currency != null ? currency.equals("€") : launchError;
				errors.state(request, isEUR, "average.reward", "administrator.challenge.currency.money");
			}

			errors.state(request, averageReward != null, "average.reward", "administrator.challenge.reward.null");
		}

		//		---- EXPERT REWARD CHECKS ----

		if (!errors.hasErrors("expert.reward")) {
			if (expertReward != null) {
				Boolean higherThanRookie = rookieReward != null ? expertReward.getAmount() > rookieReward.getAmount() : dontLaunchError;
				errors.state(request, higherThanRookie, "expert.reward", "administrator.challenge.expert.higherThanRookie");

				Boolean higherThanAverage = averageReward != null ? expertReward.getAmount() > averageReward.getAmount() : dontLaunchError;
				errors.state(request, higherThanAverage, "expert.reward", "administrator.challenge.expert.higherThanAverage");

				String currency = expertReward.getCurrency();

				Boolean isEUR = currency != null ? currency.equals("€") : launchError;
				errors.state(request, isEUR, "expert.reward", "administrator.challenge.currency.money");
			}

			errors.state(request, expertReward != null, "expert.reward", "administrator.challenge.reward.null");
		}

		if (!errors.hasErrors("deadline")) {
			Date date = DateUtils.addMonths(new Date(System.currentTimeMillis() - 1), 1);

			Boolean isAfter = entity.getDeadline().after(date);
			errors.state(request, isAfter, "deadline", "administrator.challenge.deadline.time");
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity.getRookie());
		this.repository.save(entity.getAverage());
		this.repository.save(entity.getExpert());
		this.repository.save(entity);
	}

}
