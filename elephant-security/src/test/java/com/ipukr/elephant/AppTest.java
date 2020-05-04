package com.ipukr.elephant;

import static org.junit.Assert.assertTrue;

import com.ipukr.elephant.security.Authentification;
import com.ipukr.elephant.security.rabc.*;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void name() {
        Employee employee = Employee.builder().id(1).eno("E10001").build();
        for (int i = 0; i < 10; i++) {
            Role role = Role.builder().name(i + "").build();
            OrganizationAuthentification organization = new OrganizationAuthentification();
            organization.setId(i);
            organization.setKey(i+"");


            employee.getRoles().add(role);
            employee.getSubordinates().add(organization);
        }

        UserAuthentification authentification = (UserAuthentification) employee;
        List<OrganizationAuthentification> subordinates = authentification.getSubordinates();

        System.out.println(subordinates.stream().map(e->e.getKey()).collect(Collectors.toList()));
    }
}
