package org.iesvdm.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComercialDTO{
	
	int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private float comision;
	private double total;
	private double media;
	

}
