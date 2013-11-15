import com.google.gdata.client.*;
import com.google.gdata.client.calendar.*;
import com.google.gdata.data.*;
import com.google.gdata.data.acl.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * 
 * @author Jessica
 * @sources Google Calendar API v2 Developer's Guide: Java  https://developers.google.com/google-apps/calendar/v2/developers_guide_java#CreatingEvents
 * 			"How to read file in Java using Scanner Example - text files" http://java67.blogspot.com/2012/11/how-to-read-file-in-java-using-scanner-example.html
 * 
 */

public class googleConnectRound2 {
	public static void main(String[] args) throws FileNotFoundException{
		CalendarService myService = new CalendarService("exampleCo-exampleApp-1.0");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your username.");
		String un = input.nextLine();
		System.out.println("Please enter your password");
		String pwd = input.nextLine();
		
		String filename = "results.txt";
		
		File file = new File (filename);
		Scanner fileScanner = new Scanner(file);
		String event = fileScanner.toString();
		
		
		
		
		try {
			myService.setUserCredentials(un, pwd);
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

