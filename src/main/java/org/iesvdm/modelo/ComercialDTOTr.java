package org.iesvdm.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComercialDTOTr {

	
	public ComercialDTOTr(int id, String nombre, String apellido1, String apellido2) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}

	int id;
	@NotBlank(message = "Por favor, introduzca nombre")
	@Size(max=31, message ="Nombre como maximo de 30 caracteres")
	private String nombre;
	@NotBlank(message = "Por favor, introduzca apellido")
	@Size(max=31, message ="Apellido como maximo de 30 caracteres")
	private String apellido1;
	private String apellido2;
	
	int nPedidos;
	int nPedidosT;
	int	nPedidosS;
	int nPedidosL;
}
