
package acme.entities.messages;

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

import acme.entities.discussionForum.DiscussionForum;
import acme.framework.entities.Authenticated;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Message extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(length = 50)
	@Length(max = 50)
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				creationMoment;

	private String				tags;

	@NotBlank
	@Column(length = 1024)
	@Length(max = 1024)
	private String				body;

	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private DiscussionForum		discussionForum;

	@Valid
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Authenticated		author;

}
