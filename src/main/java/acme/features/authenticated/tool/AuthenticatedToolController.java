
package acme.features.authenticated.tool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.tool.Tool;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/tool/")
public class AuthenticatedToolController extends AbstractController<Authenticated, Tool> {

	@Autowired
	private AuthenticatedToolListService			listService;

	@Autowired
	private AuthenticatedToolListByStarsService		listByStarsService;

	@Autowired
	private AuthenticatedToolListBySectorService	listBySectorService;

	@Autowired
	private AuthenticatedToolShowService			showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_BY_STARS, BasicCommand.LIST, this.listByStarsService);
		super.addCustomCommand(CustomCommand.LIST_BY_SECTOR, BasicCommand.LIST, this.listBySectorService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
