package com.epam.mentoring.outtamemory.permgen;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

import java.util.Date;

/**
 * Created by Andrey on 16.01.2016.
 */
public class OuttaMemoryPermGen {

    public static void main(String[] args) throws ClassNotFoundException {
        for (int i = 0; ; i++) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Date.class);
            enhancer.setCallbackType(FixedValue.class);
            enhancer.setSerialVersionUID((long)i);
            OuttaMemoryPermGen.class.getClassLoader().loadClass(enhancer.createClass().getName());
        }
    }
}
