package com.blackdotan.elephant.common.function;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/4/30.
 */
@Deprecated
@FunctionalInterface
public interface IPredicate<One, Two> {

    public Boolean test(One one, Two two);

}
