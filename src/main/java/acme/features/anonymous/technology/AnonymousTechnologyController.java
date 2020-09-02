
package acme.features.anonymous.technology;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.technology.Technology;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/technology/")
public class AnonymousTechnologyController extends AbstractController<Anonymous, Technology> {

	@Autowired
	private AnonymousTechnologyListService			listService;

	@Autowired
	private AnonymousTechnologyListByStarsService	listByStarsService;

	@Autowired
	private AnonymousTechnologyListBySectorService	listBySectorService;

	@Autowired
	private AnonymousTechnologyShowService			showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.LIST_BY_STARS, BasicCommand.LIST, this.listByStarsService);
		super.addCustomCommand(CustomCommand.LIST_BY_SECTOR, BasicCommand.LIST, this.listBySectorService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
