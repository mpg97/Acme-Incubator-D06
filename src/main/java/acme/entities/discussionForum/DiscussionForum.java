
package acme.entities.discussionForum;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import acme.entities.investmentround.InvestmentRound;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DiscussionForum extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@Valid
	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private InvestmentRound		investmentRound;

}
