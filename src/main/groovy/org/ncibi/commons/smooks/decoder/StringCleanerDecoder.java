package org.ncibi.commons.smooks.decoder;

import org.milyn.javabean.DataDecodeException;
import org.milyn.javabean.DataDecoder;

import com.google.common.base.CharMatcher;

@SuppressWarnings("serial")
public class StringCleanerDecoder extends AbstractConfigurable implements DataDecoder
{
    @Override
    public Object decode(String value) throws DataDecodeException
    {
        String charactersToRemove = config.getProperty("cleaner");
        return CharMatcher.anyOf(charactersToRemove).removeFrom(value);
    }

}
