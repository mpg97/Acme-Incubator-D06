
package acme.features.investor.application;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.application.Application;
import acme.entities.application.ApplicationStatement;
import acme.entities.investmentround.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationApplyService implements AbstractCreateService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creation", "moneyOffer", "justification");

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;
		result = new Application();

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreation(moment);

		InvestmentRound investmentRound;
		int investmentRoundId;
		investmentRoundId = request.getModel().getInteger("investmentRoundId");
		investmentRound = this.repository.findInvestmentRoundById(investmentRoundId);
		result.setInvestmentRound(investmentRound);

		Investor investor;
		int investorId;
		Principal principal;
		principal = request.getPrincipal();
		investorId = principal.getAccountId();
		investor = this.repository.findInvestorByUserAccountId(investorId);
		result.setInvestor(investor);

		result.setStatement(ApplicationStatement.PENDING);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal;
		principal = request.getPrincipal();
		int Id = principal.getAccountId();
		Investor e = this.repository.findInvestorByUserAccountId(Id);

		if (!errors.hasErrors("moneyOffer")) {
			String currency = entity.getMoneyOffer().getCurrency();
			Boolean isEUR = currency.equals("€");
			errors.state(request, isEUR, "moneyOffer", "investor.application.currency.amount");
		}

		if (!errors.hasErrors("ticker")) {

			Boolean isEUR = this.isValidTicker(entity.getTicker(), e.getSector().getSector());
			errors.state(request, isEUR, "ticker", "entrepreneur.investment-round.ticker.valid");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
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
