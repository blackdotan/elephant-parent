package com.ipukr.elephant.utils.function;

import com.ipukr.elephant.utils.function.IPredicate;
import com.ipukr.elephant.utils.stream.IFunction;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TreeArrayHelper {
    /**
     * 数组结构 转 树形结构
     * @param array
     * @return
     */
    public static <T> List<T> totree(List<T> array,
                                     IPredicate<T, T> predicate,
                                     IFunction<T, T> function,
                                     Predicate<T> filter){
        for (T parent : array) {
            for (T child: array) {
                if (predicate.test(parent, child)) {
                    function.apply(parent, child);
                }
            }
        }
        return array.stream().filter(filter).collect(Collectors.toList());
    }

    /**
     * 树形结构 转 数组结构
     * @param roots
     * @param predicate
     * @param function
     * @param <T>
     * @return
     */
    public static <T> List<T> toarray(List<T> roots,
                                      Predicate<T> predicate,
                                      Function<T, List<T>> function,
                                      Consumer<T> after) {
        List<T> arr = new ArrayList<T>();
        for (T root : roots) {
            arr.add(root);
            if (predicate.test(root)) {
                List<T> children = toarray(function.apply(root), predicate, function, after);
                arr = ListUtils.sum(arr, children);
            }
            after.accept(root);
        }
        return arr;
    }
}
