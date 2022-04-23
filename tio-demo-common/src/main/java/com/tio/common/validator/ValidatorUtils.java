package com.tio.common.validator;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

/**
 * 校验工具类
 *
 * @author
 */
@Slf4j
public class ValidatorUtils {

    private static final Validator validatorFast = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    private static final Validator validatorAll = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    /**
     * 校验遇到第一个不合法的字段直接返回不合法字段，后续字段不再校验
     *
     * @param <T>
     * @param domain
     * @return
     * @throws Exception
     * @Time
     */
    public static <T> Set<ConstraintViolation<T>> validateFast(T domain, Class<?>... groups) throws Exception {
        Set<ConstraintViolation<T>> validateResult = validatorFast.validate(domain, groups);
        if (validateResult.size() > 0) {
            log.error("{}:{}", validateResult.iterator().next().getPropertyPath(), validateResult.iterator().next().getMessage());
        }
        return validateResult;
    }

    /**
     * 校验遇到第一个不合法的字段直接返回不合法字段，后续字段不再校验
     *
     * @param <T>
     * @param domain
     * @return
     * @throws Exception
     * @Time
     */
    public static <T> Set<ConstraintViolation<T>> validateFast(T domain) throws Exception {
        Set<ConstraintViolation<T>> validateResult = validatorFast.validate(domain);
        if (validateResult.size() > 0) {
            log.error("{}:{}", validateResult.iterator().next().getPropertyPath(), validateResult.iterator().next().getMessage());
        }
        return validateResult;
    }

    /**
     * 校验所有字段并返回不合法字段
     *
     * @param <T>
     * @param domain
     * @return
     * @throws Exception
     * @Time
     */
    public static <T> Set<ConstraintViolation<T>> validateAll(T domain) throws Exception {
        Set<ConstraintViolation<T>> validateResult = validatorAll.validate(domain);
        if (validateResult.size() > 0) {
            Iterator<ConstraintViolation<T>> it = validateResult.iterator();
            while (it.hasNext()) {
                ConstraintViolation<T> cv = it.next();
                log.error("{}:{}", cv.getPropertyPath(), cv.getMessage());
            }
        }
        return validateResult;
    }

}
