package code;

import java.util.*;

public class LLAPSearch extends GenericSearch {
        private static byte initialProsperity;
        private static byte initialFood;
        private static byte initialMaterials;
        private static byte initialEnergy;
        private static int unitPriceFood;
        private static int unitPriceMaterials;
        private static int unitPriceEnergy;
        private static byte amountRequestFood;
        private static byte delayRequestFood;
        private static byte amountRequestMaterials;
        private static byte delayRequestMaterials;
        private static byte amountRequestEnergy;
        private static byte delayRequestEnergy;
        private static int priceBuild1;
        private static byte foodUseBuild1;
        private static byte materialsUseBuild1;
        private static byte energyUseBuild1;
        private static byte prosperityBuild1;
        private static int priceBuild2;
        private static byte foodUseBuild2;
        private static byte materialsUseBuild2;
        private static byte energyUseBuild2;
        private static byte prosperityBuild2;

        private static void getVariables(String init) {
                // Split the input string into parts
                String[] parts = init.split(";");

                // Initialize variables as specified

                initialProsperity = Byte.parseByte(parts[0]);
                String[] resourceLevels = parts[1].split(",");
                initialFood = Byte.parseByte(resourceLevels[0]);
                initialMaterials = Byte.parseByte(resourceLevels[1]);
                initialEnergy = Byte.parseByte(resourceLevels[2]);

                String[] unitPrices = parts[2].split(",");
                unitPriceFood = Integer.parseInt(unitPrices[0]);
                unitPriceMaterials = Integer.parseInt(unitPrices[1]);
                unitPriceEnergy = Integer.parseInt(unitPrices[2]);

                String[] foodDelivery = parts[3].split(",");
                amountRequestFood = Byte.parseByte(foodDelivery[0]);
                delayRequestFood = Byte.parseByte(foodDelivery[1]);

                String[] materialsDelivery = parts[4].split(",");
                amountRequestMaterials = Byte.parseByte(materialsDelivery[0]);
                delayRequestMaterials = Byte.parseByte(materialsDelivery[1]);

                String[] energyDelivery = parts[5].split(",");
                amountRequestEnergy = Byte.parseByte(energyDelivery[0]);
                delayRequestEnergy = Byte.parseByte(energyDelivery[1]);

                String[] build1Data = parts[6].split(",");
                priceBuild1 = Integer.parseInt(build1Data[0]);
                foodUseBuild1 = Byte.parseByte(build1Data[1]);
                materialsUseBuild1 = Byte.parseByte(build1Data[2]);
                energyUseBuild1 = Byte.parseByte(build1Data[3]);
                prosperityBuild1 = Byte.parseByte(build1Data[4]);

                String[] build2Data = parts[7].split(",");
                priceBuild2 = Integer.parseInt(build2Data[0]);
                foodUseBuild2 = Byte.parseByte(build2Data[1]);
                materialsUseBuild2 = Byte.parseByte(build2Data[2]);
                energyUseBuild2 = Byte.parseByte(build2Data[3]);
                prosperityBuild2 = Byte.parseByte(build2Data[4]);
        }

        public static String solve(String init, String strategy, boolean visualize) {

                getVariables(init);
                ConstantData con = new ConstantData(unitPriceFood, unitPriceMaterials, unitPriceEnergy,
                        amountRequestFood, delayRequestFood, amountRequestMaterials,
                        delayRequestMaterials, amountRequestEnergy, delayRequestEnergy,
                        priceBuild1, foodUseBuild1, materialsUseBuild1, energyUseBuild1, prosperityBuild1,
                        priceBuild2, foodUseBuild2, materialsUseBuild2, energyUseBuild2, prosperityBuild2);
                Node node = new Node(initialProsperity, initialFood, initialMaterials, initialEnergy, con);

                String res = GeneralSearch(node, strategy, visualize);
                return res;
        }


}
