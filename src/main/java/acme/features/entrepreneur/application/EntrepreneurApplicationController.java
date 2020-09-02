
package acme.features.entrepreneur.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.application.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/application/")
public class EntrepreneurApplicationController extends AbstractController<Entrepreneur, Application> {

	@Autowired
	private EntrepreneurApplicationListAppliedToMineService							listAppliedToMineService;

	@Autowired
	private EntrepreneurApplicationListAppliedToMineGroupedByCreationDateService	listAppliedToMineGroupedByCreationDateService;

	@Autowired
	private EntrepreneurApplicationListAppliedToMineGroupedByTickerService			listAppliedToMineGroupedByTickerService;

	@Autowired
	private EntrepreneurApplicationShowService										showService;

	@Autowired
	private EntrepreneurApplicationUpdateService									updateService;


	@PostConstruct
	private void initialise() {
		//	super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_APPLIED_TO_MINE, BasicCommand.LIST, this.listAppliedToMineService);
		super.addCustomCommand(CustomCommand.LIST_APPLIED_TO_MINE_GROUPED_BY_TICKER, BasicCommand.LIST, this.listAppliedToMineGroupedByTickerService);
		super.addCustomCommand(CustomCommand.LIST_APPLIED_TO_MINE_GROUPED_BY_CREATION_DATE, BasicCommand.LIST, this.listAppliedToMineGroupedByCreationDateService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);

	}
}
