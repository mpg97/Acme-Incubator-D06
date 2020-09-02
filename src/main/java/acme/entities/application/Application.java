
package acme.entities.application;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.entities.investmentround.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	/**
	 *
	 */
	private static final long		serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "^([A-Z]{3}[-][0-9]{2}[-][0-9]{6})")
	private String					ticker;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date					creation;

	@NotNull
	private ApplicationStatement	statement;

	@Valid
	@NotNull
	private Money					moneyOffer;

	private String					justification;

	@NotNull
	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private InvestmentRound			investmentRound;

	@NotNull
	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Investor				investor;

}
