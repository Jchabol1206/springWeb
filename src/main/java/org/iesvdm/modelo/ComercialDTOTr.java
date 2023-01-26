package org.iesvdm.modelo;

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
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	int nPedidos;
	int nPedidosT;
	int	nPedidosS;
	int nPedidosL;
}
