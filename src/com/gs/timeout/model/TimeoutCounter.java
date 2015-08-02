package com.gs.timeout.model;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Timeout counter.
 * 
 * It counts the times of timeout. 
 * 
 * @author gs
 * 
 */
public class TimeoutCounter implements Serializable {

	private static final long serialVersionUID = -603824780596274082L;

	private AtomicInteger timeoutCounter;

	private Integer maxTimeoutTimes;

	private UnitTime lastCheckpoint;

	private UnitTime last2Checkpoint;

	public TimeoutCounter(Integer maxTimeoutTimes) {
		this.maxTimeoutTimes = maxTimeoutTimes;
		timeoutCounter = new AtomicInteger(0);
		lastCheckpoint = new UnitTime(-1L, TimeUnit.MILLISECONDS);
		last2Checkpoint = new UnitTime(-1L, TimeUnit.MILLISECONDS);
	}

	public Integer getMaxTimeoutTimes() {
		return maxTimeoutTimes;
	}

	public void setMaxTimeoutTimes(Integer maxTimeoutTimes) {
		this.maxTimeoutTimes = maxTimeoutTimes;
	}

	public AtomicInteger getTimeoutCounter() {
		return timeoutCounter;
	}
	

	public UnitTime getLastCheckpoint() {
		return lastCheckpoint;
	}

	public UnitTime getLast2Checkpoint() {
		return last2Checkpoint;
	}

}
