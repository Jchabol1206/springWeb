package org.iesvdm.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ChequearCatValidator implements ConstraintValidator<RangoCategoria, Integer>{
	
	
	private  int[] valor = {};
	private int valor2;
	public void initialize(RangoCategoria constraintAnnotation) {
		this.valor = constraintAnnotation.value();
	}
	
	@Override
	public boolean isValid(Integer categoria, ConstraintValidatorContext context) {
		boolean valido = false;
		String mensaje = null;
		for(int i=0; i<this.valor.length; i++) {
			if(this.valor[i]==categoria) {
				valido=true;
			}
		}
		if(!valido) {
			mensaje = "Categoria Invalida";
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(mensaje)
            .addConstraintViolation();
		}
		
		return valido;
	}

}
