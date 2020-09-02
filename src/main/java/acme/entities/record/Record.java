
package acme.entities.record;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.entities.sector.Sector;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Record extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(length = 50)
	@Length(max = 50)
	private String				title;

	@NotBlank
	@Column(length = 50)
	@Length(max = 50)
	private String				investorName;

	@NotBlank
	@Column(length = 1024)
	@Length(max = 1024)
	private String				description;

	@NotBlank
	@URL
	@Length(max = 255)
	private String				web;

	@NotBlank
	@Email
	@Length(max = 50)
	private String				email;

	@NotNull
	private Boolean				openSource;

	@Range(min = 0, max = 5)
	private Integer				stars;

	//

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sector				sector;

}
