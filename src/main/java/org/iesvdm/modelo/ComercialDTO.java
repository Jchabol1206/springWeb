package org.iesvdm.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComercialDTO{
	
	int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private float comision;
	private double total;
	private double media;
	
	private int pedido_id;
	
	
	public ComercialDTO(int id, String nombre, String apellido1, String apellido2, float comision, double total, double media) {
		this.id=id;
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.comision=comision;
		this.total=total;
		this.media=media;
	}
	
	public ComercialDTO(int id, String nombre, String apellido1, String apellido2, int id_pedido) {
		this.id=id;
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.pedido_id=id_pedido;
	}
	
}
