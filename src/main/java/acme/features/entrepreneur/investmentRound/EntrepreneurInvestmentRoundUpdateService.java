
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configurations.Configuration;
import acme.entities.investmentround.InvestmentRound;
import acme.entities.investmentround.KindOfRound;
import acme.entities.roles.Entrepreneur;
import acme.features.entrepreneur.activity.EntrepreneurActivityRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	private EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	private EntrepreneurActivityRepository			activityRepository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		InvestmentRound investment = this.repository.findInvestmentRoundById(id);
		return investment.getEntrepreneur().getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "title", "description", "amount", "moreInfo", "kindRound", "finalMode", "entrepreneur.startUpName");
		model.setAttribute("kinds", KindOfRound.values());
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		request.getModel().setAttribute("kinds", KindOfRound.values());

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findInvestmentRoundById(id);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {

		int id = request.getModel().getInteger("id");

		int principal = request.getPrincipal().getAccountId();
		Entrepreneur e = this.repository.findEntrepreneurByUserAccountId(principal);
		Double total = this.activityRepository.findActivityByInvestmentId(entity.getId());
		InvestmentRound dbInvestment = this.repository.findInvestmentRoundById(entity.getId());

		if (!errors.hasErrors()) {
			errors.state(request, dbInvestment.isFinalMode() == false, "*", "entrepreneur.investment-round.finalMode");
		}

		if (!errors.hasErrors("amount")) {
			Boolean amountIsBigger = entity.getAmount().getAmount() >= total;
			errors.state(request, amountIsBigger, "amount", "entrepreneur.investment-round.amount.valid", total);
		}

		if (!errors.hasErrors("finalMode")) {
			Boolean isAlreadyFinal = this.repository.findInvestmentRoundById(id).isFinalMode();
			Boolean turnOfFinal = entity.isFinalMode();
			errors.state(request, !(isAlreadyFinal && turnOfFinal), "finalMode", "entrepreneur.investment-round.finalMode");

			Boolean notTotalAmount = entity.getAmount().getAmount() == total ? !entity.isFinalMode() : true;
			errors.state(request, notTotalAmount, "finalMode", "entrepreneur.investment-round.finalMode.amount");

		}

		if (!errors.hasErrors("ticker")) {

			Boolean isEUR = this.isValidTicker(entity.getTicker(), e.getSector().getSector());
			errors.state(request, isEUR, "ticker", "entrepreneur.investment-round.ticker.valid");
		}

		if (!errors.hasErrors("description")) {
			Double totalSum = 0d;

			List<String> spamList = new ArrayList<String>();

			String language = request.getLocale().getLanguage();
			Configuration config = this.repository.findConfigurationByLanguage(language);

			spamList.addAll(Arrays.asList(config.getSpamwords().toLowerCase().replace(", ", ",").split(",")));

			String formContent = entity.getTitle().toLowerCase() + entity.getDescription().toLowerCase();

			for (String spam : spamList) {
				int sum = 0;
				sum += formContent.split(spam, -1).length - 1;
				totalSum += sum * spam.length();
			}

			Double sizeContent = formContent.length() * 1.0;

			Boolean checkIsSpam = totalSum / sizeContent >= config.getSpamThreshold();

			errors.state(request, !checkIsSpam, "description", "entrepreneur.message.form.error.is-spam");
		}

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	public Boolean isValidTicker(final String ticker, String sector) {

		boolean res = true;

		sector = sector.toUpperCase();

		String tickerSector = ticker.substring(0, 3);
		String tickerYear = ticker.substring(4, 6);
		String tickerNumber = ticker.substring(7, 13);
		int year = Calendar.getInstance().get(Calendar.YEAR);

		String twoDigitYear = Integer.toString(year).substring(2, 4);

		if (tickerSector.length() == 1) {

			StringBuilder sb = new StringBuilder("");
			sb.append(tickerSector);
			sb.append("XX");
			tickerSector = sb.toString();

		} else if (tickerSector.length() == 2) {

			StringBuilder sb = new StringBuilder("");
			sb.append(tickerSector);
			sb.append("X");
			tickerSector = sb.toString();
		}

		res &= tickerSector.equals(sector.substring(0, 3));
		res &= tickerYear.equals(twoDigitYear);
		res &= this.isNumeric(tickerNumber);

		return res;

	}

	private boolean isNumeric(final String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;

	}

}
