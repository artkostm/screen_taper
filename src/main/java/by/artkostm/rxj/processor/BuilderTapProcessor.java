package by.artkostm.rxj.processor;

import rx.Observable;

public class BuilderTapProcessor implements TapProcessor
{
    private final String cmd;
    private final int sleepDalay;
    private final int tapCount;
    private final ProcessBuilder builder;
    
    public BuilderTapProcessor(String cmd, int sleepDalay, int tapCount)
    {
        super();
        this.cmd = cmd;
        this.sleepDalay = sleepDalay;
        this.tapCount = tapCount;
        builder = new ProcessBuilder("cmd.exe", "/c", cmd);
    }

    @Override
    public void processTap()
    {
        getBuilder().flatMap(new OneTapProcessor(tapCount)).subscribe(new ProcessSubscriber(builder, sleepDalay));
    }
    
    private Observable<ProcessBuilder> getBuilder()
    {
        return Observable.just(builder);
    }

    public String getCmd()
    {
        return cmd;
    }

    public int getSleepDalay()
    {
        return sleepDalay;
    }

    public int getTapCount()
    {
        return tapCount;
    }
}
