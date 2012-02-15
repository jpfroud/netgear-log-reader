package event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import event.myimplementation.AdminLoginFailureEvent;
import event.myimplementation.AdminLoginSuccessEvent;
import event.myimplementation.DHCPEvent;
import event.myimplementation.DosAttackEvent;
import event.myimplementation.EmailSentEvent;
import event.myimplementation.InitializedEvent;
import event.myimplementation.InternetConnectedEvent;
import event.myimplementation.InternetDisconnectedEvent;
import event.myimplementation.LanAccessFromRemoteEvent;
import event.myimplementation.RemoteLoginFailureEvent;
import event.myimplementation.RemoteLoginSuccessEvent;
import event.myimplementation.Self2WANEvent;
import event.myimplementation.ServiceBlockedEvent;
import event.myimplementation.TimeSynchroEvent;
import event.myimplementation.UPnPEvent;

public class EventFactory {

	public static EventType textLineToEvent(String s) {
		String eventName = getEventName(s);

		if (AdminLoginFailureEvent.NAME.equals(eventName)) {
			return new AdminLoginFailureEvent(s);
		}
		if (AdminLoginSuccessEvent.NAME.equals(eventName)) {
			return new AdminLoginSuccessEvent(s);
		}
		if (DHCPEvent.NAME.equals(eventName)) {
			return new DHCPEvent(s);
		}
		if (DosAttackEvent.NAME.equals(eventName)) {
			return new DosAttackEvent(s);
		}
		if (EmailSentEvent.NAME.equals(eventName)) {
			return new EmailSentEvent(s);
		}
		if (InitializedEvent.NAME.equals(eventName)) {
			return new InitializedEvent(s);
		}
		if (InternetConnectedEvent.NAME.equals(eventName)) {
			return new InternetConnectedEvent(s);
		}
		if (InternetDisconnectedEvent.NAME.equals(eventName)) {
			return new InternetDisconnectedEvent(s);
		}
		if (LanAccessFromRemoteEvent.NAME.equals(eventName)) {
			return new LanAccessFromRemoteEvent(s);
		}
		if (RemoteLoginFailureEvent.NAME.equals(eventName)) {
			return new RemoteLoginFailureEvent(s);
		}
		if (RemoteLoginSuccessEvent.NAME.equals(eventName)) {
			return new RemoteLoginSuccessEvent(s);
		}
		if (Self2WANEvent.NAME.equals(eventName)) {
			return new Self2WANEvent(s);
		}
		if (ServiceBlockedEvent.NAME.equals(eventName)) {
			return new ServiceBlockedEvent(s);
		}
		if (TimeSynchroEvent.NAME.equals(eventName)) {
			return new TimeSynchroEvent(s);
		}
		if (UPnPEvent.NAME.equals(eventName)) {
			return new UPnPEvent(s);
		}
		return null;
	}

	public static String getEventName(String s) {
		Pattern p = Pattern.compile("^\\[(.+?):.+\\]");
		Matcher m = p.matcher(s);
		if (m.find()) {
			return m.group(1);
		}
		p = Pattern.compile("^\\[(.+?)\\].+");
		m = p.matcher(s);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}

}
