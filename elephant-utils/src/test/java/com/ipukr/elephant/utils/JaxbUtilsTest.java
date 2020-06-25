package com.ipukr.elephant.utils;

import com.ipukr.elephant.utils.domain.DPOrganization;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * 请描述类 <br>
 *
 * @author ljx
 * <p>
 * Created by ljx wu on 2020-03-31 22:27
 */
public class JaxbUtilsTest {
    @Test
    public void name() throws IOException, JAXBException {
        String path = JaxbUtilsTest.class.getResource("/").getPath().concat("text.xml");
        FileInputStream fins = new FileInputStream(new File(path));
        DPOrganization map = JaxbUtils.xmlToBean(fins, DPOrganization.class);
        System.out.println(JsonUtils.parserObj2String(map));
    }
}
