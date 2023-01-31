package org.iesvdm.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.*;
import jakarta.validation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChequearCatValidator.class)
@Documented
@Repeatable(RangoCategoria.List.class)
public @interface RangoCategoria {
	
	//String value() default "0";
	int[] value() default {100, 200, 300, 400, 500, 600, 700, 800, 900};
	String message() default "error de categoria, seleccione un numeron terminado en *00";
	
	
	
		
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List{
		RangoCategoria[] value();
	}
}
