
package acme.entities.accountingRecords;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

import acme.entities.investmentround.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AccountingRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(length = 50)
	@Length(max = 50)
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	@NotBlank
	@Column(length = 1024)
	@Length(max = 1024)
	private String				body;

	@NotNull
	private AccountingStatus	status;

	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private InvestmentRound		investmentRound;

	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Bookkeeper			bookkeeper;

}
