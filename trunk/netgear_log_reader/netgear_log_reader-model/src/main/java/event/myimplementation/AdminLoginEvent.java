package event.myimplementation;

import event.implementation.AbstractEventWithSource;

public class AdminLoginEvent extends AbstractEventWithSource {

	private Boolean failure;

	public Boolean isFailure() {
		return failure;
	}

	public void setFailure(Boolean failure) {
		this.failure = failure;
	}

}
