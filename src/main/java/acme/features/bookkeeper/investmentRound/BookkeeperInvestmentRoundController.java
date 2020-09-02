
package acme.features.bookkeeper.investmentRound;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investmentround.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/investment-round/")
public class BookkeeperInvestmentRoundController extends AbstractController<Bookkeeper, InvestmentRound> {

	@Autowired
	private BookkeeperInvestmentRoundListInvolvedService	listInvolvedService;

	@Autowired
	private BookkeeperInvestmentRoundListNotInvolvedService	listNotInvolvedService;

	@Autowired
	private BookkeeperInvestmentRoundShowService			showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_INVOLVED, BasicCommand.LIST, this.listInvolvedService);
		super.addCustomCommand(CustomCommand.LIST_NOT_INVOLVED, BasicCommand.LIST, this.listNotInvolvedService);
	}

}
