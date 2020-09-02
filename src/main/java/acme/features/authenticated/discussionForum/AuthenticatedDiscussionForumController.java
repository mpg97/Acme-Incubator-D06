
package acme.features.authenticated.discussionForum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.discussionForum.DiscussionForum;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/discussion-forum/")
public class AuthenticatedDiscussionForumController extends AbstractController<Authenticated, DiscussionForum> {

	@Autowired
	private AuthenticatedDiscussionForumListInvolvedService	listInvolvedService;

	@Autowired
	private AuthenticatedDiscussionForumShowService			showService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_INVOLVED, BasicCommand.LIST, this.listInvolvedService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
