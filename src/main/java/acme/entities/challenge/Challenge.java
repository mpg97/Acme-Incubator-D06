
package acme.entities.challenge;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.entities.targets.AverageTarget;
import acme.entities.targets.ExpertTarget;
import acme.entities.targets.RookieTarget;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Challenge extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(length = 50)
	@Length(max = 50)
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotBlank
	@Column(length = 1024)
	@Length(max = 1024)
	private String				description;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private RookieTarget		rookie;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private AverageTarget		average;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private ExpertTarget		expert;

}
