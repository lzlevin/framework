package com.vf.autoconfigure;

import com.vf.log.listener.Slf4jLogListener;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

/**
 * @author levin
 * @see LogConfiguration
 * @see Slf4jLogListener
 * @since 1.0.0
 */
@Order(Ordered.LOWEST_PRECEDENCE)
public class Slf4jCondition implements Condition {
    /**
     * Determine if the condition matches.
     *
     * @param context  the condition context
     * @param metadata metadata of the {@link AnnotationMetadata class}
     *                 or {@link MethodMetadata method} being checked
     * @return {@code true} if the condition matches and the component can be registered,
     * or {@code false} to veto the annotated component's registration
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Boolean property = context.getEnvironment().getProperty(LogConfiguration.DEFAULT_PREFIX + ".slf4j", boolean.class);
        if (null == property) {
            property = LogConfiguration.DEFAULT_SLF4j_ENABLE;
        }
        return property;
    }
}
