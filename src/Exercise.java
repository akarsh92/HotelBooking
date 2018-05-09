import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String hotelFile = "/Users/varadaa/Desktop/Hotel.csv";
		String bookingFile = "/Users/varadaa/Desktop/Bookings.csv";
		BufferedReader br = null;
		BufferedReader br1 = null;
		String line = "";
		String line2 = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(hotelFile));
			br1 = new BufferedReader(new FileReader(bookingFile));

			ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
			ArrayList<Booking> hotelAvailable = new ArrayList<Booking>();
			
			Map<String, Integer> available = new HashMap<String, Integer>();

			// Parse Hotels.csv file
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] hotels = line.split(cvsSplitBy);
				hotelList.add(new Hotel(hotels[0], Integer.parseInt(hotels[1])));

			}
			
			for (Hotel list : hotelList) {
				System.out.println(list.getHotel()+" "+list.getRoomsAvailable());
			}

			// Parse bookings.file
			while ((line2 = br1.readLine()) != null) {

				// use comma as separator
				String[] bookings = line2.split(cvsSplitBy);

				// Provide list of all dates between date range

				DateFormat formatter;
				List<Date> dates = new ArrayList<Date>();

				formatter = new SimpleDateFormat("yy-MM-dd");
				Date startDate = null;
				Date endDate = null;

				startDate = (Date) formatter.parse(bookings[1]);
				endDate = (Date) formatter.parse(bookings[2]);

				long interval = 24 * 1000 * 60 * 60;
				long startTime = startDate.getTime();
				long endTime = endDate.getTime();

				while (startTime <= endTime) {
					dates.add(new Date(startTime));
					startTime += interval;
				}
				// end date range function

				String bookinghotelName = bookings[0];
				for (int i = 0; i < dates.size(); i++) {
					Date lDate = (Date) dates.get(i);
					String ds = formatter.format(lDate);
					hotelAvailable.add(new Booking(bookinghotelName, ds));

				}

			}

			System.out.println("----------");
				for (Booking list : hotelAvailable) {
				String listofHotelsAvailable = list.getHotelName()+"|"+list.getDates();
					if(available.containsKey(listofHotelsAvailable))
					{
					available.put(listofHotelsAvailable, available.get(listofHotelsAvailable)+1);
					}
					else
					{
						available.put(listofHotelsAvailable, 1);
					}
				}
				
				
				 for (Map.Entry<String, Integer> entry : available.entrySet()) {
					 
					System.out.println( entry.getKey()+" "+entry.getValue());
				 }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
