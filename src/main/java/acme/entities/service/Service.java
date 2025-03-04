
package acme.entities.service;

import javax.persistence.Column;
import javax.persistence.Entity;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Money;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoney;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;
import acme.client.components.validation.ValidUrl;
import acme.constraints.ValidShortText;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Service extends AbstractEntity {

	// Serialization -----------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes -----------------------------------

	@Mandatory
	@ValidShortText
	@Automapped
	private String				name;

	@Mandatory
	@ValidUrl
	@Automapped
	private String				link;

	@Mandatory
	@ValidNumber(min = 0, max = 10, integer = 2)
	@Automapped
	private int					avgDwellTime;

	@Optional
	@ValidString(pattern = "^[A-Z]{4}-[0-9]{2}$")
	@Column(unique = true)
	private String				promotionCode;

	@Optional
	@ValidMoney(min = 0.00, max = 1000000.00)
	@Automapped
	private Money				discount;

	// Relations --------------------------------------------

}
