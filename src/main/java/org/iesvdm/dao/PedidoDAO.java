package org.iesvdm.dao;

import java.util.Optional;
import java.util.*;
import org.iesvdm.modelo.Pedido;

public interface PedidoDAO {

	
	public Optional<List<Pedido>> find(int id);
}
