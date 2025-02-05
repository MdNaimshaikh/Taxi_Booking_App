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
@Getter
@Setter
@ToString
@Entity
@Table(name="service")
public class ServiceForm {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String image;
	
	@NotEmpty(message ="Title cannot be Empty")
	@Size(min=5 , max=50, message="Invalid title Size")
	@Column(length = 50)
	private String title;
	@NotEmpty(message ="Description cannot be Empty")
	@Size(min=5 , max=200, message="Invalid Description  Size")
	@Column(length = 200)
	private String description;

}
