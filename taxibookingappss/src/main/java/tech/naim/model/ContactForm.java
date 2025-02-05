package tech.naim.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="contactform")
public class ContactForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message ="Name cannot be Empty")
	@Size(min=2 , max=30, message="Invalid Name Size")
	@Column(length = 30)
	private String name;
	
	@NotEmpty(message ="Email cannot be Empty")
	@Size(min=5 , max=50, message="Invalid Email Size")
	@Column(length = 50)
	private String email;
	
	@NotEmpty(message="phone no cannot be empty")
	@Size(min=10 , max=10, message="Invalid phone no Size")
	@Column(length = 10)
	private String phone;
	
	@NotEmpty(message ="Message cannot be Empty")
	@Size(min=5 , max=200, message="Invalid Message  Size")
	@Column(length = 200)
	private String message;

}
