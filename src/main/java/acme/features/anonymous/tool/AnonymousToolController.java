
package acme.features.anonymous.tool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.tool.Tool;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/tool/")
public class AnonymousToolController extends AbstractController<Anonymous, Tool> {

	@Autowired
	private AnonymousToolListService			listService;

	@Autowired
	private AnonymousToolListByStarsService		listByStarsService;

	@Autowired
	private AnonymousToolListBySectorService	listBySectorService;

	@Autowired
	private AnonymousToolShowService			showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_BY_STARS, BasicCommand.LIST, this.listByStarsService);
		super.addCustomCommand(CustomCommand.LIST_BY_SECTOR, BasicCommand.LIST, this.listBySectorService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
