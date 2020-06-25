package com.ipukr.elephant.simulator;

import io.codearte.jfairy.producer.person.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SimulatorHelperTest {

    @Test
    public void name() {
        Person person = SimulatorHelper.person();
        System.out.println(person.getSex());
        List<String> eles = Arrays.asList("danger", "sup");
        SimulatorHelper.random(eles, 1);
    }


}
