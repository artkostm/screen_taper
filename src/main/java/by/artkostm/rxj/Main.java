package by.artkostm.rxj;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artkostm.rxj.cli.ExtendedParser;
import by.artkostm.rxj.cli.ShellCommandLoader;
import by.artkostm.rxj.cli.TaperCliOptionsContainer;
import by.artkostm.rxj.processor.BuilderTapProcessor;
import by.artkostm.rxj.processor.TapProcessor;

public class Main 
{
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    
    private static final int DEFAULT_SLEEP_DALAY = 105;
    private static final int DEFAULT_TAP_COUNT = 10000;
    
    public static void main(String[] args)
    {
        final CommandLineParser parser = new ExtendedParser(true);
        final ShellCommandLoader commandLoader = new ShellCommandLoader();
        try
        {
            final CommandLine cmd = parser.parse(TaperCliOptionsContainer.OPTIONS, args);

            final String pathToAdb = cmd.getOptionValue(TaperCliOptionsContainer.ADB_PATH_OPT);
            final String sleepDalayStr = cmd.getOptionValue(TaperCliOptionsContainer.SLEEP_TIME_OPT, 
                String.valueOf(DEFAULT_SLEEP_DALAY));
            final String times = cmd.getOptionValue(TaperCliOptionsContainer.TIMES_OPT, 
                String.valueOf(DEFAULT_TAP_COUNT));
            
            final int sleepDalay = Integer.parseInt(sleepDalayStr);
            final int tapCount = Integer.parseInt(times);
            final String command = commandLoader.load(pathToAdb);

            LOGGER.info("\ncmd: " + command + "\nsleep: " + sleepDalay + "\ncount: " + tapCount);
            TapProcessor processor = new BuilderTapProcessor(command, sleepDalay, tapCount);
            processor.processTap();
        }
        catch (final ParseException e)
        {
            LOGGER.error("Parse error: " + e.getMessage());
            printHelp();
        }
    }
    
    private static void printHelp()
    {
        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("screen-taper [OPTIONS]...", TaperCliOptionsContainer.OPTIONS);
    }
}
