package com.blackdotan.elephant.utils.administrative;

import org.junit.Test;

public class AdministrativeDivisionHelperTest {
    @Test
    public void test() {

        ProvinceDivisionInfo pdf = AdministrativeDivisionHelper.getInstance().findByBm("35");
        System.out.println(pdf.toString());
        pdf = AdministrativeDivisionHelper.getInstance().findByJc("闽");
        System.out.println(pdf.toString());

    }
}
