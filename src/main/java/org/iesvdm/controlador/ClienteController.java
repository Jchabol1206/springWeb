package org.iesvdm.controlador;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.ComercialDTO;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.service.ClienteService;
import org.iesvdm.service.ComercialService;
import org.iesvdm.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.validation.Valid;

@Controller
//Se puede fijar ruta base de las peticiones de este controlador.
//Los mappings de los métodos tendrían este valor /clientes como
//prefijo.
//@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteService clienteService;
	private ComercialService comercialService;
	private PedidoService pedidoService;
	
	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired
	//@Autowired
	public ClienteController(ClienteService clienteService, ComercialService comercialService, PedidoService pedidoService) {
		this.clienteService = clienteService;
		this.comercialService= comercialService;
		this.pedidoService=pedidoService;
	}
	public record stat(long trim, long Seme, long mapAnio) {}
	
	//@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	//equivalente a la siguiente anotación
	@GetMapping("/clientes") //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa
	public String listar(Model model) {
		
		List<Cliente> listaClientes =  clienteService.listAll();
		model.addAttribute("listaClientes", listaClientes);
				
		return "clientes";
		
	}
	
	
	@GetMapping("/clientes/{id}")
	public String detalle(Model model, @PathVariable Integer id) {

		Cliente cliente = clienteService.one(id);
		Map<Comercial, org.iesvdm.service.ClienteService.VectorStats> listaCom = clienteService.estadisticasPedidosClientePorComercial(id);
		
		long contar=0;
		Map<Comercial, stat> listaCom1=new HashMap<Comercial, stat>();
		for(Comercial comercial:listaCom.keySet()) {
	
			long trim=0;
			long Seme=0;
			long mapAnio=0;


			if(listaCom.get(comercial).trim()!=null) {
				trim=listaCom.get(comercial).trim().getCount();
			}
			if(listaCom.get(comercial).Seme()!=null) {
				Seme=listaCom.get(comercial).Seme().getCount();
			}
			if(listaCom.get(comercial).mapAnio()!=null) {
				mapAnio=listaCom.get(comercial).mapAnio().getCount();
			}
			listaCom1.put(comercial, new stat(trim, Seme, mapAnio));
		}
		model.addAttribute("cliente", cliente);
		model.addAttribute("listaComercial", listaCom1);
		return "detalle-cliente";
	}
	
	@GetMapping("/clientes/crear")
	public String crear(Model model) {
		
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "crear-cliente";
	}
	
	
	@PostMapping("/clientes/crear")
	public String submitCrear(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResulted, Model model) {
		
		if(bindingResulted.hasErrors()) {
			model.addAttribute("cliente", cliente);
			return "crear-cliente";
		}
		
		
		clienteService.newCliente(cliente);
		//return new RedirectView("/clientes");
		return "redirect:/clientes";
	}
	
	@GetMapping("/clientes/editar/{id}")
	public String editar(Model model, @PathVariable Integer id) {
		
		Cliente cliente = clienteService.one(id);
		model.addAttribute("cliente", cliente);
		
		return "editar-cliente";
		
	}
	
	
	@PostMapping("/clientes/editar/{id}")
	public String submitEditar(@Valid @ModelAttribute("cliente") Cliente cliente ,  BindingResult bindingResulted, Model model) {
		
		if(bindingResulted.hasErrors()) {
			model.addAttribute("cliente", cliente);
			return "editar-cliente";
		}
		
		
		clienteService.replaceCliente(cliente);		
		return "redirect:/clientes";
		//return new RedirectView("/clientes");
	}
	
	@PostMapping("/clientes/borrar/{id}")
	public RedirectView submitBorrar(@PathVariable Integer id) {
		
		clienteService.deleteCliente(id);
		
		return new RedirectView("/clientes");
	}
	
	
	
	
	

}
