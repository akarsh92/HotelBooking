
public class Booking {
 private String hotelName;
 private String dates;
public String getHotelName() {
	return hotelName;
}
public void setHotelName(String hotelName) {
	this.hotelName = hotelName;
}
public String getDates() {
	return dates;
}
public void setDates(String dates) {
	this.dates = dates;
}

public Booking(String hotel, String listofdates)
{
	hotelName=	hotel;
	dates =listofdates;
}
}
