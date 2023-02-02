package org.iesvdm.controlador;

import org.iesvdm.exception.MiExcepcion;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler(MiExcepcion.class)
	public String handleMiExcepcion(Model model, MiExcepcion miexcepcion) {
		
		model.addAttribute("traza", miexcepcion.getMessage());
		return "errorE";
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleAllUncaughtException(Model model, RuntimeException exception) {
		
		model.addAttribute("traza", exception.getMessage());
		return "error";
	}
}
