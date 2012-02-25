package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;
import utils.IoUtils;
import event.EventFactory;
import event.EventType;
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

public class ParserTestCase extends TestCase {

	public void testParseFile() {
		File input = new File("src/test/resources/listOfEventsSoFar.txt");
		int linesInFile = 0;
		try {
			linesInFile = getLinesNumber(input);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		List<EventType> listEvents = null;
		try {
			listEvents = LogParser.parse(input);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		// checks
		assertEquals(linesInFile, listEvents.size());
	}

	public void testParseFolder() {
		File input = new File("src/test/resources/folderToScan");
		List<EventType> listEvents = null;
		try {
			listEvents = LogParser.parse(input);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		// checks
		assertEquals(5, listEvents.size());
	}

	public void testCountLines() {
		File input = new File("src/test/resources/testCountLines.txt");
		try {
			assertEquals(28, getLinesNumber(input));
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testCompareByDate() {
		File input = new File("src/test/resources/testDate.txt");
		List<EventType> listEvents = null;
		try {
			listEvents = LogParser.parse(input);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		// checks
		Collections.sort(listEvents);
		DHCPEvent eventType = (DHCPEvent) listEvents.get(0);
		assertEquals("192.168.0.100", eventType.getSource().getStringValue());
	}

	public void testFilterType() {
		File input = new File("src/test/resources/testCountLines.txt");
		List<EventType> listEvents = null;
		try {
			listEvents = LogParser.parse(input);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}

		// checks
		List<EventType> listUpnp = LogParser
				.filter(listEvents, UPnPEvent.class);
		assertEquals(2, listUpnp.size());
		List<EventType> listdhcp = LogParser
				.filter(listEvents, DHCPEvent.class);
		assertEquals(10, listdhcp.size());
	}

	private int getLinesNumber(File input) throws IOException {
		BufferedReader buf = new BufferedReader(new FileReader(input));
		int count = 0;
		boolean continu = true;
		while (continu) {
			String line = buf.readLine();
			if (null != line && 0 < line.trim().length()) {
				continu = true;
				count++;
			} else {
				continu = false;
			}
		}
		buf.close();
		return count;
	}

	public void testParseAdminLogin() {
		String toParse = "[Admin login] from source 192.168.0.106, Friday, Jan 27,2012 17:39:45";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(AdminLoginSuccessEvent.class, event.getClass());
		assertEquals("192.168.0.106", ((AdminLoginSuccessEvent) event)
				.getSource().getStringValue());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 17, 39, 45);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
	}

	public void testParseAdminLoginFailure() {
		String toParse = "[Admin login failure] from source 192.168.0.106, Friday, Jan 27,2012 17:39:45";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(AdminLoginFailureEvent.class, event.getClass());
		assertEquals("192.168.0.106", ((AdminLoginFailureEvent) event)
				.getSource().getStringValue());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 17, 39, 45);
		assertEquals(cal.getTime().toString().toString(), event
				.getDateOfEvent().toString().toString());
	}

	public void testParseDHCP() {
		String toParse = "[DHCP IP: (192.168.0.100)] to MAC address 04:1E:64:26:23:72, Friday, Jan 27,2012 12:25:00";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(DHCPEvent.class, event.getClass());
		assertEquals("192.168.0.100", ((DHCPEvent) event).getSource()
				.getStringValue());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 12, 25, 00);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
		assertEquals("04:1E:64:26:23:72", ((DHCPEvent) event).getMacAdress()
				.getStringValue());
	}

	public void testParseDOSAck() {
		String toParse = "[DoS attack: ACK Scan] attack packets in last 20 sec from ip [209.85.148.193], Wednesday, Feb 01,2012 17:56:33";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(DosAttackEvent.class, event.getClass());
		assertEquals("209.85.148.193", ((DosAttackEvent) event).getSource()
				.getStringValue());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 1, 1, 17, 56, 33);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
		assertEquals("ACK Scan", ((DosAttackEvent) event).getParameter()
				.getName());
	}

	public void testParseDOSFIN() {
		String toParse = "[DoS attack: FIN Scan] attack packets in last 20 sec from ip [209.85.148.193], Wednesday, Feb 01,2012 17:56:33";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(DosAttackEvent.class, event.getClass());
		assertEquals("209.85.148.193", ((DosAttackEvent) event).getSource()
				.getStringValue());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 1, 1, 17, 56, 33);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
		assertEquals("FIN Scan", ((DosAttackEvent) event).getParameter()
				.getName());
	}

	public void testParseInitFirmware() {
		String toParse = "[Initialized, firmware version: V1.0.0.70_1.0.18] Wednesday, Jan 01,2003 00:00:00";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(InitializedEvent.class, event.getClass());
		Calendar cal = Calendar.getInstance();
		cal.set(2003, 0, 1, 0, 0, 0);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
		assertEquals("firmware version: V1.0.0.70_1.0.18",
				((InitializedEvent) event).getParameter().getName());
	}

	public void testParseInternetConnected() {
		String toParse = "[Internet connected] IP address: 178.250.210.109, Friday, Jan 27,2012 12:48:37";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(InternetConnectedEvent.class, event.getClass());
		assertEquals("178.250.210.109", ((InternetConnectedEvent) event)
				.getSource().getStringValue());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 12, 48, 37);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
	}

