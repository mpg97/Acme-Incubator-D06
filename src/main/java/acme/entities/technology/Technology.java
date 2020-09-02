
package acme.entities.technology;

import javax.persistence.Entity;

import acme.entities.record.Record;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Technology extends Record {

	private static final long serialVersionUID = 1L;

}
