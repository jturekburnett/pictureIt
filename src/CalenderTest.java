import java.io.IOException;
import java.net.URL;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.util.ServiceException;

public class CalenderTest {
	public static void main(String[] args){
		CalendarService myService = new CalendarService("exampleCo-exampleApp-1.0");
		try {
			myService.setUserCredentials("tesseracttekkies@gmail.com", "partyitup");

		
		URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
		CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
		
		
		System.out.println("Your calendars: ");
		System.out.println();
		
		for(int i = 0; i< resultFeed.getEntries().size(); i++) {
			CalendarEntry entry = resultFeed.getEntries().get(i);
			System.out.println("\t" + entry.getTitle().getPlainText());
		}
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
