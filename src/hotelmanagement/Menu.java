/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import tool.Inputter;
import java.util.ArrayList;

/**
 *
 * @author ntphu
 */
public class Menu {
    public static void menu(){
        ArrayList<Hotel> dsHotel = new ArrayList<>();
        String file = "hotel.dat";
        int choice=0;
        Boolean kt=false;
        String select="";
        do {            
            Hotels.showMenu();
            choice=Inputter.getAnInteger("Enter Choice := ", "Choice must be 1=>choice<=9", 0, 9);
            switch (choice){
                case 1:
                    dsHotel=Hotels.loadData(file);
                    System.out.println("Data Had Been Loaded....");
                    break;
                case 2:
                    Hotel x = new Hotel();
                    do {                        
                    x = Hotels.addHotel();
                    if (Hotels.checkID(x.getHotel_id(),dsHotel))
                        System.out.println("Add fail. Existed ID!!");
                    else{
                        dsHotel.add(x);
                        System.out.println("Add success!!");
                        kt=false;}
                    select = Inputter.getString("Do You Want to continue???"
                                              + " [1:Yes/Others:No] := ", "Error");
                    }while (select.equalsIgnoreCase("1"));
                    break;
                case 3:
                    do {
                    String iD = Inputter.getString("Enter Hotel ID You Want to Check := ", "Hotel ID cant blank");
                    if (Hotels.checkID(iD,dsHotel))
                        System.out.println("Exist Hotel");
                    else
                        System.out.println("No Hotel Found!");
                    select = Inputter.getString("Do You Want to continue???"
                                              + " [1:Yes/Others:No] := ", "Error");
                    }while (select.equalsIgnoreCase("1"));
                    break;
                case 4:
                     String iD = Inputter.getString("Enter Hotel ID You Want to Update := ", "Hotel ID cant blank");
                    if (Hotels.checkID(iD,dsHotel))
                        Hotels.updateHotel(iD,dsHotel);
                    else
                        System.out.println("No Hotel Found!");                        
                    break;
                case 5:
                    iD = Inputter.getString("Enter Hotel ID You Want to Delete := ", "Hotel ID cant blank");
                    select = Inputter.getString("Do You Really want to delete this hotel ??? \n"
                                            + "[1:Yes/Others:No]:=","Error");
                    if (select.equalsIgnoreCase("1")){
                    if (Hotels.checkID(iD, dsHotel)){
                        Hotel a = Hotels.deleteHotel(iD,dsHotel);
                        dsHotel.remove(a);
                        kt=false;
                        System.out.println("Deleted!!!!");
                    }
                    else
                        System.out.println("Delete Fail!!!\n"
                                         + "Unavailable Hotel!!");}
                    else
                        System.out.println("You Canceled!!!");
                    break;
                case 6:
                    select = Inputter.getString("6.1 Searching by Hotel_id.\n" +
                                                "6.2 Searching by Hotel_Address.\n"
                                              + "Enter Your Choice := ", "Inputer Must Be 6.1 or 6.2","^6\\.[12]$");
                    if (select.equalsIgnoreCase("6.1"))
                    {
                        String search = Inputter.getString("Enter ID Hotel You Want to Search := ", "ID Hotels Cant Be Emty!!");
                        Hotels.searchByID(search,dsHotel);
                    }else{
                        String search = Inputter.getString("Enter Hotel Address You Want to Search := ", "Hotel Address cant be emty!!");
                        Hotels.searchByAddress(search,dsHotel);
                    }
                    break;
                case 7:
                    Hotels.showAll(dsHotel);
                    break;
                case 8:
                    Hotels.readData(file, dsHotel);
                    dsHotel=Hotels.loadData(file);
                    System.out.println("Data Had Been Saved...");
                    kt=true;
                    break;
                case 9:
                    if (kt==false){
                        select=Inputter.getString("Do You Want save Data Before Exit???\n"
                                + "[1:Yes/Others:No]:= ", "Cant be Emty!!");
                        if (select.equalsIgnoreCase("1"))
                        if (dsHotel.isEmpty())
                        {
                          select=Inputter.getString("The List Is Emty\n"
                                  + "Do You Really Save Emty List???\n"
                                + "[1:Yes/Others:No]:= ", "Cant be Emty!!"); 
                          if (select.equalsIgnoreCase("1")){
                            Hotels.readData(file, dsHotel);
                            dsHotel=Hotels.loadData(file);
                            System.out.println("Saved...");
                          }
                        }else{
                        Hotels.readData(file, dsHotel);
                        dsHotel=Hotels.loadData(file);
                            System.out.println("Saved...");
                        }
                    }
                    break;
                default: System.out.println("Erorr...");
            }
        } while (choice!=9);
    }
    }

