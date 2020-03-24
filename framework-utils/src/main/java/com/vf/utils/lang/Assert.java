package com.vf.utils.lang;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.vf.common.exception.BusinessException;

import java.util.Collection;
import java.util.Iterator;


/**
 * 断言工具类
 *
 * @author levin
 * @since 1.0.0
 */
public class Assert extends cn.hutool.core.lang.Assert {

    /**
     * 断言是否为真，如果为 {@code true} 抛出 {@code BusinessException} 异常<br>
     *
     * @param expression       布尔值
     * @param errorMsgTemplate 错误抛出异常附带的消息模板，变量用{}代替
     * @param params           参数列表
     * @throws BusinessException if expression is {@code true}
     */
    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) throws BusinessException {
        if (expression) {
            throw new BusinessException(StrUtil.format(errorMsgTemplate, params));
        }
    }

    /**
     * 断言是否为真，如果为 {@code true} 抛出 {@code BusinessException} 异常<br>
     *
     * @param expression 布尔值
     * @throws BusinessException if expression is {@code true}
     */
    public static void isTrue(boolean expression) throws BusinessException {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    /**
     * 断言是否为假，如果为 {@code false} 抛出 {@code BusinessException} 异常<br>
     *
     * @param expression       布尔值
     * @param errorMsgTemplate 错误抛出异常附带的消息模板，变量用{}代替
     * @param params           参数列表
     * @throws BusinessException if expression is {@code false}
     */
    public static void isFalse(boolean expression, String errorMsgTemplate, Object... params) throws BusinessException {
        isTrue(!expression, errorMsgTemplate, params);
    }

    /**
     * 断言是否为假，如果为 {@code false} 抛出 {@code BusinessException} 异常<br>
     *
     * @param expression 布尔值
     * @throws BusinessException if expression is {@code false}
     */
    public static void isFalse(boolean expression) throws BusinessException {
        isFalse(expression, "[Assertion failed] - this expression must be false");
    }

    /**
     * 断言对象是否为{@code null} ，如果为{@code null} 抛出{@link BusinessException} 异常
     *
     * @param object           被检查的对象
     * @param errorMsgTemplate 消息模板，变量使用{}表示
     * @param params           参数列表
     * @throws BusinessException if the object is {@code null}
     */
    public static void isNull(Object object, String errorMsgTemplate, Object... params) throws BusinessException {
        isTrue(object == null, errorMsgTemplate, params);
    }

    /**
     * 断言对象是否为{@code null} ，如果为{@code null} 抛出{@link BusinessException} 异常
     *
     * @param object 被检查对象
     * @throws BusinessException if the object is  {@code null}
     */
    public static void isNull(Object object) throws BusinessException {
        isNull(object, "[Assertion failed] - the object argument must be not null");
    }

    /**
     * 断言对象是否不为{@code null} ，如果不为{@code null} 抛出{@link BusinessException} 异常
     *
     * @param <T>              被检查对象泛型类型
     * @param object           被检查对象
     * @param errorMsgTemplate 错误消息模板，变量使用{}表示
     * @param params           参数
     * @return 被检查后的对象
     * @throws BusinessException if the object is not {@code null}
     */
    public static <T> T notNull(T object, String errorMsgTemplate, Object... params) throws BusinessException {
        if (object == null) {
            throw new BusinessException(StrUtil.format(errorMsgTemplate, params));
        }
        return object;
    }

    /**
     * 断言对象是否不为{@code null} ，如果不为{@code null} 抛出{@link BusinessException} 异常
     *
     * @param <T>    被检查对象类型
     * @param object 被检查对象
     * @return 非空对象
     * @throws BusinessException if the object is {@code null}
     */
    public static <T> T notNull(T object) throws BusinessException {
        return notNull(object, "[Assertion failed] - this argument is required; it must be null");
    }

    /**
     * 检查给定字符串是否空白（null、空串或只包含空白符），空白抛出 {@link BusinessException}
     *
     * @param <T>              字符串类型
     * @param text             被检查字符串
     * @param errorMsgTemplate 错误消息模板，变量使用{}表示
     * @param params           参数
     * @return 非空字符串
     * @throws BusinessException 被检查字符串为空白
     * @see StrUtil#isBlank(CharSequence)
     */
    public static <T extends CharSequence> T isBlank(T text, String errorMsgTemplate, Object... params) throws BusinessException {
        isTrue(StrUtil.isNotBlank(text), errorMsgTemplate, params);
        return text;
    }

    /**
     * 检查给定字符串是否空白（null、空串或只包含空白符），空白抛出 {@link BusinessException}
     *
     * @param <T>  字符串类型
     * @param text 被检查字符串
     * @return 非空字符串
     * @throws BusinessException 被检查字符串为空白
     * @see StrUtil#isBlank(CharSequence)
     */
    public static <T extends CharSequence> T isBlank(T text) throws BusinessException {
        isTrue(StrUtil.isNotBlank(text), "[Assertion failed] - this argument is required; it must be not blank");
        return text;
    }

    /**
     * 断言给定集合非空
     *
     * @param <T>              集合元素类型
     * @param collection       被检查的集合
     * @param errorMsgTemplate 错误消息模板，变量使用{}表示
     * @param params           参数
     * @return 被检查集合
     * @throws BusinessException if the collection is {@code null} or has no elements
     */
    public static <T> Collection<T> isEmpty(Collection<T> collection, String errorMsgTemplate, Object... params) throws BusinessException {
        isTrue(CollectionUtil.isEmpty(collection), errorMsgTemplate, params);
        return collection;
    }

    /**
     * 断言给定集合非空
     *
     * @param <T>        集合元素类型
     * @param collection 被检查的集合
     * @return 被检查集合
     * @throws BusinessException if the collection is {@code null} or has no elements
     */
    public static <T> Collection<T> isEmpty(Collection<T> collection) throws BusinessException {
        isEmpty(collection, "[Assertion failed] - this argument is required; it must be not empty");
        return collection;
    }

    /**
     * 断言给定迭代器非空
     *
     * @param <T>              集合元素类型
     * @param iterator         被检查的集合
     * @param errorMsgTemplate 错误消息模板，变量使用{}表示
     * @param params           参数
     * @return 被检查集合
     * @throws BusinessException if the iterator is {@code null} or has no elements
     */
    public static <T> Iterator<T> isEmpty(Iterator<T> iterator, String errorMsgTemplate, Object... params) throws BusinessException {
        isTrue(CollectionUtil.isEmpty(iterator), errorMsgTemplate, params);
        return iterator;
    }

    /**
     * 断言给定迭代器非空
     *
     * @param <T>      集合元素类型
     * @param iterator 被检查的集合
     * @return 被检查集合
     * @throws BusinessException if the iterator is {@code null} or has no elements
     */
    public static <T> Iterator<T> isEmpty(Iterator<T> iterator) throws BusinessException {
        isTrue(CollectionUtil.isEmpty(iterator), "[Assertion failed] - this argument is required; it must be not null and not empty");
        return iterator;
    }
}
