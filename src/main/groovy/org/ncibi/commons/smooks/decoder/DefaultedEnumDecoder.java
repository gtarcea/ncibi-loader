package org.ncibi.commons.smooks.decoder;

import java.util.Properties;

import org.milyn.javabean.DataDecodeException;
import org.milyn.javabean.decoders.EnumDecoder;

@SuppressWarnings("serial")
public class DefaultedEnumDecoder extends EnumDecoder
{
    public DefaultedEnumDecoder()
    {
        super();
    }
    
    @Override
    public Object decode(String data) throws DataDecodeException
    {
        try
        {
            return super.decode(data);
        }
        catch (DataDecodeException e)
        {
            Properties config = getConfiguration();
            if (config != null)
            {
                String defaultValue = config.getProperty("default");
                return super.decode(defaultValue);
            }
            else
            {
                throw new DataDecodeException("No default value");
            }
        }
    }
}
