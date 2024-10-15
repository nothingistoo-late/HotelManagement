package hotelmanagement;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ntphu
 */
public class Hotel implements Serializable{

    private String hotel_id, hotel_Name, hotel_Address,hotel_Rating,
    hotel_Room_Available,hotel_Phone;

    public Hotel() {
    }

    public Hotel(String hotel_id, String hotel_Name,String hotel_Room_Available, String hotel_Address, String hotel_Phone,String hotel_Rating) {
        this.hotel_id = hotel_id.toUpperCase();
        this.hotel_Name = hotel_Name;
        this.hotel_Address = hotel_Address;
        this.hotel_Room_Available = hotel_Room_Available;
        this.hotel_Rating = hotel_Rating;
        this.hotel_Phone = hotel_Phone;
    }

    public String getHotel_id() {
        return hotel_id.toUpperCase();
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id.toUpperCase();
    }

    public String getHotel_Name() {
        return hotel_Name;
    }

    public void setHotel_Name(String hotel_Name) {
        this.hotel_Name = hotel_Name;
    }

    public String getHotel_Address() {
        return hotel_Address;
    }

    public void setHotel_Address(String hotel_Address) {
        this.hotel_Address = hotel_Address;
    }

    public String getHotel_Room_Available() {
        return hotel_Room_Available;
    }

    public void setHotel_Room_Available(String hotel_Room_Available) {
        this.hotel_Room_Available = hotel_Room_Available;
    }

    public String getHotel_Rating() {
        return hotel_Rating;
    }

    public void setHotel_Rating(String hotel_Rating) {
        this.hotel_Rating = hotel_Rating;
    }

    public String getHotel_Phone() {
        return hotel_Phone;
    }

    public void setHotel_Phone(String hotel_Phone) {
        this.hotel_Phone = hotel_Phone;
    }

    @Override
    public String toString() {
        return String.format("|%5s|%20s|%5s|%20s|%15s|%6s|", this.hotel_id,this.hotel_Name,this.hotel_Room_Available,this.hotel_Address,this.hotel_Phone, this.hotel_Rating);
    }

 
}
