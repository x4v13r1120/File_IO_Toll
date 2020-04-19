import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TollBooth {

    public static double totalCashMade = 0;
    public static double totalCardMade = 0;
    public static double totalEsMade = 0;

    public static double Price_Before_payment_Types(double number_Of_Axel, double car_Type,double hybrid_discount,double EV_discount,double base_rate) {
        switch ((int) car_Type) {
            case 1://gas
                return vehicle.calculatedGasCost(number_Of_Axel,base_rate);
            case 2://hybrid
                return  vehicle.calculatedHybridCost(number_Of_Axel,hybrid_discount,base_rate);
            case 3://EV
                return  vehicle.calculatedEVCost(number_Of_Axel,EV_discount, base_rate);
        }
        return 0;
    }
    public static double Price_to_be_Paid(double payment_Type, double number_of_Axels, double car_Type,
                                          double hybrid_discount,double EV_discount,double base_rate,double es_discount, double card_fees){
        switch ((int) payment_Type) {
            case 1://cash
                double money_cash = TollBooth.Price_Before_payment_Types(number_of_Axels,car_Type,hybrid_discount,EV_discount,base_rate);
                totalCashMade =+ money_cash;
                return money_cash;
            case 2://card
                        double money_card =TollBooth.Price_Before_payment_Types(number_of_Axels,car_Type,hybrid_discount,EV_discount,base_rate)
                        +(card_fees*(TollBooth.Price_Before_payment_Types(number_of_Axels,car_Type,hybrid_discount,EV_discount,base_rate)));
                        totalCardMade =+money_card;
                return money_card;
            case 3://es
                 double money_es = TollBooth.Price_Before_payment_Types(number_of_Axels,car_Type,hybrid_discount,EV_discount,base_rate)
                        -(es_discount*(TollBooth.Price_Before_payment_Types(number_of_Axels,car_Type,hybrid_discount,EV_discount,base_rate)));
                totalEsMade =+ money_es;
                return money_es;
        }
        return 0;
    }
    public static double Booth_Money_Total (){
        return TollBooth.totalCardMade + TollBooth.totalCashMade + TollBooth.totalEsMade;
    }

}
