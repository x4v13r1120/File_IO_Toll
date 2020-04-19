import java.util.List;

public class TollBooth {

    public static double totalCashMade = 0;
    public static double totalCardMade = 0;
    public static double totalEsMade = 0;

    public static double totalCardCost(double carCost) {
        double cardFees = 0.15;
        return (carCost * cardFees) + carCost;
    }

    public static double totalESCost(double carCost) {
        double esdiscount = 0.20;
        return carCost - (carCost * esdiscount);
    }

    public static double totalCashCost(double carCost) {
        return carCost;
    }
    public static double Price_Before_payment_Types(int number_Of_Axel, int car_Type,double hybrid_discount,double EV_discount,double base_rate) {
        switch (car_Type) {
            case 1:
                return vehicle.calculatedGasCost(number_Of_Axel,base_rate);
            case 2:
                return  vehicle.calculatedHybridCost(number_Of_Axel,hybrid_discount,base_rate);
            case 3:
                return  vehicle.calculatedEVCost(number_Of_Axel,EV_discount, base_rate);
        }
        return 0;
    }
    public static double Price_to_be_Paid(int payment_Type, int number_of_Axels, int car_Type, double hybrid_discount,double EV_discount,double base_rate){
        switch (payment_Type) {
            case 1:
                totalCashMade =+ TollBooth.totalCashCost(Price_Before_payment_Types(number_of_Axels , car_Type, hybrid_discount, EV_discount, base_rate));
                return TollBooth.totalCashCost(Price_Before_payment_Types(number_of_Axels , car_Type, hybrid_discount, EV_discount, base_rate));
            case 2:
                totalCardMade =+ TollBooth.totalCardCost(Price_Before_payment_Types(number_of_Axels , car_Type, hybrid_discount,EV_discount, base_rate));
                return TollBooth.totalCardCost(Price_Before_payment_Types(number_of_Axels , car_Type, hybrid_discount,EV_discount, base_rate));
            case 3:
                totalEsMade =+ TollBooth.totalESCost(Price_Before_payment_Types(number_of_Axels , car_Type,hybrid_discount, EV_discount,base_rate));
                return TollBooth.totalESCost(Price_Before_payment_Types(number_of_Axels , car_Type,hybrid_discount, EV_discount,base_rate));
        }
        return 0;
    }
    public static double Booth_Money_Total (){
        return TollBooth.totalCardMade + TollBooth.totalCashMade + TollBooth.totalEsMade;
    }

}
