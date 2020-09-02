
package acme.features.authenticated.messages;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.messages.Message;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/message/")
public class AuthenticatedMessageController extends AbstractController<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageListByDiscussionService	listByDiscussionService;

	@Autowired
	private AuthenticatedMessageShowService				showService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_BY_DISCUSSION, BasicCommand.LIST, this.listByDiscussionService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