	public void testParseInternetDisconnected() {
		String toParse = "[Internet disconnected] Wednesday, Jan 01,2003 00:00:03";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(InternetDisconnectedEvent.class, event.getClass());
		Calendar cal = Calendar.getInstance();
		cal.set(2003, 0, 1, 0, 0, 3);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
	}

	public void testParseLanRemote() {
		String toParse = "[LAN access from remote] from 178.151.23.164:38234 to 192.168.0.112:445 Friday, Jan 27,2012 12:57:46";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(LanAccessFromRemoteEvent.class, event.getClass());
		assertEquals("178.151.23.164", ((LanAccessFromRemoteEvent) event)
				.getSource().getStringValue());
		assertEquals((Integer) 38234,
				((LanAccessFromRemoteEvent) event).getSourcePort());
		assertEquals("192.168.0.112", ((LanAccessFromRemoteEvent) event)
				.getDestination().getStringValue());
		assertEquals((Integer) 445,
				((LanAccessFromRemoteEvent) event).getDestinationPort());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 12, 57, 46);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
	}

	public void testParseRemoteLogin() {
		String toParse = "[Remote login] from source 160.53.250.126, Friday, Jan 27,2012 12:49:54";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(RemoteLoginSuccessEvent.class, event.getClass());
		assertEquals("160.53.250.126", ((RemoteLoginSuccessEvent) event)
				.getSource().getStringValue());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 12, 49, 54);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
	}

	public void testParseRemoteLoginFailure() {
		String toParse = "[Remote login failure] from source 160.53.250.126, Friday, Jan 27,2012 12:49:54";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(RemoteLoginFailureEvent.class, event.getClass());
		assertEquals("160.53.250.126", ((RemoteLoginFailureEvent) event)
				.getSource().getStringValue());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 12, 49, 54);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
	}

	public void testParseSelf2Wan() {
		String toParse = "[Self2WAN ICMP type b Detected!] To prevent from revealing router's activity, this packet is dropped! Friday, Jan 27,2012 22:05:11";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(Self2WANEvent.class, event.getClass());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 22, 5, 11);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
	}

	public void testParseServiceBlocked() {
		String toParse = "[Service blocked: ICMP_echo_req] from source 178.250.209.42, Friday, Jan 27,2012 19:50:27";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(ServiceBlockedEvent.class, event.getClass());
		assertEquals("178.250.209.42", ((ServiceBlockedEvent) event)
				.getSource().getStringValue());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 19, 50, 27);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
		assertEquals("ICMP_echo_req", ((ServiceBlockedEvent) event)
				.getParameter().getName());
	}

	public void testParseTimeSynchro() {
		String toParse = "[Time synchronized with NTP server] Friday, Jan 27,2012 12:48:37";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(TimeSynchroEvent.class, event.getClass());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 12, 48, 37);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
	}

	public void testParseUPnP() {
		String toParse = "[UPnP set event: Public_UPNP_C3] from source 192.168.0.129, Friday, Jan 27,2012 12:48:21";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(UPnPEvent.class, event.getClass());
		assertEquals("192.168.0.129", ((UPnPEvent) event).getSource()
				.getStringValue());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 12, 48, 21);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
		assertEquals("Public_UPNP_C3", ((UPnPEvent) event).getParameter()
				.getName());
	}

	public void testParseEmail() {
		String toParse = "[email sent to: jpfroud@gmail.com] Friday, Jan 27,2012 13:01:01";
		List<EventType> listEvents = LogParser.parse(toParse);
		EventType event = listEvents.get(0);
		// checks
		assertEquals(EmailSentEvent.class, event.getClass());
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 0, 27, 13, 1, 1);
		assertEquals(cal.getTime().toString(), event.getDateOfEvent()
				.toString());
		assertEquals("jpfroud@gmail.com", ((EmailSentEvent) event)
				.getParameter().getName());
	}

	public void testLineToListConverter() {
		try {
			File input = new File(
					"src/test/resources/testLineToListConverter.txt");
			String string = IoUtils.fileToString(input);
			List<String> convertedLines = IoUtils.convert(string);
			assertEquals(4, convertedLines.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testNameEventGetter() {
		String eventName = EventFactory
				.getEventName("[UPnP set event: Public_UPNP_C3] from source 192.168.0.129, Friday, Jan 27,2012 12:48:21");
		assertEquals("UPnP set event", eventName);
		eventName = EventFactory
				.getEventName("[Remote login failure] from source 160.53.250.126, Friday, Jan 27,2012 12:49:53");
		assertEquals("Remote login failure", eventName);
	}
}
