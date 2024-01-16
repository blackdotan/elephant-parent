package com.blackdotan.elephant.common.mybatis;

import com.blackdotan.elephant.common.mybatis.utils.ConditionHelper;
import com.blackdotan.elephant.utils.DateUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

public class ConditionHelperTest {
    @Test
    public void in() {
        System.out.println(ConditionHelper.in("Type", Arrays.asList()));
        System.out.println(ConditionHelper.in("Type", Arrays.asList(1)));
        System.out.println(ConditionHelper.in("Type", Arrays.asList(1,2,3)));
        System.out.println(ConditionHelper.in("Type", Arrays.asList("1", "12", "3123")));
    }

    @Test
    public void between() {
        Date begin = DateUtils.now();
        Date end = DateUtils.nowWithOffset(1);
        System.out.println(ConditionHelper.between("Day", begin, end));
    }


}
