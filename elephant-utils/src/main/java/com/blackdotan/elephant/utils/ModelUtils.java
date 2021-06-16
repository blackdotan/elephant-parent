package com.blackdotan.elephant.utils;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by wmw on 1/16/17.
 */
@Deprecated
public class ModelUtils {
    public static <T, K> void append(List<T> ts,
                                     List<K> ks,
                                     Function<T,String> tF,
                                     Function<K, String> kF,
                                     Function<T, Map> params,
                                     String alia) throws Exception {
        for(T t : ts){
            K tmp = null;
            for(K k : ks){
                if(tF.apply(t).equals(kF.apply(k))){
                    tmp = k;
                }
            }
            params.apply(t).put(alia, tmp);
        }
    }

    public static <T, K> void append(List<T> ts,
                                     Function<List<T>, List<K>> ksF,
                                     Function<T,String> tF,
                                     Function<K, String> kF,
                                     Function<T, Map> params,
                                     String alia) throws Exception {
        List<K> ks = ksF.apply(ts);
        append(ts, ks, tF, kF,params, alia);
    }




}
