
package acme.entities.roles;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.sector.Sector;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Entrepreneur extends UserRole {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				startUpName;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sector				sector;

	@NotBlank
	private String				qualificationRecord;

	@NotBlank
	private String				skillRecord;

}
