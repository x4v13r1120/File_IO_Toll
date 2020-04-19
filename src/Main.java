import javax.imageio.IIOException;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        final DecimalFormat df2 = new DecimalFormat("#.##");

        Scanner keyboard = new Scanner(System.in);

        switch (args[0]) {
            case "config":
                try {

                    ObjectOutputStream oStreamConfig = new ObjectOutputStream(new FileOutputStream(new File("Config.txt")));

                    List<String> car_Types = new ArrayList<>(List.of("1.Gas", "2.hybrid", "3.EV"));
                    System.out.println("The acceptable car types are" + car_Types);

                    System.out.println("Please write your number of booths.");
                    double number_Of_Booths = keyboard.nextDouble();

                    List<String> payment_methods = new ArrayList<>(Arrays.asList("1.cash", "2.card", "3.ES"));
                    System.out.println("The acceptable payment methods are" + payment_methods);

                    System.out.println("What is the card fee?");
                    double card_fee = keyboard.nextDouble();

                    System.out.println("What is the ES discount");
                    double es_discount = keyboard.nextDouble();

                    System.out.println("Please write your base rate.");
                    double base_rate = keyboard.nextDouble();

                    System.out.println("Please write your discount for electric cars.");
                    double EV_discount = keyboard.nextDouble();

                    System.out.println("Please write your discount for hybrid cars.");
                    double hybrid_Discount = keyboard.nextDouble();

                    System.out.println("You have successful configured the toll station.");

                    oStreamConfig.writeObject(car_Types);
                    oStreamConfig.writeDouble(number_Of_Booths);
                    oStreamConfig.writeObject(payment_methods);
                    oStreamConfig.writeDouble(card_fee);
                    oStreamConfig.writeDouble(es_discount);
                    oStreamConfig.writeDouble(base_rate);
                    oStreamConfig.writeDouble(EV_discount);
                    oStreamConfig.writeDouble(hybrid_Discount);

                    oStreamConfig.close();

                } catch (EOFException e) {
                    System.out.println("You have successful configured the toll station." + e.getLocalizedMessage());
                } catch (IOException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                break;
            case "report":
                    try {
                        ObjectInputStream iStreamVehicle = new ObjectInputStream(new FileInputStream(new File("vehicle.txt")));
                        Object car_types = iStreamVehicle.readObject();
                        double number_Of_Booth = iStreamVehicle.readDouble();
                        Object payment_types = iStreamVehicle.readObject();
                        double card_fees = iStreamVehicle.readDouble();
                        double es_discount = iStreamVehicle.readDouble();
                        double base_rate = iStreamVehicle.readDouble();
                        double EV_discount = iStreamVehicle.readDouble();
                        double hybrid_discount = iStreamVehicle.readDouble();

                        double totalStationMade = iStreamVehicle.readDouble();
                        double money_Made_at_Booth = iStreamVehicle.readDouble();
                        double totalCardMade = iStreamVehicle.readDouble();
                        double totalCashMade = iStreamVehicle.readDouble();
                        double totalEsMade = iStreamVehicle.readDouble();

                        double payment_Type = iStreamVehicle.readDouble();
                        double car_Type = iStreamVehicle.readDouble();
                        double number_Of_Axel = iStreamVehicle.readDouble();

                        double price_Car_pay = iStreamVehicle.readDouble();

                        iStreamVehicle.close();
                        try {
                            ObjectInputStream iStreamPaid = new ObjectInputStream(new FileInputStream(new File("paid.txt")));

                            Object prices_paid = iStreamPaid.readObject();
                            Object  users_car_Types= iStreamPaid.readObject();
                            Object  users_Payment_Types= iStreamPaid.readObject();
                            Object  users_number_of_axels= iStreamPaid.readObject();

                            iStreamPaid.close();
                            try {

                                ObjectOutputStream oStreamReport = new ObjectOutputStream(new FileOutputStream(new File("Report.txt")));
                                // where the final report will be written in
                                oStreamReport.writeObject(car_types);
                                oStreamReport.writeDouble(number_Of_Booth);
                                oStreamReport.writeObject(payment_types);
                                oStreamReport.writeDouble(card_fees);
                                oStreamReport.writeDouble(es_discount);
                                oStreamReport.writeDouble(base_rate);
                                oStreamReport.writeDouble(EV_discount);
                                oStreamReport.writeDouble(hybrid_discount);

                                oStreamReport.writeDouble(money_Made_at_Booth);
                                oStreamReport.writeDouble(totalCardMade);
                                oStreamReport.writeDouble(totalCashMade);
                                oStreamReport.writeDouble(totalEsMade);
                                oStreamReport.writeDouble(totalStationMade);

                                oStreamReport.writeDouble(payment_Type);
                                oStreamReport.writeDouble(car_Type);
                                oStreamReport.writeDouble(number_Of_Axel);

                                oStreamReport.writeDouble(price_Car_pay);

                                oStreamReport.writeObject(prices_paid);
                                oStreamReport.writeObject(users_car_Types);
                                oStreamReport.writeObject(users_Payment_Types);
                                oStreamReport.writeObject(users_number_of_axels);

                                oStreamReport.close();

                                try {
                                    ObjectInputStream iStreamReport = new ObjectInputStream(new FileInputStream(new File("Report.txt")));

                                    iStreamReport.readObject();
                                    iStreamReport.readDouble();
                                    iStreamReport.readObject();
                                    iStreamReport.readDouble();
                                    iStreamReport.readDouble();
                                    iStreamReport.readDouble();
                                    iStreamReport.readDouble();
                                    iStreamReport.readDouble();

                                    System.out.println("The total money made from booth's is $" + df2.format(iStreamReport.readDouble()));
                                    System.out.println("The total money made from card transactions is $" + df2.format(iStreamReport.readDouble()));
                                    System.out.println("The total money made from cash transactions is $" + df2.format(iStreamReport.readDouble()));
                                    System.out.println("The total money made from Es transactions is $" + df2.format(iStreamReport.readDouble()));
                                    System.out.println("The total money made from this station is $" + df2.format(iStreamReport.readDouble()));

                                    iStreamReport.readDouble();
                                    iStreamReport.readDouble();
                                    iStreamReport.readDouble();
                                    iStreamReport.readDouble();

                                    System.out.println("The money paid by each car is $" + iStreamReport.readObject());
                                    System.out.println("The car type of each user is " + iStreamReport.readObject());
                                    System.out.println("The payment type of each car is $" + iStreamReport.readObject());
                                    System.out.println("The number of axel's  of each car is " + iStreamReport.readObject());

                                    iStreamReport.close();
                                } catch (IOException e) {
                                    System.out.println("A" + e.getLocalizedMessage());
                                }
                            } catch (IOException e) {
                                System.out.println("B" + e.getLocalizedMessage());
                            }
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("C" + e.getLocalizedMessage());
                    } catch (IOException e) {
                        System.out.println("D " + e.getMessage());
                    }
                break;
            case "user":
                try {
                    ObjectInputStream iStreamConfig = new ObjectInputStream(new FileInputStream(new File("config.txt")));
                    // where the configuration will be read from
                    Object car_types = iStreamConfig.readObject();
                    double number_Of_Booth = iStreamConfig.readDouble();
                    Object payment_types = iStreamConfig.readObject();
                    double card_fees = iStreamConfig.readDouble();
                    double es_discount = iStreamConfig.readDouble();
                    double base_rate = iStreamConfig.readDouble();
                    double EV_discount = iStreamConfig.readDouble();
                    double hybrid_discount = iStreamConfig.readDouble();

                    iStreamConfig.close();

                    double totalStationMade = 0;
                    for (int i = (int) number_Of_Booth; i > 0; i--) {
                        double total_card_made = TollBooth.totalCardMade;
                        double total_cash_made = TollBooth.totalCashMade;
                        double total_ES_made = TollBooth.totalEsMade;
                        double money_Made_at_Booth = TollBooth.Booth_Money_Total();
                        totalStationMade =+ money_Made_at_Booth;
                        for (double number_Of_Cars_At_Booth = 3; number_Of_Cars_At_Booth > 0; number_Of_Cars_At_Booth--) {

                            vehicle.Sample_Questions_for_vehicle_Numbers(car_types, payment_types);
                            double payment_Type = keyboard.nextDouble();
                            double car_Type = keyboard.nextDouble();
                            double number_Of_Axel = keyboard.nextDouble();

                            double price_Car_pay = TollBooth.Price_to_be_Paid(payment_Type,number_Of_Axel,car_Type,hybrid_discount,EV_discount,base_rate,es_discount,card_fees);
                            System.out.println("Your car's cost is $" + df2.format(price_Car_pay));

                            try {
                                ObjectOutputStream oStreamVehicle = new ObjectOutputStream(new FileOutputStream(new File("Vehicle.txt")));
                                oStreamVehicle.writeObject(car_types);
                                oStreamVehicle.writeDouble(number_Of_Booth);
                                oStreamVehicle.writeObject(payment_types);
                                oStreamVehicle.writeDouble(card_fees);
                                oStreamVehicle.writeDouble(es_discount);
                                oStreamVehicle.writeDouble(base_rate);
                                oStreamVehicle.writeDouble(EV_discount);
                                oStreamVehicle.writeDouble(hybrid_discount);

                                oStreamVehicle.writeDouble(totalStationMade);
                                oStreamVehicle.writeDouble(total_card_made);
                                oStreamVehicle.writeDouble(total_cash_made);
                                oStreamVehicle.writeDouble(total_ES_made);
                                oStreamVehicle.writeDouble(money_Made_at_Booth);

                                oStreamVehicle.writeDouble(payment_Type);
                                oStreamVehicle.writeDouble(car_Type);
                                oStreamVehicle.writeDouble(number_Of_Axel);

                                oStreamVehicle.writeDouble(price_Car_pay);

                                oStreamVehicle.close();

                                List<String> prices_Paid_by_users = new ArrayList<>();
                                List<String> users_car_Types = new ArrayList<>();
                                List<String> users_Payment_Types = new ArrayList<>();
                                List<String> users_number_of_axels = new ArrayList<>();

                                for (int x = (int) number_Of_Cars_At_Booth; x > 0; x--) {
                                    prices_Paid_by_users.add(String.valueOf(price_Car_pay));
                                    prices_Paid_by_users.add(String.valueOf(users_car_Types));
                                    prices_Paid_by_users.add(String.valueOf(users_Payment_Types));
                                    prices_Paid_by_users.add(String.valueOf(users_number_of_axels));
                                }
                                try {
                                    ObjectOutputStream oStreamPaid = new ObjectOutputStream(new FileOutputStream(new File("paid.txt")));
                                    oStreamPaid.writeObject(prices_Paid_by_users);
                                    oStreamPaid.writeObject(users_car_Types);
                                    oStreamPaid.writeObject(users_Payment_Types);
                                    oStreamPaid.writeObject(users_number_of_axels);
                                    oStreamPaid.close();
                                } catch (IOException e) {
                                    System.out.println(e.getLocalizedMessage());
                                }
                            } catch (IOException e) {
                                System.out.println(e.getLocalizedMessage());
                            }
                        }
                    }
                }catch (IOException e){
                    System.out.println(e.getLocalizedMessage());
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + args[0]);
        }
    }
}

