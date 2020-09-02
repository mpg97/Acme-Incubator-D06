
package acme.features.entrepreneur.workProgramme;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Entrepreneur;
import acme.entities.workProgramme.WorkProgramme;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/work-programme/")
public class EntrepreneurWorkProgrammeController extends AbstractController<Entrepreneur, WorkProgramme> {

	@Autowired
	private EntrepreneurWorkProgrammeListService	listService;

	@Autowired
	private EntrepreneurWorkProgrammeShowService	showService;

	@Autowired
	private EntrepreneurWorkProgrammeDeleteService	deleteService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
