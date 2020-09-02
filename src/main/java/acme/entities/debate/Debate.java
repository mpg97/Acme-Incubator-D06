
package acme.entities.debate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import acme.entities.basicPost.BasicPost;
import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Debate extends BasicPost {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(length = 1024)
	@Length(max = 1024)
	private String				paragraph;

	@NotNull
	@Valid
	private Money				minMoney;

	@NotNull
	@Valid
	private Money				maxMoney;

	@NotBlank
	@Email
	@Length(max = 255)
	private String				email;

}
