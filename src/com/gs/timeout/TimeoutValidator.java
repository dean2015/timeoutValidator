package com.gs.timeout;

import com.gs.timeout.model.UnitTime;

/**
 * Interfaces of a timeout validator
 * @author gs
 *
 */
public interface TimeoutValidator {

	/**
	 * Result of Timeout validator
	 * @return
	 */
	Boolean isTimeout();
	
	/**
	 * Update the time of the latest action.
	 * It should be updated by current active thread or process
	 * 
	 * @param lastCheckpoint
	 */
	void updateLastCheckpoint(UnitTime lastCheckpoint);
	
	/**
	 * First, last2Checkpoint will be compared with lastCheckpoint. 
	 * Then, last2Checkpoint will be updated by the value of lastCheckpoint.
	 * 
	 * This method should be invoked by the standby thread or process
	 */
	void checkLastCheckpoint();
	
}
