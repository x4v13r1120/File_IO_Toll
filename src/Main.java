import javax.imageio.IIOException;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws IOException, ClassNotFoundException {

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
                    ObjectInputStream iStreamConfig = new ObjectInputStream(new FileInputStream(new File("config.txt")));
                    // where the configuration will be read from
                    Object car_types = iStreamConfig.readObject();
                    double number_Of_Booth = iStreamConfig.readDouble();
                    Object payment_types = iStreamConfig.readObject();
                    double base_rate = iStreamConfig.readDouble();
                    double EV_discount = iStreamConfig.readDouble();
                    double hybrid_discount = iStreamConfig.readDouble();

                    iStreamConfig.close();
                    try {
                        ObjectInputStream iStreamVehicle = new ObjectInputStream(new FileInputStream(new File("vehicle.txt")));

                        //noinspection unused
                        double payment_Type = iStreamVehicle.readDouble();
                        //noinspection unused
                        double car_Type = iStreamVehicle.readDouble();
                        //noinspection unused
                        double number_Of_Axel = iStreamVehicle.readDouble();

                        double money_Made_at_Booth = iStreamVehicle.readDouble();
                        double totalCardMade = iStreamVehicle.readDouble();
                        double totalCashMade = iStreamVehicle.readDouble();
                        double totalEsMade = iStreamVehicle.readDouble();
                        double totalStationMade = iStreamVehicle.readDouble();
                        double price_Car_pay = iStreamVehicle.readDouble();

                        iStreamVehicle.close();
                        try {
                            ObjectInputStream iStreamPaid = new ObjectInputStream(new FileInputStream(new File("paid.txt")));

                            Object prices_paid = iStreamPaid.readObject();
                            iStreamPaid.close();
                            try {

                                ObjectOutputStream oStreamReport = new ObjectOutputStream(new FileOutputStream(new File("Report.txt")));
                                // where the final report will be written in
                                oStreamReport.writeObject(car_types);
                                oStreamReport.writeDouble(number_Of_Booth);
                                oStreamReport.writeObject(payment_types);
                                oStreamReport.writeDouble(base_rate);
                                oStreamReport.writeDouble(EV_discount);
                                oStreamReport.writeDouble(hybrid_discount);

                                oStreamReport.writeDouble(money_Made_at_Booth);
                                oStreamReport.writeDouble(totalCardMade);
                                oStreamReport.writeDouble(totalCashMade);
                                oStreamReport.writeDouble(totalEsMade);
                                oStreamReport.writeDouble(totalStationMade);
                                oStreamReport.writeDouble(price_Car_pay);

                                oStreamReport.writeObject(prices_paid);

                                oStreamReport.close();

                            } catch (IOException e) {
                                System.out.println(e.getLocalizedMessage());
                            }
                            try {
                                ObjectInputStream iStreamReport = new ObjectInputStream(new FileInputStream(new File("Report.txt")));

                                iStreamReport.readObject();
                                iStreamReport.readDouble();
                                iStreamReport.readObject();
                                iStreamReport.readDouble();
                                iStreamReport.readDouble();
                                iStreamReport.readDouble();


                                System.out.println("The total money made from booth's is $" + df2.format(iStreamReport.readDouble()));
                                System.out.println("The total money made from card transactions is $" + df2.format(iStreamReport.readDouble()));
                                System.out.println("The total money made from cash transactions is $" + df2.format(iStreamReport.readDouble()));
                                System.out.println("The total money made from Es transactions is $" + df2.format(iStreamReport.readDouble()));
                                System.out.println("The total money made from this station is $" + df2.format(iStreamReport.readDouble()));
                                System.out.println("The money paid by each car is" + iStreamReport.readObject());


                                iStreamReport.close();
                            } catch (IOException e) {
                                System.out.println(e.getLocalizedMessage());
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                } catch (IOException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                break;
            case "user":
                try {
                    ObjectInputStream iStreamConfig = new ObjectInputStream(new FileInputStream(new File("config.txt")));
                    // where the configuration will be read from
                    Object car_types = (iStreamConfig.readObject());
                    double number_Of_Booth = iStreamConfig.readDouble();
                    Object payment_types = iStreamConfig.readObject();
                    double base_rate = iStreamConfig.readDouble();
                    double EV_discount = iStreamConfig.readDouble();
                    double hybrid_discount = iStreamConfig.readDouble();
                    iStreamConfig.close();
                    try {
                        ObjectOutputStream oStreamVehicle = new ObjectOutputStream(new FileOutputStream(new File("Vehicle.txt")));
                        double totalStationMade = 0;
                        for (int i = (int) number_Of_Booth; i > 0; i--) {
                            double money_Made_at_Booth = TollBooth.Booth_Money_Total();
                            totalStationMade = +money_Made_at_Booth;
                            for (double number_Of_Cars_At_Booth = 3; number_Of_Cars_At_Booth > 0; number_Of_Cars_At_Booth--) {

                                vehicle.Sample_Questions_for_vehicle_Numbers(car_types, payment_types);
                                double payment_Type = keyboard.nextDouble();
                                double car_Type = keyboard.nextDouble();
                                double number_Of_Axel = keyboard.nextDouble();

                                double price_Car_pay = TollBooth.Price_to_be_Paid(payment_Type, car_Type, number_Of_Axel, EV_discount, hybrid_discount, base_rate);

                                oStreamVehicle.writeDouble(payment_Type);
                                oStreamVehicle.writeDouble(car_Type);
                                oStreamVehicle.writeDouble(number_Of_Axel);
                                oStreamVehicle.writeObject(price_Car_pay);
                                try {
                                    ObjectOutputStream oStreamPaid = new ObjectOutputStream(new FileOutputStream(new File("paid.txt")));
                                    List<Double> price_Paid_by_user = new ArrayList<>();
                                    for (int x = (int) number_Of_Booth; x > 0; x--) {
                                        price_Paid_by_user.add(price_Car_pay);
                                    }
                                    oStreamPaid.writeObject(price_Paid_by_user);
                                } catch (IOException e) {
                                    System.out.println(e.getLocalizedMessage());
                                }
                            }
                            oStreamVehicle.writeDouble(money_Made_at_Booth);
                        }
                        oStreamVehicle.writeDouble(TollBooth.totalCardMade);
                        oStreamVehicle.writeDouble(TollBooth.totalCashMade);
                        oStreamVehicle.writeDouble(TollBooth.totalEsMade);
                        oStreamVehicle.writeDouble(totalStationMade);

                        oStreamVehicle.close();
                    } catch (IOException e) {
                        System.out.println(e.getLocalizedMessage());
                    }

                } catch (IOException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                break;
        }
    }
}

