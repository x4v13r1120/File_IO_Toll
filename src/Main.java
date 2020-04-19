import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

       Random rand = new Random();
        Scanner keyboard = new Scanner(System.in);

        if (args[0].equals("config")) {


            starting_config_Questions_();
            try {
                while (true) {
                    ObjectOutputStream oStreamConfig = new ObjectOutputStream(new FileOutputStream(new File("Config.txt")));

                    int number_Of_Booths = keyboard.nextInt();
                    double base_rate = keyboard.nextDouble();
                    double EV_discount = keyboard.nextDouble();
                    double hybrid_Discount = keyboard.nextDouble();

                    List car_Types = new ArrayList(3);
                    car_Types.add("1." + keyboard.next());
                    car_Types.add("2." + keyboard.next());
                    car_Types.add("3." + keyboard.next());

                    List payment_methods = new ArrayList(3);
                    payment_methods.add("1." + keyboard.next());
                    payment_methods.add("2." + keyboard.next());
                    payment_methods.add("3." + keyboard.next());

                    oStreamConfig.writeInt(number_Of_Booths);
                    oStreamConfig.writeDouble(base_rate);
                    oStreamConfig.writeDouble(EV_discount);
                    oStreamConfig.writeDouble(hybrid_Discount);

                    oStreamConfig.writeObject(car_Types);
                    oStreamConfig.writeObject(payment_methods);

                    oStreamConfig.close();
                }

            } catch (EOFException e) {
                System.out.println("You have successful configured the toll station." + e.getLocalizedMessage());
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }

        } else if (args[0].equals("report")) {
            try {
                ObjectInputStream iStreamConfig = new ObjectInputStream(new FileInputStream(new File("config.txt")));
                // where the configuration will be read from

                int number_Of_Booth = iStreamConfig.readInt();
                double base_rate = iStreamConfig.readDouble();
                double EV_discount = iStreamConfig.readDouble();
                double hybrid_discount = iStreamConfig.readDouble();

                List car_types = (List) iStreamConfig.readObject();
                List payment_types = (List) iStreamConfig.readObject();

                iStreamConfig.close();

                try {
                    ObjectInputStream iStreamVehicle = new ObjectInputStream(new FileInputStream(new File("vehicle.txt")));


                    int payment_Type = iStreamVehicle.readInt();
                    int car_Type = iStreamVehicle.readInt();
                    int number_Of_Axel = iStreamVehicle.readInt();

                    double price_Car_pay = iStreamVehicle.readDouble();
                    double money_Made_at_Booth= iStreamVehicle.readDouble();
                    double totalStationMade =  iStreamVehicle.readDouble();

                    iStreamVehicle.close();

                    try {

                        ObjectInputStream iStreamReport = new ObjectInputStream(new FileInputStream(new File("Report.txt")));
                        // where the final report will be read from

                    } catch (IOException e) {
                        System.out.println(e.getLocalizedMessage());
                    }
                } catch (IOException e) {
                    System.out.println(e.getLocalizedMessage());
                }

            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getLocalizedMessage());
            }

        } else if ((args[0].equals("config") && args[1].equals("report")) || (args[0].equals("report") && args[1].equals("config"))) {

        } else if (args[0].equals("user")) {
            try {
                ObjectInputStream iStreamConfig = new ObjectInputStream(new FileInputStream(new File("config.txt")));
                // where the configuration will be read from

                int number_Of_Booth = iStreamConfig.readInt();
                double base_rate = iStreamConfig.readDouble();
                double EV_discount = iStreamConfig.readDouble();
                double hybrid_discount = iStreamConfig.readDouble();

                List car_types = (List) iStreamConfig.readObject();
                List payment_types = (List) iStreamConfig.readObject();

                iStreamConfig.close();

                ObjectOutputStream oStreamVehicle = new ObjectOutputStream(new FileOutputStream(new File("Vehicle.txt")));
                double totalStationMade = 0;
                for (int i = 0; i<number_Of_Booth; i--) {
                    double money_Made_at_Booth = TollBooth.Booth_Money_Total();
                    totalStationMade =+ money_Made_at_Booth;
                    for (int numberOfCarsAtStation = rand.nextInt(5); numberOfCarsAtStation > 0; numberOfCarsAtStation--) {

                        vehicle.Sample_numbers_for_vehicle_Questions(car_types, payment_types);

                        int payment_Type = keyboard.nextInt();
                        int car_Type = keyboard.nextInt();
                        int number_Of_Axel = keyboard.nextInt();

                        double price_Car_pay = TollBooth.Price_to_be_Paid(payment_Type, car_Type, number_Of_Axel, EV_discount, hybrid_discount, base_rate);

                        oStreamVehicle.writeInt(payment_Type);
                        oStreamVehicle.writeInt(car_Type);
                        oStreamVehicle.writeInt(number_Of_Axel);

                        oStreamVehicle.writeDouble(price_Car_pay);
                    }
                    oStreamVehicle.writeDouble(money_Made_at_Booth);
                }
                oStreamVehicle.writeDouble(totalStationMade);

                oStreamVehicle.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getLocalizedMessage());
            }
        } else {
            System.out.println("Something went wrong please try again.");
        }
    }

    public static void starting_config_Questions_() {
        System.out.println("Please write your number of booths.\n");
        System.out.println("Please write your base rate.\n");
        System.out.println("Please write your discount for electric cars.\n");
        System.out.println("Please write your discount for hybrid cars.\n");
        System.out.println("Please write your acceptable car types");
        System.out.println("Please write your acceptable payment methods");

    }

}
