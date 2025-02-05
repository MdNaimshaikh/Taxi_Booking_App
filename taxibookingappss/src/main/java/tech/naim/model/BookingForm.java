package tech.naim.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.type.descriptor.jdbc.TimeJdbcType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="bookingforms")
public class BookingForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message="Name cannot be Empty")
	@NotBlank(message="Name cannot be blank")
	@Size(min=2, max=30 , message="invalid name length")
	@Column(length = 30)
	private String name;
	
	@NotEmpty(message="Source cannot be Empty")
	@NotBlank(message="Source cannot be blank")
	@Size(min=2, max=100 , message="invalid source length")
	@Column(length = 100)
	private String source;
	
	@NotEmpty(message="Email cannot be Empty")
	@NotBlank(message="Email cannot be blank")
	@Size(min=2, max=30 , message="invalid email length")
	@Column(length = 30)
	private String email;
	
	@NotEmpty(message="Destinations cannot be Empty")
	@NotBlank(message="Destinations cannot be blank")
	@Size(min=2, max=100 , message="invalid destinations length")
	@Column(length = 100)
	private String destination;
	
	//@NotNull(message="time cannot be Empty")
	//private LocalTime time;
	
	@NotNull(message="Date cannot be Empty")
	private LocalDate date;
	
	@NotEmpty(message="Comfort cannot be Empty")
	@Column(length = 30)
	private String comfort;
	
	@Min(value=1 , message="Adult at least 1")
	@Max(value=4 , message="Adult atmost be 3")
	private int adult;
	
	
	@Max(value=4 , message="Children at most 4 ")
	private int children;
	
	@NotEmpty(message="Message cannot be Empty")
	@NotBlank(message="Message cannot be blank")
	@Size(min=5, max=2000 , message="invalid message length")
	@Column(length = 2000)
	private String message;

}
