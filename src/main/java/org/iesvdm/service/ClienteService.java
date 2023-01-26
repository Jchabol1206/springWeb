package org.iesvdm.service;

import java.util.Calendar;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;
import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.modelo.*;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	private ClienteDAO clienteDAO;
	private ComercialDAO comercialDAO;
	private PedidoDAO pedidoDAO;
	
	
	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired
	public ClienteService(ClienteDAO clienteDAO, ComercialDAO comercialDAO, PedidoDAO pedidoDAO) {
		super();
		this.clienteDAO = clienteDAO;
		this.comercialDAO = comercialDAO;
		this.pedidoDAO = pedidoDAO;
	}
	
	public List<Cliente> listAll() {
		
		return clienteDAO.getAll();
		
	}

	

	public Cliente one (Integer id) {
		Optional optCli = clienteDAO.find(id);
		if(optCli.isPresent()) {
			return (Cliente) optCli.get();
		}
		else {
			return null;
		}
	}
	
	public void newCliente (Cliente cliente) {
		clienteDAO.create(cliente);
	}
	
	public void replaceCliente(Cliente cliente) {
		clienteDAO.update(cliente);
	}
	public void deleteCliente(int id) {
		clienteDAO.delete(id);
	}
	public record VectorStats(DoubleSummaryStatistics trim, DoubleSummaryStatistics Seme, DoubleSummaryStatistics mapAnio) {}
	
	public Map<Comercial, VectorStats> estadisticasPedidosClientePorComercial(int idCliente){
		List<Pedido> listPedidos = pedidoDAO.getPedidosPorComercial(idCliente);
		
		Map<Integer, Comercial> mapComerciales = listPedidos.stream().map(Pedido::getId_comercial).distinct().
				map(id_comercial -> comercialDAO.find(id_comercial).get())
				.collect(Collectors.toMap(Comercial::getId, Function.identity()));
		//Stats por trimestre
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -3);
		Date fechaHaceUnTrimestre= c.getTime();
		
		Map<Comercial, DoubleSummaryStatistics> mapStatisticsTrimestre = listPedidos.stream()
				.filter(p-> p.getFecha().after(fechaHaceUnTrimestre))
				.collect(Collectors.groupingBy(p->mapComerciales.get(p.getId_comercial()), Collectors.summarizingDouble(Pedido::getTotal)));
        //Stats semestre
        c = Calendar.getInstance();
        c.add(Calendar.MONTH, -6);
        Date fechaHaceUnSemestre= c.getTime();
       
        Map<Comercial,DoubleSummaryStatistics> mapStatisticsSemestre = listPedidos.stream()
                .filter(p -> p.getFecha().after(fechaHaceUnSemestre))
                .collect(Collectors.groupingBy(p -> mapComerciales.get(p.getId_comercial()), Collectors.summarizingDouble(Pedido::getTotal)));
       
        //Stats anio
        c = Calendar.getInstance();
        c.add(Calendar.MONTH, -60);
        Date fechaHaceUnAnio= c.getTime();
       
        Map<Comercial,DoubleSummaryStatistics> mapStatisticsAnio = listPedidos.stream()
                .filter(p -> p.getFecha().after(fechaHaceUnAnio))
                .collect(Collectors.groupingBy(p -> mapComerciales.get(p.getId_comercial()), Collectors.summarizingDouble(Pedido::getTotal)));
	
        //LinkedHashMap para mantener ordenado
        Map<Comercial, VectorStats> mapComVectorStats = new LinkedHashMap<>();
        
        mapComerciales.values().stream().sorted(Comparator.comparing(Comercial::getNombre).thenComparing(Comercial::getApellido1)
        .thenComparing(Comercial::getApellido2)).forEach(com -> mapComVectorStats.put(com, new VectorStats(mapStatisticsTrimestre.get(com),
        		mapStatisticsSemestre.get(com),
        		mapStatisticsAnio.get(com))));
        
        
        
        return mapComVectorStats;
        
        
        
        
        
        
	}
	
	
	
	
	
	
	

}
