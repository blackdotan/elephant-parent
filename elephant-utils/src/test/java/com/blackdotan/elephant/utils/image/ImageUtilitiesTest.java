package com.blackdotan.elephant.utils.image;

import com.blackdotan.elephant.utils.images.ImageUtilities;
import org.junit.Test;

import java.io.*;
import java.util.Base64;


public class ImageUtilitiesTest {
    @Test
    public void name() throws IOException {
        File file = new File(this.getClass().getResource("/").getPath().concat("bximg.txt"));
        FileInputStream ins = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(ins);
        BufferedReader reader = new BufferedReader(isr);

        StringBuffer sb = new StringBuffer();
        String text = null;
        while((text = reader.readLine()) != null){
            sb.append(text);
        }
        System.out.println(sb.length());
        String nbl = ImageUtilities.tobase64(Base64.getDecoder().decode(sb.toString()));
        System.out.println(nbl.length());

        System.out.println(nbl);
    }
}
