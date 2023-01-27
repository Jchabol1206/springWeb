package org.iesvdm.modelo;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comercial {

	public Comercial() {
		// TODO Auto-generated constructor stub
	}
	private int id;
	@NotBlank(message = "Por favor, introduzca nombre")
	@Size(max=31, message ="Nombre como maximo de 30 caracteres")
	private String nombre;
	@NotBlank(message = "Por favor, introduzca apellido")
	@Size(max=31, message ="Apellido como maximo de 30 caracteres")
	private String apellido1;
	private String apellido2;
	@DecimalMax(value="0.946" , inclusive=true)
	@DecimalMin(value="0.276" , inclusive=true)
	private BigDecimal comision;
	
}
