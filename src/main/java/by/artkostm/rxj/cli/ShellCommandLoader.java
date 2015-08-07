package by.artkostm.rxj.cli;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShellCommandLoader
{
    private static final Logger LOGGER = LogManager.getLogger(ShellCommandLoader.class);
    private static final String CMD_FILE_NAME = "shell_command";
    private static final String REPLACEMENT_STR = "#{PATH_TO_ADB}";
    
    public String load(String pathToAdb)
    {
        final InputStream in = ShellCommandLoader.class.getClassLoader().getResourceAsStream(CMD_FILE_NAME);
        try
        {
            final String cmd = IOUtils.toString(in);
            return prepareCmd(cmd, pathToAdb);
        }
        catch (IOException e)
        {
            LOGGER.error("Cannot read cmd from " + CMD_FILE_NAME + " file", e);
            throw new RuntimeException("Cannot read cmd from " + CMD_FILE_NAME + " file", e);
        }
        finally
        {
            IOUtils.closeQuietly(in);
        }
    }
    
    private String prepareCmd(String cmd, String pathToAdb)
    {
        if (cmd == null)
        {
            return null;
        }
        
        return cmd.replace(REPLACEMENT_STR, pathToAdb);
    }
}
