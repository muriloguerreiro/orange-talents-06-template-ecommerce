package br.com.zupacademy.murilo.mercadolivre.validacao.validadores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

import br.com.zupacademy.murilo.mercadolivre.validacao.anotacao.ExisteOuNulo;

public class ValidaSeExisteOuNulo implements ConstraintValidator<ExisteOuNulo, Object> {
	
	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ExisteOuNulo params) {
		klass = params.domainClass();
		domainAttribute = params.fieldName();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		
		Query query = manager.createQuery("select l from " + klass.getName() + " l where " + domainAttribute + " = :pValue");
		query.setParameter("pValue", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <=1, "Foi encontrado mais de um " + klass	+ " com o atributo" + domainAttribute + " = " + value);
		
		if (list.size() == 1) {
			return true;
		}
		
		return false;
	}

}
