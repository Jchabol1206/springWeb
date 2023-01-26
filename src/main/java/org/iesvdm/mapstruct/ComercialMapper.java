package org.iesvdm.mapstruct;

import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.ComercialDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ComercialMapper {
	
	
	@Mapping(target = "pedido_id", source ="idpedido")
	public ComercialDTO comercialAComercialDTO(Comercial comercial, int idpedido);

	
	public Comercial comercialDTOAComercial(ComercialDTO comercial);
}
