package com.gs.heartbeat;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import com.gs.timeout.model.TimeoutCounter;
import com.gs.timeout.model.UnitTime;

/**
 * Same code with DefaultTimeoutValidator.
 * 
 * Hope it will help to understand the usage of this project.
 * 
 * @author gs
 * 
 */
public class DefaultHeartBeat implements HeartBeat, Serializable {

	private static final long serialVersionUID = 1814697151981423858L;

	private TimeoutCounter timeoutCounter;

	/**
	 * @param maxHeartBeatStopTimes
	 */
	public DefaultHeartBeat(Integer maxHeartBeatStopTimes) {
		timeoutCounter = new TimeoutCounter(maxHeartBeatStopTimes);
	}

	@Override
	public int getCurrentStopCounter() {
		return timeoutCounter.getTimeoutCounter().get();
	}

	public void setMaxHeartBeatStopTimes(Integer maxHeartBeatStopTimes) {
		timeoutCounter.setMaxTimeoutTimes(maxHeartBeatStopTimes);
	}

	@Override
	public void reset() {
		timeoutCounter.getLast2Checkpoint().setTime(-1L);
		timeoutCounter.getTimeoutCounter().set(0);
	}

	@Override
	public Boolean isActiveSideHeartBeatStopped() {
		return timeoutCounter.getTimeoutCounter().get() > timeoutCounter
				.getMaxTimeoutTimes();
	}

	@Override
	public void updateActiveSideLastHearBeatTimepoint(
			UnitTime LastHearBeatTimepoint) {
		timeoutCounter.getLastCheckpoint().setTime(
				LastHearBeatTimepoint.getTime());
		timeoutCounter.getLastCheckpoint().setUnit(
				LastHearBeatTimepoint.getUnit());
		timeoutCounter.getTimeoutCounter().set(0);
	}

	@Override
	public void updateActiveSideLastHearBeatTimepoint(Long time, TimeUnit unit) {
		timeoutCounter.getLastCheckpoint().setTime(time);
		timeoutCounter.getLastCheckpoint().setUnit(unit);
		timeoutCounter.getTimeoutCounter().set(0);
	}

	/**
	 * 
	 * @return TRUE = OK; FALSE = No heart beat in this loop
	 */
	@Override
	public Boolean checkActiveSideLastHeartBeatTimepoint() {
		Boolean result = Boolean.TRUE;
		if (timeoutCounter.getLastCheckpoint().getUnit()
				.toMillis(timeoutCounter.getLastCheckpoint().getTime()) <= timeoutCounter
				.getLastCheckpoint().getUnit()
				.toMillis(timeoutCounter.getLastCheckpoint().getTime())) {
			timeoutCounter.getTimeoutCounter().addAndGet(1);
			result = Boolean.FALSE;
		}
		timeoutCounter.getLast2Checkpoint().setTime(
				timeoutCounter.getLastCheckpoint().getTime());
		timeoutCounter.getLast2Checkpoint().setUnit(
				timeoutCounter.getLastCheckpoint().getUnit());
		return result;
	}

}
