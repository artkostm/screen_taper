package by.artkostm.rxj.cli;

import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

@SuppressWarnings("static-access")
public class TaperCliOptionsContainer
{
    public static final Options OPTIONS;
    
    public static final String ADB_PATH_OPT = "adb";
    public static final String SLEEP_TIME_OPT = "s";
    public static final String TIMES_OPT = "t";
    public static final String HELP_OPT = "h";

    static
    {
        OPTIONS = new Options();
        OPTIONS.addOption(OptionBuilder.isRequired(true).hasArg().withLongOpt("adb")
                .withDescription("Path to the adb folder").create(ADB_PATH_OPT));
        OPTIONS.addOption(OptionBuilder.isRequired(true).hasArg().withLongOpt("sleep")
                .withDescription("Sleep dalay").create(SLEEP_TIME_OPT));
        OPTIONS.addOption(OptionBuilder.isRequired(true).hasArg().withLongOpt("times")
                .withDescription("Times").create(TIMES_OPT));
        OPTIONS.addOption(OptionBuilder.isRequired(false).hasArg(false).withLongOpt("help")
                .withDescription("Print current help").create(HELP_OPT));
    }
}
