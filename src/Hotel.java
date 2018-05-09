
public class Hotel {
	
private String hotel;
private int roomsAvailable;
public String getHotel() {
	return hotel;
}
public void setHotel(String hotel) {
	this.hotel = hotel;
}
public int getRoomsAvailable() {
	return roomsAvailable;
}
public void setRoomsAvailable(int roomsAvailable) {
	this.roomsAvailable = roomsAvailable;
}

public Hotel (String hotelname, int rooms)
{
	hotel=hotelname;
	roomsAvailable=rooms;
}
}
