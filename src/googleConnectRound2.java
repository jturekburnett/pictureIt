import com.google.gdata.client.*;
import com.google.gdata.client.calendar.*;
import com.google.gdata.data.*;
import com.google.gdata.data.acl.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;

import java.net.*;
import java.io.*;

public class googleConnectRound2 {
	public static void main(String[] args){
		CalendarService myService = new CalendarService("exampleCo-exampleApp-1.0");
		try {
			myService.setUserCredentials("tesseracttekkies@gmail.com", "partyitup");
			URL postUrl = new URL ("https://www.google.com/calendar/feeds/tesseracttekkies@gmail.com/private/full");

			CalendarEventEntry myEntry = new CalendarEventEntry();

			myEntry.setTitle(new PlainTextConstruct("Tennis with Beth"));
			myEntry.setContent(new PlainTextConstruct("Meet for a quick lesson."));

			DateTime startTime = DateTime.parseDateTime("2013-11-13T15:00:00-08:00");
			DateTime endTime = DateTime.parseDateTime("2013-11-17T17:00:00-08:00");
			When eventTimes = new When();
			eventTimes.setStartTime(startTime);
			eventTimes.setEndTime(endTime);
			myEntry.addTime(eventTimes);

			myService.insert(postUrl, myEntry);
			System.out.println("Event added");
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

