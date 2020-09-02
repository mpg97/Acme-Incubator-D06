
package acme.features.investor.message;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.messages.Message;
import acme.entities.roles.Investor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/investor/message/")
public class InvestorMessageController extends AbstractController<Investor, Message> {

	@Autowired
	private InvestorMessagePostService postService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.POST, BasicCommand.CREATE, this.postService);

	}
}
