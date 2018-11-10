package com.athena.activity.utils;

import java.time.Duration;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodPoller<T> {

	Duration pollDurationSec;
    int pollIntervalMillis;

    private Supplier<T> pollMethod = null;
    private Predicate<T> pollResultPredicate = null;

    public MethodPoller() {
    }

    public MethodPoller<T> poll(int pollIntervalMillis) {
        this.pollIntervalMillis = pollIntervalMillis;
        return this;
    }
    
    public MethodPoller<T> poll(Duration pollDurationSec, int pollIntervalMillis) {
        this.pollDurationSec = pollDurationSec;
        this.pollIntervalMillis = pollIntervalMillis;
        return this;
    }
    
    public MethodPoller<T> method(Supplier<T> supplier) {
        pollMethod = supplier;
        return this;
    }

    public MethodPoller<T> until(Predicate<T> predicate) {
        pollResultPredicate = predicate;
        return this;
    }

    public T execute()  {

        T result = null;
        boolean pollSucceeded = false;
        
        while (!pollSucceeded) {
            result = pollMethod.get();
            pollSucceeded = pollResultPredicate.test(result);
        }

        return result;
    }
    
}
