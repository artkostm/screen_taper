package by.artkostm.rxj.cli;

import java.util.ListIterator;

import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.ParseException;

public class ExtendedParser extends GnuParser
{
    private final boolean ignoreUnrecognizedOption;

    public ExtendedParser(final boolean ignoreUnrecognizedOption)
    {
        this.ignoreUnrecognizedOption = ignoreUnrecognizedOption;
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected void processOption(final String arg, final ListIterator iter) throws ParseException
    {
        final boolean hasOption = getOptions().hasOption(arg);

        if (hasOption || !ignoreUnrecognizedOption)
        {
            super.processOption(arg, iter);
        }
    }
}
