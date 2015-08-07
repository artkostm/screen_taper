package by.artkostm.rxj.processor;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rx.Subscriber;

public class ProcessSubscriber extends Subscriber<Process>
{
    private static final Logger LOGGER = LogManager.getLogger(ProcessSubscriber.class);
    
    private final ProcessBuilder builder;
    private final int sleep;
    
    public ProcessSubscriber(ProcessBuilder builder, int sleep)
    {
        this.builder = builder;
        this.sleep = sleep;
    }
    
    @Override
    public void onCompleted()
    {
        LOGGER.info("onComplete(): completed.");
    }

    @Override
    public void onError(Throwable e)
    {
        LOGGER.warn("onError(): " + e);
    }

    @Override
    public void onNext(Process t)
    {
        try
        {
            t = builder.start();
            Thread.sleep(sleep);
        }
        catch (IOException | InterruptedException e)
        {
            onError(e);
        }
        finally
        {
            if (t != null)
            {
                t.destroy();
                t = null;
            }
        }
    }
}
