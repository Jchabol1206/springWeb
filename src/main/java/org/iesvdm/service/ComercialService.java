package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.ComercialDTO;
import org.springframework.stereotype.Service;

@Service
public class ComercialService {
	
	private ComercialDAO comercialDAO;
	
	public ComercialService(ComercialDAO comercialDAO) {
		this.comercialDAO=comercialDAO;
	}
	
	public List<Comercial> listAll(){
		return comercialDAO.getAll();
	}
	
	public Comercial one (Integer id) {
		Optional optCli = comercialDAO.find(id);
		if(optCli.isPresent()) {
			return (Comercial) optCli.get();
		}
		else {
			return null;
		}
	}
	
	public ComercialDTO oneDTO (Integer id) {
		Optional optCli = comercialDAO.findDTO(id);
		if(optCli.isPresent()) {
			return (ComercialDTO) optCli.get();
		}
		else {
			return null;
		}
	}
	
	public void newComercial (Comercial cliente) {
		comercialDAO.create(cliente);
	}
	
	public void replaceComercial(Comercial cliente) {
		comercialDAO.update(cliente);
	}
	public void deleteComercial(int id) {
		comercialDAO.delete(id);
	}
	
}
