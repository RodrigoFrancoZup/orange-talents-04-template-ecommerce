package br.com.zupacademy.rodrigo.ecommerce.annotation;

import org.springframework.util.Assert;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    @PersistenceContext
    private EntityManager manager;
    private String domainAttribute;
    private Class<?> klass;

    @Override
    public void initialize(ExistsId params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("SELECT 1 FROM "+klass.getName()+" WHERE "+ domainAttribute+"=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        return !list.isEmpty();
    }
}