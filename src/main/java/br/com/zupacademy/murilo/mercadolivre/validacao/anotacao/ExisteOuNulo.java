package br.com.zupacademy.murilo.mercadolivre.validacao.anotacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.murilo.mercadolivre.validacao.validadores.ValidaSeExisteOuNulo;

@Documented
@Constraint(validatedBy = {ValidaSeExisteOuNulo.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteOuNulo {
	
	String message() default "O campo deve existir ou ser nulo";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	String fieldName();
	Class<?> domainClass();
}
