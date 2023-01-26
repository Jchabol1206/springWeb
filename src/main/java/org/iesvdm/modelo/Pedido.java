package org.iesvdm.modelo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor

public class Pedido {
	
	private int id;
	private double total;
	private Date fecha;
	private long id_cliente;
	private int id_comercial;
	
	
	public Pedido() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public long getId_cliente() {
		return id_cliente;
	}


	public void setId_cliente(long id_cliente) {
		this.id_cliente = id_cliente;
	}


	public int getId_comercial() {
		return id_comercial;
	}


	public void setId_comercial(int id_comercial) {
		this.id_comercial = id_comercial;
	}
	

}
