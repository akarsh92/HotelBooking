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

			// read Hotels file
			br = new BufferedReader(new FileReader(args[0]));
			// read bookings file
			br1 = new BufferedReader(new FileReader(args[1]));

			ArrayList<Booking> hotelAvailable = new ArrayList<Booking>();

			Map<String, Integer> hotelList = new HashMap<String, Integer>();
			Map<String, Integer> available = new HashMap<String, Integer>();
			Map<String, Map<String, Integer>> listofhotels = new HashMap<String, Map<String, Integer>>();

			DateFormat formatter;
			List<Date> dates = new ArrayList<Date>();
			List<Date> cdates = new ArrayList<Date>();
			formatter = new SimpleDateFormat("yy-MM-dd");

			// Parse Hotels.csv file
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] hotels = line.split(cvsSplitBy);
				hotelList.put(hotels[0], Integer.parseInt(hotels[1]));

			}

			// Parse bookings.file
			while ((line2 = br1.readLine()) != null) {

				// use comma as separator
				String[] bookings = line2.split(cvsSplitBy);

				// Provide list of all dates between date range

				dates = getDates(bookings[1], bookings[2]);

				String bookinghotelName = bookings[0];
				for (int i = 0; i < dates.size(); i++) {
					Date lDate = (Date) dates.get(i);
					String ds = formatter.format(lDate);
					hotelAvailable.add(new Booking(bookinghotelName, ds));
				}

			}

			// Calculate Number of rooms available with specific date

			Map<String, Integer> innerMap = new HashMap<String, Integer>();
			for (Booking list : hotelAvailable) {
				if (listofhotels.containsKey(list.getHotelName())) {

					if (innerMap.containsKey(list.getDates())) {
						innerMap.put(list.getDates(), innerMap.get(list.getDates()) + 1);
						listofhotels.put(list.getHotelName(), innerMap);
					} else {
						innerMap.put(list.getDates(), 1);
						listofhotels.put(list.getHotelName(), innerMap);

					}

				} else {
					innerMap = new HashMap<String, Integer>();
					innerMap.put(list.getDates(), 1);
					listofhotels.put(list.getHotelName(), innerMap);
				}

			}

			String checkIn = args[2];
			String checkOut = args[3];

			cdates = getDates(checkIn, checkOut);

			// Output the hotels with rooms booked more than or equal to
			// available rooms
			for (Map.Entry<String, Map<String, Integer>> entry : listofhotels.entrySet()) {

				for (Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()) {
					for (int i = 0; i < cdates.size(); i++) {
						Date lDate = (Date) cdates.get(i);
						String ds = formatter.format(lDate);
						if (ds.equals(entry2.getKey()) && entry2.getValue() > hotelList.get(entry.getKey())) {
							available.put(entry.getKey(), 1);
						}

					}

				}

			}

			// Output all available rooms for a specific date range
			for (Map.Entry<String, Integer> entry11 : hotelList.entrySet()) {
				if (!available.containsKey(entry11.getKey()))
					System.out.println(entry11.getKey());
			}

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

	// method to provide range of dates from start and end date
	public static List<Date> getDates(String sdate, String edate) {
		DateFormat formatter;
		List<Date> dates = new ArrayList<Date>();

		formatter = new SimpleDateFormat("yy-MM-dd");
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = (Date) formatter.parse(sdate);

			endDate = (Date) formatter.parse(edate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long interval = 24 * 1000 * 60 * 60;
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();

		while (startTime <= endTime) {
			dates.add(new Date(startTime));
			startTime += interval;
			// return dates;
		}
		return dates;
	}
}
