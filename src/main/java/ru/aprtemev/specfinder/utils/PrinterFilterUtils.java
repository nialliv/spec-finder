package ru.aprtemev.specfinder.utils;

import org.apache.commons.lang3.StringUtils;
import ru.aprtemev.specfinder.dto.Printer;

import java.util.function.Predicate;

public class PrinterFilterUtils {

    public static Predicate<Printer> getPredicate(String value) {
        return printer -> StringUtils.containsIgnoreCase(printer.getModel(), value)
                || StringUtils.containsIgnoreCase(printer.getPrintAreaX(), value)
                || StringUtils.containsIgnoreCase(printer.getPrintAreaY(), value)
                || StringUtils.containsIgnoreCase(printer.getPrintAreaZ(), value)
                || StringUtils.containsIgnoreCase(printer.getMaxPrintSpeed(), value)
                || StringUtils.containsIgnoreCase(printer.getPrintHeadMovementSpeed(), value)
                || StringUtils.containsIgnoreCase(printer.getTypeCompatiblePlastic(), value)
                || StringUtils.containsIgnoreCase(printer.getConnectInterface(), value)
                || StringUtils.containsIgnoreCase(printer.getPlatformCalibration(), value)
                || StringUtils.containsIgnoreCase(printer.getNumbsOfNozzlesOnPrintHead(), value)
                || StringUtils.containsIgnoreCase(printer.getMinLayerThickness(), value)
                || StringUtils.containsIgnoreCase(printer.getNozzleDiameter(), value)
                || StringUtils.containsIgnoreCase(printer.getMaxPrintHeadTemp(), value)
                || StringUtils.containsIgnoreCase(printer.getCoolingPrintArea(), value)
                || StringUtils.containsIgnoreCase(printer.getAvailabilityOfPlasticControlSys(), value)
                || StringUtils.containsIgnoreCase(printer.getMaximumPrintTemperature(), value)
                || StringUtils.containsIgnoreCase(printer.getPrintPlatformType(), value)
                || StringUtils.containsIgnoreCase(printer.getAvailabilityOfClosedCase(), value)
                || StringUtils.containsIgnoreCase(printer.getAvailabilityOfVentilationSystem(), value)
                || StringUtils.containsIgnoreCase(printer.getAvailabilityOfHEPAFilter(), value)
                || StringUtils.containsIgnoreCase(printer.getAvailabilityOfPrintPlatformControlSystem(), value)
                || StringUtils.containsIgnoreCase(printer.getTypeOfFirstLayerHeightControlSystem(), value)
                || StringUtils.containsIgnoreCase(printer.getPrintFileFormat(), value)
                || StringUtils.containsIgnoreCase(printer.getAvailabilityOfBuiltSettingsFunction(), value)
                || StringUtils.containsIgnoreCase(printer.getPrinterControlType(), value)
                || StringUtils.containsIgnoreCase(printer.getAvailabilityOfAccessControlToPrinter(), value)
                || StringUtils.containsIgnoreCase(printer.getAvailabilityOfContinuePrintingAfterPowerOff(), value)
                || StringUtils.containsIgnoreCase(printer.getAvailabilityOfRemoteControl(), value)
                || StringUtils.containsIgnoreCase(printer.getAvailabilityOfDryingModeFunction(), value)
                || StringUtils.containsIgnoreCase(printer.getCountryOfOrigin(), value)
                || StringUtils.containsIgnoreCase(printer.getPrice(), value)
                || StringUtils.containsIgnoreCase(printer.getFilamentFeedType(), value)
                || StringUtils.containsIgnoreCase(printer.getFilamentDiameter(), value)
                || StringUtils.containsIgnoreCase(printer.getAccuracyPositioningX(), value)
                || StringUtils.containsIgnoreCase(printer.getAccuracyPositioningY(), value)
                || StringUtils.containsIgnoreCase(printer.getAccuracyPositioningZ(), value)
                || StringUtils.containsIgnoreCase(printer.getMaterial(), value)
                || StringUtils.containsIgnoreCase(printer.getPrintBedMaterial(), value)
                || StringUtils.containsIgnoreCase(printer.getPrintBedCalibrationType(), value);
    }
}
