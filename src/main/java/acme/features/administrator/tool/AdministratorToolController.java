
package acme.features.administrator.tool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.tool.Tool;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/tool/")
public class AdministratorToolController extends AbstractController<Administrator, Tool> {

	@Autowired
	private AdministratorToolShowService	showService;

	@Autowired
	private AdministratorToolListService	listService;

	@Autowired
	private AdministratorToolUpdateService	updateService;

	@Autowired
	private AdministratorToolCreateService	createService;

	@Autowired
	private AdministratorToolDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}