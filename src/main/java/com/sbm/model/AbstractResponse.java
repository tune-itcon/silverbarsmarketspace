/**
 * 
 */
package com.sbm.model;


import static com.sbm.common.CommonConstants.UNDEFINED_STATUS;

import java.util.Objects;

/**
 * @author duansubramaniam
 * Base class for models holding response status and message
 */
public class AbstractResponse {
	private short status = UNDEFINED_STATUS;
	private String message;
	/**
	 * 
	 */
	public AbstractResponse() {
	}
	/**
	 * @return the status
	 */
	public short getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(short status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		return Objects.hash(message, status);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractResponse other = (AbstractResponse) obj;
		return Objects.equals(message, other.message) && status == other.status;
	}
	

}
