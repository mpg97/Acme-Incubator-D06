
package acme.features.anonymous.patonBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.PatonBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/paton-bulletin/")
public class AnonymousPatonBulletinController extends AbstractController<Anonymous, PatonBulletin> {

	@Autowired
	private AnonymousPatonBulletinCreateService	createService;

	@Autowired
	private AnonymousPatonBulletinListService	listService;

	@Autowired
	private AnonymousPatonBulletinShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
