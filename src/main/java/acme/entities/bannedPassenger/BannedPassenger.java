
package acme.entities.bannedPassenger;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.constraints.ValidBannedPassenger;
import acme.constraints.ValidLongText;
import acme.constraints.ValidPassport;
import acme.constraints.ValidShortText;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidBannedPassenger
@Table(indexes = {
	@Index(columnList = "banDate"),//
	@Index(columnList = "liftDate")

})
public class BannedPassenger extends AbstractEntity {

	// Serialization ----------------------------------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes ------------------------------------------------------------------------

	@Mandatory
	@ValidShortText
	@Automapped
	private String				name;

	@Mandatory
	@ValidMoment(past = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				birthDate;

	@Mandatory
	@ValidPassport
	@Column(unique = true)
	private String				passport;

	@Mandatory
	@ValidShortText
	@Automapped
	private String				nationality;

	@Mandatory
	@ValidLongText
	@Automapped
	private String				reason;

	@Mandatory
	@ValidMoment(past = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				banDate;

	@Optional
	@ValidMoment(past = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				liftDate;

	// Derived Attributes ------------------------------------------------------------------------

	// Relations ------------------------------------------------

}
