package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	
	private PedidoDAO pedidoDAO;
	
	public PedidoService(PedidoDAO pedidoDAO) {
		this.pedidoDAO=pedidoDAO;
	}
	
	public List<Pedido> one (Integer id) {
		Optional<List<Pedido>> optPed = pedidoDAO.find(id);
		if(optPed.isPresent()) {
			return (List<Pedido>) optPed.get();
		}
		else {
			return null;
		}
	}
	

}
