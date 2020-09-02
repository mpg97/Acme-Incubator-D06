
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configurations.Configuration;
import acme.entities.investmentround.InvestmentRound;
import acme.entities.investmentround.KindOfRound;
import acme.entities.roles.Entrepreneur;
import acme.entities.workProgramme.WorkProgramme;
import acme.features.entrepreneur.workProgramme.EntrepreneurWorkProgrammeRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository	repository;

	@Autowired
	EntrepreneurWorkProgrammeRepository		workProgrammeRepository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
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
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		assert request != null;

		request.getModel().setAttribute("kinds", KindOfRound.values());

		InvestmentRound result = new InvestmentRound();

		Date moment = new Date(System.currentTimeMillis() - 1);

		int entrepreneurId = request.getPrincipal().getAccountId();
		Entrepreneur entrepreneur = this.repository.findEntrepreneurByUserAccountId(entrepreneurId);

		result.setEntrepreneur(entrepreneur);
		result.setCreationDate(moment);
		result.setFinalMode(false);
		return result;
	}
	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {

		//		int id = request.getModel().getInteger("id");

		int principal = request.getPrincipal().getAccountId();
		Entrepreneur e = this.repository.findEntrepreneurByUserAccountId(principal);

		if (!errors.hasErrors("amount")) {
			String currency = entity.getAmount().getCurrency();
			Boolean isEUR = currency.equals("€");
			errors.state(request, isEUR, "amount", "entrepreneur.investment-round.currency.amount");
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
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

		WorkProgramme workProgramme = new WorkProgramme();
		workProgramme.setInvestmentRound(entity);
		this.workProgrammeRepository.save(workProgramme);
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
