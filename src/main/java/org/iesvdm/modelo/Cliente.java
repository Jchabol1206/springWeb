package org.iesvdm.modelo;

import org.iesvdm.validator.RangoCategoria;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
//La anotación @Data de lombok proporcionará el código de: 
//getters/setters, toString, equals y hashCode
//propio de los objetos POJOS o tipo Beans
@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
public class Cliente {
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	private long id;
	@NotBlank(message = "Por favor, introduzca nombre")
	@Size(max=31, message ="Nombre como maximo de 30 caracteres")
	private String nombre;
	@NotBlank(message = "Por favor, introduzca apellido")
	@Size(max=31, message ="Apellido como maximo de 30 caracteres")
	private String apellido1;
	private String apellido2;
	@NotBlank(message = "Por favor, introduzca ciudad")
	@Size(max=51, message ="Ciudad como maximo de 50 caracteres")
	private String ciudad;
	@Min(value=99, message = "Categoría minima 100")
	@Max(value=1001, message= "Categoria maxima 1000")
	@RangoCategoria()
	private int categoria;
	
	@Email(message="obligatorio")
	private String email;
	
	
	
}
