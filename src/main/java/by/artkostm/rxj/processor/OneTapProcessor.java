package by.artkostm.rxj.processor;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class OneTapProcessor implements Func1<ProcessBuilder, Observable<? extends Process>>
{
    private final int tapCount;
    
    public OneTapProcessor(int tapCount)
    {
        this.tapCount = tapCount;
    }
    
    @Override
    public Observable<? extends Process> call(ProcessBuilder t)
    {
        Process[] ps = new Process[tapCount];
        return Observable.from(ps).observeOn(Schedulers.io());
    }
}
