
package acme.features.entrepreneur.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentround.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/investment-round/")
public class EntrepreneurInvestmentRoundController extends AbstractController<Entrepreneur, InvestmentRound> {

	@Autowired
	private EntrepreneurInvestmentRoundListMineService						listMineService;

	@Autowired
	private EntrepreneurInvestmentRoundListMineOrderedByTickerService		listMineTickerService;

	@Autowired
	private EntrepreneurInvestmentRoundListMineOrderedByCreationDateService	listMineCreationDateService;

	@Autowired
	private EntrepreneurInvestmentRoundShowService							showService;

	@Autowired
	private EntrepreneurInvestmentRoundCreateService						createService;

	@Autowired
	private EntrepreneurInvestmentRoundDeleteService						deleteService;

	@Autowired
	private EntrepreneurInvestmentRoundUpdateService						updateService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_BY_TICKER, BasicCommand.LIST, this.listMineTickerService);
		super.addCustomCommand(CustomCommand.LIST_BY_CREATION_DATE, BasicCommand.LIST, this.listMineCreationDateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}

}
