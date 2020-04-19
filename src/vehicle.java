import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class vehicle {

    public static double calculatedGasCost(double number_Of_Axel, double base_rate) {
        return base_rate + ((number_Of_Axel - 3) * 5) + (number_Of_Axel - (number_Of_Axel - 2));
    }
    public static double calculatedEVCost(double number_Of_Axel, double EV_discount,double base_rate) {
        double EVcost = base_rate + ((number_Of_Axel - 3) * 5) + ((number_Of_Axel * 1) - (number_Of_Axel - 2));
        return EVcost -
                ((EVcost) * EV_discount);
    }
    public static double calculatedHybridCost(double number_Of_Axel,double hybrid_discount,double base_rate) {

        double hybridCost = base_rate + ((number_Of_Axel - 3) * 5) + ((number_Of_Axel * 1) - (number_Of_Axel - 2));
        return hybridCost -
                (hybridCost * hybrid_discount);
    }
    @SuppressWarnings("rawtypes")
    public static void Sample_Questions_for_vehicle_Numbers(Object car_Types, Object payment_methods){
        System.out.println("What type of car do you drive?"+ car_Types);
        System.out.println("How will you be paying?"+ payment_methods);
        System.out.println("How many axel's does your car have?");
    }
}
