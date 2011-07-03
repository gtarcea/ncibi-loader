package org.ncibi.commons.smooks.decoder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class ReflectionTest
{
    @Test
    public void testReflection() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
        Class<?> cls = Class.forName("java.lang.Integer");
        Class<?> args[] = {String.class};
        Constructor<?> c = cls.getConstructor(args);
        System.out.println(c.newInstance("5"));
    }
}
