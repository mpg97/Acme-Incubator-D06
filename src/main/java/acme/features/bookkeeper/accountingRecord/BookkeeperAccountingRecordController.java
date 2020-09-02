
package acme.features.bookkeeper.accountingRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.accountingRecords.AccountingRecord;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/bookkeeper/accounting-record/")
public class BookkeeperAccountingRecordController extends AbstractController<Bookkeeper, AccountingRecord> {

	@Autowired
	private BookkeeperAccountingRecordListByInvestmentService	listByInvestmentService;

	@Autowired
	private BookkeeperAccountingRecordShowService				showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_BY_INVESTMENT, BasicCommand.LIST, this.listByInvestmentService);
	}

}
