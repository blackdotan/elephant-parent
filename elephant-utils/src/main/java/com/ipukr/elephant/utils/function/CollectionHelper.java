package com.ipukr.elephant.utils.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2019/6/6.
 */
public class CollectionHelper {
    /**
     * 集合列表结构转树形结构
     * @param ts    列表集合
     * @param predicate 判断条件 eg: a.id = b.parent
     * @param function 处理函数 eg: a.children 添加 b
     * @param filter 过滤条件（ts过滤） eg：a.parent 为空
     * @param <T>
     * @return
     */
    public static <T> List<T> totree(List<T> ts,
                                     IPredicate<T, T> predicate,
                                     IFunction<T, T> function,
                                     Predicate<T> filter) {
        for (T parent: ts) {
            for (T child: ts) {
                if(predicate.test(parent, child)) {
                    function.apply(parent, child);
                }
            }
        }
        return ts.stream().filter(filter).collect(Collectors.toList());
    }

    /**
     * 集合属性结构转列表结构
     * @param ts      树形集合
     * @param predicate 判断条件 eg: a.children 非空
     * @param function 处理函数(返回子集合) eg: return a.children
     * @param <T>
     * @return
     */
    public static <T> List<T> toarr(List<T> ts,
                                    Predicate<T> predicate,
                                    Function<T, List<T>> function) {
        List<T> arr = new ArrayList<>();
        for (T t: ts) {
            if (predicate.test(t)) {
                List<T> children = function.apply(t);
                List<T> elements = toarr(children, predicate, function);
                arr.addAll(elements);
            }
            arr.add(t);
        }
        return arr;
    }
}
