package com.blackdotan.elephant.utils.function;

import com.blackdotan.elephant.utils.domain.Organization;
import com.blackdotan.elephant.utils.JsonUtils;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TreeArrayHelperTest {
    @Test
    public void name() throws IOException {
        List<Organization> organizations = JsonUtils.parserString2CollectionWithType(new File(this.getClass().getResource("/").getPath().concat("data/json/organization.json")), List.class, Organization.class);


        List<Organization> tree = TreeArrayHelper.totree(
                organizations,
                // 条件
                (parent, child) -> {
                    return parent.getId().equals(child.getSuperiorId());
                },
                // Opt
                (parent, child) -> {
                    parent.getChildren().add(child);
                },
                // Filter
                (organization) -> {
                    return organization.getSuperiorId() == null;
                });
        System.out.println(JsonUtils.parserObj2String(tree));

        System.out.println("------------------");

        List<Organization> list = TreeArrayHelper.toarray(
                tree,
                // 条件
                (organization) ->  {
                    return !organization.getChildren().isEmpty();
                },
                // Opt
                organization -> {
                    return organization.getChildren();
                },
                // After
                organization -> {
                    organization.setChildren(Lists.newArrayList());
                });
        System.out.println(JsonUtils.parserObj2String(list));
    }
}
