<<<<<<< HEAD
import java.io.*;
import java.lang.Object;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
=======
import java.io.File;
import java.io.FileNotFoundException;
>>>>>>> 24a7b5648a0f085ee0859497b37fc47c7425e98f
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;

/**
 * 
 * @author Jessica
 * @sources Google Calendar API v2 Developer's Guide: Java  https://developers.google.com/google-apps/calendar/v2/developers_guide_java#CreatingEvents
 * 			"How to read file in Java using Scanner Example - text files" http://java67.blogspot.com/2012/11/how-to-read-file-in-java-using-scanner-example.html
 * 			http://java-demos.blogspot.com/2012/10/java-filereader.html
 * 
 */

public class googleConnectRound2 {
	public static void main(String[] args) throws IOException{
		CalendarService myService = new CalendarService("exampleCo-exampleApp-1.0");

		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your username.");
		String un = input.nextLine();
		System.out.println("Please enter your password");
		String pwd = input.nextLine();

		String filename = "results.txt";
		FileInputStream file = new FileInputStream(filename);
		byte [ ] b = new byte [file.available()];
		file.read(b);
		file.close();
		String event = new String(b);
		System.out.println(event);

		String eventAndBothDates = getDate(event);
		String[] details = eventAndBothDates.split("\\+");
		//for (int i=1; i<details.length; i++){
			//System.out.println("date: " + details[i]);
		//}
		String startTimes = getTime(event);
		String endTimes;
		int count = 0;
		for(int i=0; i<event.length(); i++){
			count++;
		}
		if(count == 2){
			endTimes = getTime(event.substring(event.indexOf(':')+1));
		}
		else{
			endTimes = startTimes;
		}
		System.out.println("combo = " + assemble(details[1], startTimes));
		String start = assemble(details[1], startTimes);
		String end = assemble(details[2], startTimes);

		try {
			myService.setUserCredentials(un, pwd);
			URL postUrl = new URL ("https://www.google.com/calendar/feeds/tesseracttekkies@gmail.com/private/full");

			CalendarEventEntry myEntry = new CalendarEventEntry();

			myEntry.setTitle(new PlainTextConstruct(details[0]));
			myEntry.setContent(new PlainTextConstruct(details[3]));

			DateTime startTime = DateTime.parseDateTime(start);
			DateTime endTime = DateTime.parseDateTime(end);
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

	public static String getDate(String text){
		String[] months = new String[12]; months[0]= "Jan"; months[1]="Feb"; months[2]="Mar"; months[3]="Apr"; months[4]="May";
		months[5]="Jun"; months[6]="Jul"; months[7]="Aug"; months[8]="Sep"; months[9]="Oct"; months[10]="Nov"; months[11]="Dec";
		String[] monthNums = new String[12]; monthNums[0]= "01"; monthNums[1]="02"; monthNums[2]="03"; monthNums[3]="04"; monthNums[4]="05";
		monthNums[5]="06"; monthNums[6]="07"; monthNums[7]="08"; monthNums[8]="09"; monthNums[9]="10"; monthNums[10]="11"; monthNums[11]="12";


		int monthIndex = 0;
		int yearIndex = 0;
		String startDate = "";
		String eventTitle = "";
		String details = " ";
		//String [] dates = new String[2]; dates[0] = startDate; dates[1] = endDate;

		if(text.indexOf(",")>0){
			text = text.replace(" ", "");
			//System.out.println(text);
		}
		//identifies start year
		for(int i=2013; i<2015; i++){
			//System.out.println(Integer.toString(i));
			if(text.indexOf(Integer.toString(i))>0){
				startDate= Integer.toString(i);
				yearIndex = text.indexOf(Integer.toString(i));
				i = 2015;
			}
		}
		if (startDate == ""){
			startDate = "2013";
		}
		//identifies start month
		for(int i=0; i<months.length; i++){
			if(text.contains(months[i])||text.contains(months[i].toUpperCase())||text.contains(Integer.toString(i+1)+'/')){
				startDate+="-";
				startDate+=monthNums[i];
				monthIndex = text.indexOf(months[i]);
				//System.out.println("monthIndex = " + monthIndex);
				i = months.length;
			}
		}
		if (startDate == "2013"){
			startDate = "2013-01-01";
		}
		//System.out.println("monthIndex = " + monthIndex);
		eventTitle = text.substring(0, monthIndex);
		String preMonth = text.substring(monthIndex-3,monthIndex);
		//System.out.println("preMonth = " + preMonth);
		String midMonthYear = text.substring(monthIndex, yearIndex);
		//System.out.println("midMonthYear = " + midMonthYear);
		for(int i=31; i>=1; i--){
			if(preMonth.indexOf(Integer.toString(i))>0){
				//System.out.println("Here I am");
				
				if(Integer.toString(i).length() == 1){
					String lengthen = "0"+Integer.toString(i);
					startDate +=("-" + lengthen);
					i=0;
				}
				else{
					startDate += ("-" + Integer.toString(i));
					i=0;
				}
			}
			if(midMonthYear.indexOf(Integer.toString(i))>0){
				//System.out.println("Here I am");
				if(Integer.toString(i).length() == 1){
					String lengthen = "0"+Integer.toString(i);
					startDate +=("-" + lengthen);
					i=0;
				}
				else{
					startDate += ("-" + Integer.toString(i));
					i=0;
				}
			}
		}



		System.out.println(startDate);
		System.out.println("Now collecting the end date");
		text = text.substring(yearIndex+4);
		//System.out.println(text);

		monthIndex = 0;
		String endDate = "";
		//String [] dates = new String[2]; dates[0] = startDate; dates[1] = endDate;

		//identifies end year
		for(int i=2013; i<2015; i++){
			//System.out.println(Integer.toString(i));
			if(text.indexOf(Integer.toString(i))>0){
				//System.out.println("I am here!");
				endDate= Integer.toString(i);
				yearIndex = text.indexOf(Integer.toString(i));
				i = 2015;
			}
		}
		//System.out.println("The end date is: " + endDate);
		if (endDate == ""){
			endDate = "2013";
		}
		//identifies end month
		for(int i=0; i<months.length; i++){
			if(text.contains(months[i])||text.contains(months[i].toUpperCase())||text.contains(Integer.toString(i+1)+'/')){
				endDate+="-";
				endDate+=monthNums[i];
				monthIndex = text.indexOf(months[i]);
				//System.out.println("monthIndex = " + monthIndex);
				i = months.length;
			}
		}
		if (endDate == "2013"){
			endDate = startDate;
			String bothDates = eventTitle + "+" + startDate + "+" + endDate+ "+" + details;
			return bothDates;
			
		}
		//System.out.println("monthIndex = " + monthIndex);
		
		preMonth = text.substring(monthIndex-3,monthIndex);
		//System.out.println("preMonth = " + preMonth);
		midMonthYear = text.substring(monthIndex, yearIndex);
		//System.out.println("midMonthYear = " + midMonthYear);
		details = text.substring(yearIndex+4);
		for(int i=31; i>=1; i--){
			if(preMonth.indexOf(Integer.toString(i))>0){
				//System.out.println("Here I am");
				if(Integer.toString(i).length() == 1){
					String lengthen = "0"+Integer.toString(i);
					endDate +=("-" + lengthen);
					i=0;
				}
				else{
					endDate += ("-" + Integer.toString(i));
					i=0;
				}
			}
			if(midMonthYear.indexOf(Integer.toString(i))>0){
				//System.out.println("Here I am");
				if(Integer.toString(i).length() == 1){
					String lengthen = "0"+Integer.toString(i);
					endDate +=("-" + lengthen);
					i=0;
				}
				else{
					endDate += ("-" + Integer.toString(i));
					i=0;
				}
				i=1;
			}
		}
		System.out.println(endDate);
		String bothDates = eventTitle + "+" + startDate + "+" + endDate+ "+" + details;

		return bothDates;
	}

	public static String getTime(String text){
		String startTime = "";
		if(text.indexOf(":")>0){
			String hour = text.substring((text.indexOf(':')-2), text.indexOf(':'));
			String min = text.substring(text.indexOf(':')+1, text.indexOf(':')+3);
			//if statements cleaning up hour
			if((Integer.valueOf(hour) > 24)||(text.indexOf("pm")>0 && Integer.valueOf(hour)>12)){
				hour = hour.substring(1);
			}
			if(Integer.valueOf(hour)>12 && Integer.valueOf(hour)<=24){
				int x = Integer.valueOf(hour)-12;
				hour = Integer.toString(x);
			}
			else if((Integer.valueOf(hour) < 12)&&(text.indexOf("pm")>0)){
				int x = Integer.valueOf(hour)+12;
				hour = Integer.toString(x);
			}
			if(Integer.valueOf(min) > 60){
				min = "00";
			}
			
			startTime= hour + ":" + min;
		}
		System.out.println(startTime);
		//if(text.substring(text.indexOf(':')+3).contains("pm")||text.contains("p.m.")|| text.contains("p. m.")||text.contains(PM)|| text.contains(P.M.)|| text.contains(P. M.)){

		//}
		//}
		return startTime;
	}

	public static String assemble(String date, String time){
		String combo = (date + "T" + time + ":00-07:00");
		return combo;
	}
}
