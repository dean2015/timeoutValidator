package com.gs.heartbeat;

import com.gs.timeout.model.UnitTime;

public interface HeartBeat {

	/**
	 * If Active heart beat has stopped.
	 * @return
	 */
	Boolean isActiveHeartBeatStopped();
	
	void updateActiveLastHearBeatTimepoint(UnitTime LastHearBeatTimepoint);
	
	Boolean checkActiveLashHeartBeatTimepoint();
	
}
