package com.ipukr.elephant.simulator;

import org.junit.Test;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/4/30.
 */
public class SimulatorHelperTest {

    @Test
    public void portrait() {
        System.out.println(SimulatorHelper.portrait(SimulatorHelper.person().getEmail()));
    }

    @Test
    public void random() {
        System.out.println(SimulatorHelper.random(10, 20));
    }

    @Test
    public void mobile() {
        System.out.println(SimulatorHelper.mobile());
    }
}
