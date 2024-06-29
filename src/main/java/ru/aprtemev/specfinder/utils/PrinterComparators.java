package ru.aprtemev.specfinder.utils;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.aprtemev.specfinder.dto.Printer;
import ru.aprtemev.specfinder.dto.frontend.Direction;


//TODO fixed this sheet, mb need use reflection...
public final class PrinterComparators {

    static Map<Key, Comparator<Printer>> map = new HashMap<>();

    static {
        map.put(new Key("model", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getModel)));
        map.put(new Key("model", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getModel).reversed()));

        map.put(new Key("print_area_x", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintAreaX)));
        map.put(new Key("print_area_x", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintAreaX).reversed()));

        map.put(new Key("print_area_y", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintAreaY)));
        map.put(new Key("print_area_y", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintAreaY).reversed()));

        map.put(new Key("print_area_z", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintAreaZ)));
        map.put(new Key("print_area_z", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintAreaZ).reversed()));

        map.put(new Key("maxPrintSpeed", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getMaxPrintSpeed)));
        map.put(new Key("maxPrintSpeed", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getMaxPrintSpeed).reversed()));

        map.put(new Key("printHeadMovementSpeed", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintHeadMovementSpeed)));
        map.put(new Key("printHeadMovementSpeed", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintHeadMovementSpeed).reversed()));

        map.put(new Key("typeCompatiblePlastic", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getTypeCompatiblePlastic)));
        map.put(new Key("typeCompatiblePlastic", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getTypeCompatiblePlastic).reversed()));

        map.put(new Key("connectInterface", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getConnectInterface)));
        map.put(new Key("connectInterface", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getConnectInterface).reversed()));

        map.put(new Key("platformCalibration", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPlatformCalibration)));
        map.put(new Key("platformCalibration", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPlatformCalibration).reversed()));

        map.put(new Key("numbsOfNozzlesOnPrintHead", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getNumbsOfNozzlesOnPrintHead)));
        map.put(new Key("numbsOfNozzlesOnPrintHead", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getNumbsOfNozzlesOnPrintHead).reversed()));

        map.put(new Key("minLayerThickness", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getMinLayerThickness)));
        map.put(new Key("minLayerThickness", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getMinLayerThickness).reversed()));

        map.put(new Key("nozzleDiameter", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getNozzleDiameter)));
        map.put(new Key("nozzleDiameter", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getNozzleDiameter).reversed()));

        map.put(new Key("maxPrintHeadTemp", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getMaxPrintHeadTemp)));
        map.put(new Key("maxPrintHeadTemp", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getMaxPrintHeadTemp).reversed()));

        map.put(new Key("coolingPrintArea", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getCoolingPrintArea)));
        map.put(new Key("coolingPrintArea", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getCoolingPrintArea).reversed()));

        map.put(new Key("availabilityOfPlasticControlSys", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfPlasticControlSys)));
        map.put(new Key("availabilityOfPlasticControlSys", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfPlasticControlSys).reversed()));

        map.put(new Key("maximumPrintTemperature", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getMaximumPrintTemperature)));
        map.put(new Key("maximumPrintTemperature", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getMaximumPrintTemperature).reversed()));

        map.put(new Key("printPlatformType", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintPlatformType)));
        map.put(new Key("printPlatformType", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintPlatformType).reversed()));

        map.put(new Key("availabilityOfClosedCase", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfClosedCase)));
        map.put(new Key("availabilityOfClosedCase", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfClosedCase).reversed()));

        map.put(new Key("availabilityOfVentilationSystem", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfVentilationSystem)));
        map.put(new Key("availabilityOfVentilationSystem", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfVentilationSystem).reversed()));

        map.put(new Key("availabilityOfHEPAFilter", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfHEPAFilter)));
        map.put(new Key("availabilityOfHEPAFilter", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfHEPAFilter).reversed()));

        map.put(new Key("availabilityOfPrintPlatformControlSystem", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfPrintPlatformControlSystem)));
        map.put(new Key("availabilityOfPrintPlatformControlSystem", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfPrintPlatformControlSystem).reversed()));

        map.put(new Key("typeOfFirstLayerHeightControlSystem", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getTypeOfFirstLayerHeightControlSystem)));
        map.put(new Key("typeOfFirstLayerHeightControlSystem", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getTypeOfFirstLayerHeightControlSystem).reversed()));

        map.put(new Key("printFileFormat", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintFileFormat)));
        map.put(new Key("printFileFormat", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintFileFormat).reversed()));

        map.put(new Key("availabilityOfBuiltSettingsFunction", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfBuiltSettingsFunction)));
        map.put(new Key("availabilityOfBuiltSettingsFunction", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfBuiltSettingsFunction).reversed()));

        map.put(new Key("printerControlType", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrinterControlType)));
        map.put(new Key("printerControlType", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrinterControlType).reversed()));

        map.put(new Key("availabilityOfAccessControlToPrinter", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfAccessControlToPrinter)));
        map.put(new Key("availabilityOfAccessControlToPrinter", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfAccessControlToPrinter).reversed()));

        map.put(new Key("availabilityOfContinuePrintingAfterPowerOff", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfContinuePrintingAfterPowerOff)));
        map.put(new Key("availabilityOfContinuePrintingAfterPowerOff", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfContinuePrintingAfterPowerOff).reversed()));

        map.put(new Key("availabilityOfRemoteControl", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfRemoteControl)));
        map.put(new Key("availabilityOfRemoteControl", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfRemoteControl).reversed()));

        map.put(new Key("availabilityOfDryingModeFunction", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfDryingModeFunction)));
        map.put(new Key("availabilityOfDryingModeFunction", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getAvailabilityOfDryingModeFunction).reversed()));

        map.put(new Key("countryOfOrigin", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getCountryOfOrigin)));
        map.put(new Key("countryOfOrigin", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getCountryOfOrigin).reversed()));

        map.put(new Key("price", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrice)));
        map.put(new Key("price", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrice).reversed()));

        map.put(new Key("filamentFeedType", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getFilamentFeedType)));
        map.put(new Key("filamentFeedType", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getFilamentFeedType).reversed()));

        map.put(new Key("filamentDiameter", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getFilamentDiameter)));
        map.put(new Key("filamentDiameter", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getFilamentDiameter).reversed()));

        map.put(new Key("material", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getMaterial)));
        map.put(new Key("material", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getMaterial).reversed()));

        map.put(new Key("printBedMaterial", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintBedMaterial)));
        map.put(new Key("printBedMaterial", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintBedMaterial).reversed()));

        map.put(new Key("printBedCalibrationType", Direction.asc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintBedCalibrationType)));
        map.put(new Key("printBedCalibrationType", Direction.desc), Comparator.nullsFirst(Comparator.comparing(Printer::getPrintBedCalibrationType).reversed()));
    }

    private PrinterComparators() {
    }

    public static Comparator<Printer> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    static class Key {

        String name;

        Direction dir;

    }
}
