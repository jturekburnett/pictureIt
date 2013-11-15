import com.google.gdata.client.*;
//import com.google.gdata.client.GoogleAuthTokenFactory.UserToken;
import com.google.gdata.client.calendar.*;
import com.google.gdata.data.*;
import com.google.gdata.data.acl.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;

import java.net.*;
import java.io.*;

/**
 * @author Jessica Burnett
 * @sources Google Calendar API v2 Developer's Guide: Java https://developers.google.com/google-apps/calendar/v2/developers_guide_java
 * 
 * 
 *
 */

public class googleConnect {
	public static void main(String[] args){
		try {
			//create a CalendarService and authenticate
			CalendarService client = new CalendarService("Picture-to-Calendar");
			client.setUserCredentials("tesseracttekkies@gmail.com", "partyitup");

			//saving an auth token
			String token = "partyitup"; // TODO: Read user's token from your database
			client.setUserToken(token);

			//calling the auth token
			//UserToken auth_token = (UserToken) client.getAuthTokenFactory().getAuthToken();
			//token = auth_token.getValue(); // token is 'partyitup'

			//send and create the event
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
		
		client.insert(postUrl, myEntry);
		System.out.println("Event added");
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
