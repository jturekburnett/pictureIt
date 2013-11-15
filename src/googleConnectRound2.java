import java.io.IOException;
import java.net.URL;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;

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

