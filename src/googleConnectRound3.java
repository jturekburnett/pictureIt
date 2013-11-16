import java.io.*;
import java.lang.Object;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

public class googleConnectRound3 {
	public static void main(String[] args) throws IOException{
		CalendarService myService = new CalendarService("exampleCo-exampleApp-1.0");

		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your username.");
		//String un = input.nextLine();
		System.out.println("Please enter your password");
		//String pwd = input.nextLine();

		String filename = "results.txt";

		/*File file = new File (filename);
		Scanner fileScanner = new Scanner(file);
		FileReader fr = new FileReader(filename);
		String event = " ";
		int k = 0;
		while(((k=fr.read())!=-1)){
			event+= (char)k;
		}
		fr.close();*/

		FileInputStream file = new FileInputStream(filename);
		byte [ ] b = new byte [file.available()];
		file.read(b);
		file.close();
		String event = new String(b);
		System.out.println(event);

		String[] dateandTime = getDate(event);
		String times = getTimes(event);

		try {
			myService.setUserCredentials("tesseracttekkies@gmail.com", "partyitup");
			URL postUrl = new URL ("https://www.google.com/calendar/feeds/tesseracttekkies@gmail.com/private/full");

			CalendarEventEntry myEntry = new CalendarEventEntry();

			myEntry.setTitle(new PlainTextConstruct("Tennis with Beth"));
			myEntry.setContent(new PlainTextConstruct("Meet for a quick lesson."));

			DateTime startTime = DateTime.parseDateTime("2013-11-17T15:00:00-08:00");
			DateTime endTime = DateTime.parseDateTime("2013-11-17T15:00:00-08:00");
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

	public static String[] getDate(String text){
		String[] months = new String[12]; months[0]= "Jan"; months[1]="Feb"; months[2]="Mar"; months[3]="Apr"; months[4]="May";
		months[5]="Jun"; months[6]="Jul"; months[7]="Aug"; months[8]="Sep"; months[9]="Oct"; months[10]="Nov"; months[11]="Dec";
		String[] monthNums = new String[12]; monthNums[0]= "01"; monthNums[1]="02"; monthNums[2]="03"; monthNums[3]="04"; monthNums[4]="05";
		monthNums[5]="06"; monthNums[6]="07"; monthNums[7]="08"; monthNums[8]="09"; monthNums[9]="10"; monthNums[10]="11"; monthNums[11]="12";


		int monthIndex = 0;
		int yearIndex = 0;
		String startDate = "";
		String endDate = "";
		String [] dates = new String[2]; dates[0] = startDate; dates[1] = endDate;

		if(text.indexOf(",")>0){
			System.out.println("I'm in the day's if statement!");
			text = text.replace(" ", "");
			System.out.println(text);
		}
		//identifies start year
		for(int i=2013; i<2015; i++){
			System.out.println(Integer.toString(i));
			if(text.indexOf(Integer.toString(i))>0){
				System.out.println("I am here!");
				startDate= Integer.toString(i);
				yearIndex = text.indexOf(Integer.toString(i));
				i = 2015;
			}
		}
		//identifies start month
		for(int i=0; i<months.length; i++){
			if(text.contains(months[i])||text.contains(months[i].toUpperCase())||text.contains(Integer.toString(i+1)+'/')){
				startDate+="-";
				startDate+=monthNums[i];
				monthIndex = i;
				System.out.println("monthIndex = " + monthIndex);
				i = months.length;
			}
		}

		//identifies start day

		String temp = text.substring(text.indexOf(',')+1);
		System.out.println("temp = " + temp);
		//later can check in the month section if this is a 29, 30, or 31 month and then make this 3 separate methods
		if(temp.indexOf(",2")>0){
			System.out.println("I am in ths if statement");
			System.out.println(temp.substring(temp.indexOf(",2")-2, temp.indexOf(",2")));
			for(int i=31; i>=1; i--){
				if(temp.substring(temp.indexOf(",")-3, temp.indexOf(",")).indexOf(Integer.toString(i))>0){
					startDate+=("-" + Integer.toString(i));
					i=0;
				}
			}
		}

		/*if(text.substring(0,yearIndex).indexOf(Integer.toString(i)) > 0){//checking the parts of the string before the year
				String temp = Integer.toString(i);
				if(temp.length() == 1){
					temp = "0" + temp;
					System.out.print("temp = " + temp);
				}
				startDate+=temp;
			}
			if(text.substring(yearIndex+4).indexOf(Integer.toString(i)) > 0){//checking the parts of the string after the year
				String temp = Integer.toString(i);
				if(temp.length() == 1){
					temp = "0" + temp;
				}
				startDate+=temp;
			}*/
//		int count =0;
//		for(int i=0; i<startDate.length(); i++){
//			if(startDate.charAt(i)=='-'){
//				count++;
//			}
//		}
//		if(count>2){
//			temp = startDate.substring(0,startDate.indexOf('-'));
//			temp +=startDate.substring(startDate.indexOf('-'));
//		}
		System.out.println(startDate);

		//identifies end date
		for(int i=monthIndex; i<months.length; i++){
			if(text.contains(months[i])||text.contains(months[i].toUpperCase())||text.contains(Integer.toString(i+1)+'/')){
				endDate.replace("01", monthNums[i]);
				i = months.length;
			}
			else{
				endDate = startDate;
			}

		}
		System.out.println(endDate);

		return dates;
	}

	public static String getTimes(String text){
		String startTime = "00:00";
		if(text.contains(":")){
			startTime.replace("00:00", text.substring(text.indexOf(':')-2, text.indexOf(':')+ 2));
		}
		System.out.println(startTime);
		//if(text.substring(text.indexOf(':')+3).contains("pm")||text.contains("p.m.")|| text.contains("p. m.")||text.contains(PM)|| text.contains(P.M.)|| text.contains(P. M.)){

		//}
		//}
		return startTime;
	}

}