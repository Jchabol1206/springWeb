package org.iesvdm.controlador;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.ComercialDTO;
import org.iesvdm.modelo.Pedido;
import org.iesvdm.service.ComercialService;
import org.iesvdm.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.iesvdm.service.*;
@Controller
public class ComercialController {
	
	private ComercialService comercialService;
	private PedidoService pedidoService;
	private ClienteService clienteService;

	public ComercialController(ComercialService comercialService, PedidoService pedidoService, ClienteService clienteService) {
		this.comercialService = comercialService;
		this.pedidoService= pedidoService;
		this.clienteService=clienteService;
	}
	
	
	
	@GetMapping("/comerciales")
	public String listar(Model model) {
		
		List<Comercial> listaComerciales = comercialService.listAll();
		model.addAttribute("listaComerciales", listaComerciales);
		
		return "comerciales";
	}

	@GetMapping("/comerciales/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		ComercialDTO comercial = comercialService.oneDTO(id);
		int idMin=0;
		int idMax=0;
		List<Pedido> pedido = pedidoService.one(id);
		List<Pedido> pedidoOrdenado = pedidoService.two(id);
		model.addAttribute("pedidosOr", pedidoOrdenado);
		model.addAttribute("comercial", comercial);
		model.addAttribute("pedidos", pedido);
		List<Cliente> listaClientes =  clienteService.listAll();
		Pedido pedMin = new Pedido();
		pedMin.setId(0);
		for(Pedido cli: pedido) {
			if(pedMin.getId()==0) {
				pedMin.setId(cli.getId());
				pedMin.setTotal(cli.getTotal());
			}
			if(pedMin.getId()!=0) {
				if(pedMin.getTotal()>cli.getTotal()) {
					pedMin.setId(cli.getId());
					pedMin.setTotal(cli.getTotal());
				}
			}
		}
		idMin=pedMin.getId();
		for(Pedido cli: pedido) {
			if(pedMin.getId()!=0) {
				if(pedMin.getTotal()<cli.getTotal()) {
					pedMin.setId(cli.getId());
					pedMin.setTotal(cli.getTotal());
				}
			}
		}
		idMax=pedMin.getId();
		
		model.addAttribute("idMin", idMin);
		model.addAttribute("idMax", idMax);
		model.addAttribute("listaClientes", listaClientes);
		return "detalle-comercial";
	}
	
	@GetMapping("/comerciales/crear")
	public String crear(Model model) {
		
		Comercial comercial = new Comercial();
		model.addAttribute("comercial", comercial);
		return "crear-comercial";
	}
	
	@PostMapping("/comerciales/crear")
	public RedirectView submitCrear(@ModelAttribute("comercial") Comercial comercial) {
		comercialService.newComercial(comercial);
		return new RedirectView("/comerciales");
	}
	
	@GetMapping("/comerciales/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {
		
		Comercial comercial = comercialService.one(id);
		model.addAttribute("comercial", comercial);
		
		return "editar-comercial";
		
	}
	
	
	@PostMapping("/comerciales/editar/{id}")
	public RedirectView submitEditar(@ModelAttribute("comercial") Comercial comercial) {
		
		comercialService.replaceComercial(comercial);		
		
		return new RedirectView("/comerciales");
	}
	
	@PostMapping("/comerciales/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable Integer id) {
		
		comercialService.deleteComercial(id);
		
		return new RedirectView("/comerciales");
	}
	
}
