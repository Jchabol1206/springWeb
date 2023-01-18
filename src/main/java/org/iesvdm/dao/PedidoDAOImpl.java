package org.iesvdm.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
@Repository
//Utilizo lombok para generar el constructor
@AllArgsConstructor

public class PedidoDAOImpl implements PedidoDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Optional<List<Pedido>> find(int id) {
		List<Pedido> listPed =  jdbcTemplate.query(
				"SELECT * FROM pedido WHERE id_comercial = ?", (rs, rowNum) -> new Pedido(rs.getInt("id"),
																				rs.getDouble("total"),
																				rs.getDate("fecha"),
																				rs.getLong("id_cliente"),
																				rs.getInt("id_comercial"))
																				,id);
		if(listPed !=null) {
			return Optional.of(listPed);
		} else {
			log.info("Pedido no encontrado");
			return Optional.empty();
		}
		
	}
	@Override
	public Optional<List<Pedido>> findSorted(int id) {
		List<Pedido> listPed =  jdbcTemplate.query(
				"SELECT * FROM pedido WHERE id_comercial = ? order by total desc", (rs, rowNum) -> new Pedido(rs.getInt("id"),
																				rs.getDouble("total"),
																				rs.getDate("fecha"),
																				rs.getLong("id_cliente"),
																				rs.getInt("id_comercial"))
																				,id);
		if(listPed !=null) {
			return Optional.of(listPed);
		} else {
			log.info("Pedido no encontrado");
			return Optional.empty();
		}
		
	}




}
