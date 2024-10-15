/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import tool.Inputter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Holder;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Comparator;
import tool.Tool;


/**
 *
 * @author ntphu
 */
class Hotels {
    public static void showMenu() {
        System.out.println("---------------------------Hotel Management----------------------------------");
        System.out.println("-----------------------------------------------------------------------------");
         System.out.println("1) Load data from file to program\n" +
                            "2) Adding new Hotel.\n" +
                            "3) Checking exits Hotel.\n" +
                            "4) Updating Hotel information.\n" +
                            "5) Deleting Hotel.\n" +
                            "6) Searching Hotel.\n" +
                            "7) Displaying a hotel list (descending by Hotel_Name).\n" +
                            "8) Save data to file\n" +
                            "9) Others Quit.");
        System.out.println("-----------------------------------------------------------------------------");

    }

    public static Hotel addHotel() {
        Hotel a = new Hotel();
        a.setHotel_id(Inputter.getString("Enter Hotel ID := ", "Hotel ID Wrong !!"));
        a.setHotel_Name(Inputter.getString("Enter Hotel Name := ", "Hotel Name cant be emty!! "));
        a.setHotel_Room_Available(Inputter.getString("Enter Room Available := ", "Erorr Room!! ", "\\d+"));
        a.setHotel_Address(Inputter.getString("Enter Hotel Address := ", "Address cant be emty!!"));
        a.setHotel_Phone(Inputter.getString("Enter Hotel Phone := ", "Hotel Phone error", "^(\\+84|0)(3[2-9]|5[689]|7[06-9]|8[1-9]|9\\d)\\d{7}$"));
        a.setHotel_Rating(Inputter.getString("Enter Hotel Rating := ", "Hotel Rating must start with number", "^[0-9].*"));
        return a;
    }

    

    static boolean checkID(String hotel_id, ArrayList<Hotel> dsHotel) {
        boolean check=false;
        for (Hotel x: dsHotel)
            if (x.getHotel_id().equalsIgnoreCase(hotel_id))
                return true;
        return check;
    }
    
