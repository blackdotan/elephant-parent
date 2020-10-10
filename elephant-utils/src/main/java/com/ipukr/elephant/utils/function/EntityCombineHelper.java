package com.ipukr.elephant.utils.function;

import java.util.List;

/**
 * 实体合并帮助类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/4/30.
 */
public class EntityCombineHelper {
    /**
     * @param ts
     * @param ks
     * @param predicate
     * @param function
     */
    public static <T, K> void combine(List<T> ts, List<K> ks, IPredicate<T, K> predicate, IFunction<T, K> function) {
        for (T supply : ts) {
            for(K user : ks) {
                if(predicate.test(supply, user)) {
                    function.apply(supply, user);
                }
            }
        }
    }
}
