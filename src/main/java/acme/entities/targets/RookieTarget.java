
package acme.entities.targets;

import javax.persistence.Entity;

import acme.entities.targets.targetRewards.TargetReward;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RookieTarget extends TargetReward {

	private static final long serialVersionUID = 1L;

}
