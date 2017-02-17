/* $Header: biappsinst/projects/bilcmcommon/src/oracle/bi/framework/util/ExecuteWithTimeout.java vivekv_bug-20730667/4 2015/06/24 23:48:41 vivekv Exp $ */

/* Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.*/

/*
 DESCRIPTION
 <This class will be used to handle timeout. On using this class any task should finish within a already defined time limit otherwise an Exception will be thrown.>

 PRIVATE CLASSES
 <list of private classes defined - with one-line descriptions>

 NOTES
 <other useful comments, qualifications, etc.>

 MODIFIED    (MM/DD/YY)
 vivekv      06/18/15 - Bug 20730667 - ADD TIMEOUT LOGIC FOR ALL MBEAN RELATED ACTIVITIES
 vivekv      06/18/15 - Creation
 */

/**
 *  @version $Header: biappsinst/projects/bilcmcommon/src/oracle/bi/framework/util/ExecuteWithTimeout.java vivekv_bug-20730667/4 2015/06/24 23:48:41 vivekv Exp $
 *  @author  vivekv  
 *  @since   11.1.12.0.0
 */

package com.prv.jmx;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class will be used to handle timeout. On using this class any task
 * should finish within a already defined time limit otherwise an Exception will
 * be thrown.
 * 
 * @author vivekv
 * 
 */
public abstract class ExecuteWithTimeout {

    private static final Logger _logger = Logger.getLogger(ExecuteWithTimeout.class.getName());

    private long timeoutSecs;
    private Object returnValue = null;

    public ExecuteWithTimeout(final long timeoutSec) {
        this.timeoutSecs = timeoutSec;
    }

    public long getActualTimeoutSecs() {
        return this.timeoutSecs;
    }

    public Object getReturnValue() {
        return this.returnValue;
    }

    public abstract Object execute() throws Exception;

    /**
     * To handle Exception type of executeWithTimeout method. If the Exception
     * occurred in executeWithTimeout method is of type Exception and not null
     * then return it otherwise return a RuntimeException.
     * 
     * @param Throwable
     *            type as input
     * @return Exception if input argument is of Exception type otherwise
     *         RuntimeException
     */
    private Exception handleException(final Throwable e) {
        if (e != null && e instanceof Exception)
            return (Exception) e;
        else {
            return new RuntimeException("Error: Exception type is either null or not a instance of Exception type.");
        }
    }

    /**
     * Handle timeout while executing MBEAN operation
     * 
     * @return Object type. The outcome of input task execution
     * @throws Exception
     *             if it occurs while execution of input task
     */
    public Object executeWithTimeout() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Executor tm = new Executor(this);
        Future<Object> future = executor.submit(tm);
        executor.shutdown();
        try {
            this.returnValue = future.get(this.timeoutSecs, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            _logger.log(Level.SEVERE, "Exception while invoking ExecutorService. Error: " + e.getMessage(), e);
            throw handleException(e.getCause());
        } catch (InterruptedException e) {
            _logger.log(Level.SEVERE, "Interrupted exception while executing the task. Error: " + e.getMessage(), e);
            throw handleException(e.getCause());
        } catch (TimeoutException e) {
            future.cancel(true);
            _logger.log(Level.SEVERE, "Execution timed out after [" + getActualTimeoutSecs() + " ] secs]. Error: " + e.getMessage(), e);
            throw handleException(e.getCause());
        } catch (Exception e) {
            _logger.log(Level.SEVERE, "Exception while executing the task. Error: " + e.getMessage(), e);
            throw e;
        } finally {
            executor.shutdown();
        }
        return this.returnValue;
    }

    private static class Executor implements Callable<Object> {

        private ExecuteWithTimeout process;
        Object returnValue;

        public Executor(ExecuteWithTimeout process) {
            this.process = process;
        }

        public Object call() throws Exception {
            try {
                returnValue = process.execute();
            } catch (Exception e) {
                _logger.log(Level.SEVERE, "Exception while executing the execute API. Error: " + e.getMessage(), e);
                throw e;
            }
            return returnValue;
        }
    }
}