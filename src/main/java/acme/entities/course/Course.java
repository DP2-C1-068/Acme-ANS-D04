
package acme.entities.course;

import javax.persistence.Column;
import javax.persistence.Entity;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidUrl;
import acme.constraints.ValidLongText;
import acme.constraints.ValidShortText;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Course extends AbstractEntity {
	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Query attributes -------------------------------------------------------

	@Mandatory
	@ValidUrl
	@Automapped
	private String				blocksUrl;         // URL - Siempre presente, formato consistente de URL a la API

	@Mandatory
	@ValidShortText
	@Automapped
	private String				name;               // String no vacío (ej: "AP Physics 1", "Test")

	@Mandatory
	@ValidShortText
	@Automapped
	private String				org;                // String corto identificando la organización (ej: "BUx", "BerkeleyX")

	@Optional
	@ValidLongText
	@Automapped
	private String				shortDescription;  // Puede ser string vacío o texto descriptivo

	@Mandatory
	@ValidShortText
	@Automapped
	private String				start;              // Timestamp ISO 8601 (ej: "2016-09-12T18:00:00Z")

	@Mandatory
	@ValidShortText
	@Column(unique = true)
	private String				courseId;          // String con formato específico, igual a id
	// Response attributes ----------------------------------------------------

}
