package com.vf.lock.aop;

import cn.hutool.core.util.StrUtil;
import com.vf.lock.annotation.Lock;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

/**
 * 获取锁key
 *
 * @author levin
 * @date 2020/6/13
 * @since 1.0.0
 */
class LockKeyResolver {

    DefaultParameterNameDiscoverer defaultParameterNameDiscoverer
            = new DefaultParameterNameDiscoverer();
    private SpelExpressionParser spelExpressionParser = new SpelExpressionParser();

    /**
     * 通过方法及注解解析锁name
     *
     * @param method 方法
     * @param args   参数
     * @return 锁name
     */
    public String resolveKey(Method method, Object[] args) {
        return resolveNameKey(method) + resolveSpElKey(method, args);
    }

    /**
     * 获取spel表达式解析后的key
     *
     * @param method 方法
     * @param args   参数
     * @return 解析后的key
     */
    private String resolveSpElKey(Method method, Object[] args) {
        Lock annotation = method.getAnnotation(Lock.class);
        String spEl = annotation.spEl();
        if (StrUtil.isBlank(spEl)) {
            return StrUtil.EMPTY;
        }
        MethodBasedEvaluationContext methodBasedEvaluationContext
                = new MethodBasedEvaluationContext(null, method, args, defaultParameterNameDiscoverer);
        String spElKey = spelExpressionParser.parseExpression(spEl).getValue(methodBasedEvaluationContext).toString();
        return spElKey;
    }

    /**
     * 解析name
     *
     * @param method 方法
     * @return name
     */
    private String resolveNameKey(Method method) {
        Lock annotation = method.getAnnotation(Lock.class);
        String name = annotation.name();
        if (StrUtil.isBlank(name)) {
            return method.getDeclaringClass().getSimpleName() + "#" + method.getName();
        } else {
            return name;
        }
    }
}
