package code;

public record ConstantData(int unitPriceFood, int unitPriceMaterials, int unitPriceEnergy, byte amountRequestFood,
                           byte delayRequestFood, byte amountRequestMaterials, byte delayRequestMaterials,
                           byte amountRequestEnergy, byte delayRequestEnergy, int priceBuild1, byte foodUseBuild1,
                           byte materialsUseBuild1, byte energyUseBuild1, byte prosperityBuild1, int priceBuild2,
                           byte foodUseBuild2, byte materialsUseBuild2, byte energyUseBuild2, byte prosperityBuild2) {
}
