package com.blackdotan.elephant.utils.stream;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/4/30.
 */
@FunctionalInterface
public interface IFunction<One, Two> {

    public void apply(One one, Two two);

}
