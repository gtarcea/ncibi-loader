package org.ncibi.commons.smooks.decoder;

import java.lang.reflect.Constructor;

import org.milyn.javabean.DataDecodeException;
import org.milyn.javabean.DataDecoder;

import com.google.common.base.CharMatcher;

@SuppressWarnings("serial")
public class ReflectiveTypeStringCleanerDecoder extends AbstractConfigurable implements DataDecoder
{

    @Override
    public Object decode(String value) throws DataDecodeException
    {
        String classType = config.getProperty("type");
        String charactersToRemove = config.getProperty("cleaner");
        String cleanedValue = CharMatcher.anyOf(charactersToRemove).removeFrom(value);
        try
        {
            return createInstanceFromCleanedValue(classType, cleanedValue);
        }
        catch (Throwable t)
        {
            throw new DataDecodeException("Error decoding type: " + classType + ", for value: " + value);
        }
        
    }
    
    private Object createInstanceFromCleanedValue(String classType, String cleanedValue) throws Exception
    {
        Class<?> cls = Class.forName(classType);
        Class<?> args[] = {String.class};
        Constructor<?> c = cls.getConstructor(args);
        /*
         * Hack for now.
         */
        if (Boolean.class.equals(cls))
        {
            if ("Y".equals(cleanedValue))
            {
                cleanedValue = "true";
            }
        }
        return c.newInstance(cleanedValue);
    }
}
