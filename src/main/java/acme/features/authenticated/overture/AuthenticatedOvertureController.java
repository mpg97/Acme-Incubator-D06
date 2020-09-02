
package acme.features.authenticated.overture;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.overture.Overture;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/overture/")
public class AuthenticatedOvertureController extends AbstractController<Authenticated, Overture> {

	@Autowired
	private AuthenticatedOvertureListActiveService	listActiveService;

	@Autowired
	private AuthenticatedOvertureShowService		showService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_ACTIVE, BasicCommand.LIST, this.listActiveService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
