package com.blackdotan.elephant.simulator;

import com.googlecode.javaewah.EWAHCompressedBitmap;
import io.codearte.jfairy.producer.person.Person;
import org.junit.Test;

import java.io.*;
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


    @Test
    public void t() throws IOException {
        EWAHCompressedBitmap ewahBitmap1 = EWAHCompressedBitmap.bitmapOf(0, 2, 55, 64, 1 << 30);
        ewahBitmap1.add(11);

        EWAHCompressedBitmap ewahBitmap2 = EWAHCompressedBitmap.bitmapOf(1, 3, 64, 1 << 30);
        System.out.println("bitmap 1: " + ewahBitmap1);
        System.out.println("bitmap 2: " + ewahBitmap2);
        System.out.println(ewahBitmap1.cardinality());


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutput dout = new DataOutputStream(baos);
        ewahBitmap1.serialize(dout);
        System.out.println(dout);
        byte[] bytes = baos.toByteArray();
        System.out.println(Arrays.toString(bytes));
        System.out.println(ewahBitmap1.toDebugString());

        // or
        EWAHCompressedBitmap orbitmap = ewahBitmap1.or(ewahBitmap2);
        System.out.println(orbitmap.cardinality());
        System.out.println("bitmap 1 OR bitmap 2: " + orbitmap);
        System.out.println("memory usage: " + orbitmap.sizeInBytes() + " bytes");
        // and
        EWAHCompressedBitmap andbitmap = ewahBitmap1.and(ewahBitmap2);
        System.out.println(andbitmap.cardinality());
        System.out.println("bitmap 1 AND bitmap 2: " + andbitmap);
        System.out.println("memory usage: " + andbitmap.sizeInBytes() + " bytes");
        // xor
        EWAHCompressedBitmap xorbitmap = ewahBitmap1.xor(ewahBitmap2);
        System.out.println("bitmap 1 XOR bitmap 2:" + xorbitmap);
        System.out.println("memory usage: " + xorbitmap.sizeInBytes() + " bytes");
        // fast aggregation over many bitmaps
        EWAHCompressedBitmap ewahBitmap3 = EWAHCompressedBitmap.bitmapOf(5, 55,
                1 << 30);
        EWAHCompressedBitmap ewahBitmap4 = EWAHCompressedBitmap.bitmapOf(4, 66,
                1 << 30);
        System.out.println("bitmap 3: " + ewahBitmap3);
        System.out.println("bitmap 4: " + ewahBitmap4);
    }
}
