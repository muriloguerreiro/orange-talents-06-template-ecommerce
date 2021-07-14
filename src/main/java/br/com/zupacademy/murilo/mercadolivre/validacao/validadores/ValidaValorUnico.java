package br.com.zupacademy.murilo.mercadolivre.validacao.validadores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

import br.com.zupacademy.murilo.mercadolivre.validacao.anotacao.ValorUnico;

public class ValidaValorUnico implements ConstraintValidator<ValorUnico, Object> {
	
	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ValorUnico params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select l from " + klass.getName() + " l where " + domainAttribute + " = :pValue");
		query.setParameter("pValue", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, "Foi encontrado mais de um " + klass	+ " com o atributo" + domainAttribute + " = " + value);
		
		return list.isEmpty();

	}
	
}
