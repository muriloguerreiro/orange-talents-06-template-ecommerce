package br.com.zupacademy.murilo.mercadolivre.validacao.anotacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.murilo.mercadolivre.validacao.validadores.ValidaSeExiste;

@Documented
@Constraint(validatedBy = {ValidaSeExiste.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Existe {
	
	String message() default "O campo deve ser unico";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	String fieldName();
	Class<?> domainClass();
}
