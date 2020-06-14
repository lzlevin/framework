package com.vf.lock.aop;

import com.vf.lock.annotation.Lock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * {@link Lock}注解切面
 *
 * @author levin
 * @date 2020/6/13
 * @since 1.0.0
 */
@Slf4j
public class LockAnnotationPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor implements InitializingBean {

    private AnnotationMethodMatcher annotationMethodMatcher = new AnnotationMethodMatcher(Lock.class, false);

    public LockAnnotationPointcutAdvisor(LockAnnotationAdvice advice) {
        this.setAdvice(advice);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return annotationMethodMatcher.matches(method, targetClass);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Initializing LockAnnotationPointcutAdvisor");
    }
}
