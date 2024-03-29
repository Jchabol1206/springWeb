package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.ComercialDTO;
import org.iesvdm.modelo.ComercialDTOTr;


public interface ComercialDAO {
	
	public void create(Comercial comercial);
	
	public List<Comercial> getAll();
	
	public Optional<Comercial>  find(int id);
	public Optional<ComercialDTO>  findDTO(int id);
	
	//public Optional<List<ComercialDTO>> getAllconId(int id);	

	public void update(Comercial comercial);
	
	public void delete(long id);

}
