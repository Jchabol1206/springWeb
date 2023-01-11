package org.iesvdm.modelo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class Pedido {
	
	private int id;
	private double total;
	private Date fecha;
	private long id_cliente;
	private int id_comercial;
	
	
	public Pedido() {
		
	}
	

}
