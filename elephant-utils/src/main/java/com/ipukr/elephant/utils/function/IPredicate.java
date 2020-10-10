package com.ipukr.elephant.utils.function;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/4/30.
 */
@FunctionalInterface
public interface IPredicate<One, Two> {

    public Boolean test(One one, Two two);

}
