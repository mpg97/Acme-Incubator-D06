
package acme.entities.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.basicPost.BasicPost;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notice extends BasicPost {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@URL
	private String				header;

	@NotBlank
	@Column(length = 1024)
	@Length(max = 1024)
	private String				body;

	@URL
	@Length(max = 255)
	private String				link;

}