    public static ArrayList<Hotel> loadData(String fileName) {
        ArrayList<Hotel> x = new ArrayList<>();
        File f = new File(fileName);
        try (FileInputStream fis = new FileInputStream(f); 
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available()>0){
                Hotel y = (Hotel)ois.readObject();
                x.add(y);
            }
        } catch (FileNotFoundException ex) {
           // Logger.getLogger(Hotels.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Khong Ton Tai File!!");
        } catch (IOException ex) {
            Logger.getLogger(Hotels.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hotels.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    

    static void readData(String file, ArrayList<Hotel> dsHotelFile) {
        FileOutputStream fos= null;
        try {
            File f= new File(file);
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Hotel x: dsHotelFile)
                oos.writeObject(x);
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hotels.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Hotels.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Hotels.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    static void updateHotel(String iD, ArrayList<Hotel> dsHotel) {
        Hotel a=null;
        for (Hotel x : dsHotel)
            if (x.getHotel_id().equalsIgnoreCase(iD)){
                a=x;
                break;}
        String st="";
        st=Tool.inputString("Enter Hotel Name:= ");
        if (!st.isEmpty() && !st.trim().isEmpty()){
             a.setHotel_Name(st);
        }
        st=Tool.inputString("Enter Hotel Room Available := ");
        if (!st.isEmpty()&& !st.trim().isEmpty())
        {
            a.setHotel_Room_Available(st);
        }
        st=Tool.inputString("Enter Hotel Address := ");
        if (!st.isEmpty()&& !st.trim().isEmpty()){
            a.setHotel_Address(st);
        }
        st=Tool.inputString("Enter Hotel Phone := ");
        if (!st.isEmpty()&& !st.trim().isEmpty()){
            a.setHotel_Phone(st);
        }
        st=Tool.inputString("Enter Hotel Rating := ");
            if (!st.isEmpty()&& !st.trim().isEmpty()){
                a.setHotel_Rating(st);
    }
            System.out.println("Updated!!!!");
    }

    static Hotel deleteHotel(String iD, ArrayList<Hotel> dsHotel) {
        Hotel a=null;
        for (Hotel x: dsHotel)
            if (x.getHotel_id().equalsIgnoreCase(iD)){
                a=x;
                break;
            }
        return a;
    }

    static void showAll(ArrayList<Hotel> dsHotelFile) {
        ArrayList<Hotel> a = dsHotelFile;
        Comparator<Hotel> hau = new Comparator<Hotel>(){
            @Override
            public int compare(Hotel o1, Hotel o2) {
                 return o1.getHotel_Name().compareToIgnoreCase(o2.getHotel_Name());
            }
        };
        Collections.sort(a, hau);
        if (!a.isEmpty()){
        System.out.println("---------------------------Hotel Information---------------------------------");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("| ID  |      Hotel Name    |Room |   Hotel Address    |  Hotel Phone  |Rating|");
        for (Hotel x: a)
            System.out.println(x);
        System.out.println("-----------------------------------------------------------------------------");
    }else
            System.out.println("No Hotel In List!!!!");
    }

    static void searchByID(String search, ArrayList<Hotel> dsHotelFile) {
        ArrayList<Hotel> ds = new ArrayList<>();
        for (Hotel x: dsHotelFile)
            if (x.getHotel_id().toLowerCase().equalsIgnoreCase(search.toLowerCase()))
                ds.add(x);
        if (!ds.isEmpty()){
        System.out.println("---------------------------Hotel Information---------------------------------");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("| ID  |      Hotel Name    |Room |   Hotel Address    |  Hotel Phone  |Rating|");
        for (Hotel x: ds)
                System.out.println(x);
        System.out.println("-----------------------------------------------------------------------------");
        }else
            System.out.println("No Hotel Found!!!");
            }

    static void searchByName(String search, ArrayList<Hotel> dsHotelFile) {
        ArrayList<Hotel> ds = new ArrayList<>();
        for (Hotel x: dsHotelFile)
            if (x.getHotel_Name().equalsIgnoreCase(search))
                ds.add(x);
        if (!ds.isEmpty()){
        System.out.println("---------------------------Hotel Information---------------------------------");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("| ID  |      Hotel Name    |Room |   Hotel Address    |  Hotel Phone  |Rating|");
        for (Hotel x: ds)
                System.out.println(x);
        System.out.println("-----------------------------------------------------------------------------");
        }else
            System.out.println("No Hotel Found!!!");
    }

    static void searchByAddress(String search, ArrayList<Hotel> dsHotelFile) {
        ArrayList<Hotel> a = new ArrayList<>();
        for (Hotel x: dsHotelFile)
            if (x.getHotel_Address().toLowerCase().contains(search.toLowerCase()))
                a.add(x);
        Comparator<Hotel> hau = new Comparator<Hotel>(){
            @Override
            public int compare(Hotel o1, Hotel o2) {
                 int intValue1 = Integer.parseInt(o1.getHotel_Room_Available());
                 int intValue2 = Integer.parseInt(o2.getHotel_Room_Available());
                 if (intValue1<intValue2)
                     return 1;
                 else   if(intValue1>intValue2)
                        return -1;
                 else
                     return 0;
            }
        };
        Collections.sort(a, hau);
        System.out.println("---------------------------Hotel Information---------------------------------");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("| ID  |      Hotel Name    |Room |   Hotel Address    |  Hotel Phone  |Rating|");
        for (Hotel x: a)
            System.out.println(x);
        System.out.println("-----------------------------------------------------------------------------");

    }

public static void addFake(ArrayList<Hotel> dsHotel) {
        Hotel x = new Hotel("H01", "Seraton", "10", "Binh Thanh", "0911796099", "4 star");
        dsHotel.add(x);
        x = new Hotel("H02", "Vinstar", "5", "VinHome", "0918940111", "5 star");        dsHotel.add(x);

        x = new Hotel("H03", "OutString", "7", "NVT", "0988940222","6 star");        dsHotel.add(x);

        x = new Hotel("H04", "Betigar", "8", "Go Vap", "09778940100", "3 star");        dsHotel.add(x);

        x = new Hotel("H05", "Trung", "12", "Vinhome", "0339381305", "5 star");        dsHotel.add(x);

        x = new Hotel("H06", "Midgar", "29", "Isekai", "0977894010", "9 star");        dsHotel.add(x);
        x = new Hotel("H07", "Neuvilette", "23", "Fontain", "092382132", "6 Star"); dsHotel.add(x);
        x = new Hotel("H08", "Bennet", "1", "Mondstat", "097878787", "1 Star");dsHotel.add(x);
    }



}


