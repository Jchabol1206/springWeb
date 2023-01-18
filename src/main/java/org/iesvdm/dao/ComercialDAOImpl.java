package org.iesvdm.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.ComercialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
@Repository
//Utilizo lombok para generar el constructor
@AllArgsConstructor
public class ComercialDAOImpl implements ComercialDAO {

	//JdbcTemplate se inyecta por el constructor de la clase automáticamente
	//
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void create(Comercial cliente) {
		//Desde java15+ se tiene la triple quote """ para bloques de texto como cadenas.
		String sqlInsert = """
				INSERT INTO comercial (nombre, apellido1, apellido2, comisión) 
				VALUES  (     ?,         ?,         ?,       ?)
			   """;

		KeyHolder keyHolder = new GeneratedKeyHolder();
	//Con recuperación de id generado
		int rows = jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
			int idx = 1;
			ps.setString(idx++, cliente.getNombre());
			ps.setString(idx++, cliente.getApellido1());
			ps.setString(idx++, cliente.getApellido2());
			ps.setFloat(idx++, cliente.getComision());
			return ps;
		},keyHolder);

		cliente.setId(keyHolder.getKey().intValue());

//Sin recuperación de id generado
//int rows = jdbcTemplate.update(sqlInsert,
//				cliente.getNombre(),
//				cliente.getApellido1(),
//				cliente.getApellido2(),
//				cliente.getCiudad(),
//				cliente.getCategoria()
//		);

		log.info("Insertados {} registros.", rows);

	}

	@Override
	public List<Comercial> getAll() {
		
		List<Comercial> listComercial = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"), 
                							  rs.getString("nombre"), 
                							  rs.getString("apellido1"),
                							  rs.getString("apellido2"), 
                							  rs.getFloat("comisión"))
                						 	
        );
		
		log.info("Devueltos {} registros.", listComercial.size());
		
        return listComercial;
	}

	@Override
	public Optional<Comercial> find(int id) {
		Comercial fab =  jdbcTemplate
				.queryForObject("SELECT * FROM comercial WHERE id = ?"														
								, (rs, rowNum) -> new Comercial(rs.getInt("id"),
            						 						rs.getString("nombre"),
            						 						rs.getString("apellido1"),
            						 						rs.getString("apellido2"),
            						 						rs.getInt("comisión")) 
								, id
								);
		
		if (fab != null) { 
			return Optional.of(fab);}
		else { 
			log.info("Comercial no encontrado.");
			return Optional.empty(); }
	}

	@Override
	public void update(Comercial comercial) {
		int rows = jdbcTemplate.update("""
				UPDATE comercial SET 
								nombre = ?, 
								apellido1 = ?, 
								apellido2 = ?,
								comisión = ?
						WHERE id = ?
				""", comercial.getNombre()
				, comercial.getApellido1()
				, comercial.getApellido2()
				, comercial.getComision()
				, comercial.getId());

log.info("Update de Comercial con {} registros actualizados.", rows);

	}

	@Override
	public void delete(long id) {
		int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);
		
		log.info("Delete de Comercial con {} registros eliminados.", rows);

	}

	@Override
	public Optional<ComercialDTO> findDTO(int id) {
		ComercialDTO com =  (ComercialDTO) jdbcTemplate
				.queryForObject("select comercial.*, sum(p.total), avg(p.total) from comercial left outer join pedido p on p.id_comercial=comercial.id where comercial.id=?"														
								, (rs, rowNum) -> new ComercialDTO(rs.getInt("id"),
            						 						rs.getString("nombre"),
            						 						rs.getString("apellido1"),
            						 						rs.getString("apellido2"),
            						 						rs.getInt("comisión"),
            						 						rs.getDouble("sum(p.total)"),
            						 						rs.getDouble("avg(p.total)")) 
								, id
								);
		
		if (com != null) { 
			return Optional.of(com);}
		else { 
			log.info("Comercial no encontrado.");
			return Optional.empty(); }
	}



}
