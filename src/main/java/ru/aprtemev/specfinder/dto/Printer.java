package ru.aprtemev.specfinder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Data
public class Printer {

    private String model;

    @JsonProperty("print_area_x")
    private Integer printAreaX;

    @JsonProperty("print_area_y")
    private Integer printAreaY;

    @JsonProperty("print_area_z")
    private Integer printAreaZ;

    private String maxPrintSpeed;

    private String printHeadMovementSpeed;

    private String typeCompatiblePlastic;

    private String connectInterface;

    private String platformCalibration;

    private String numbsOfNozzlesOnPrintHead;

    private String minLayerThickness;

    private String nozzleDiameter;

    private String maxPrintHeadTemp;

    private String coolingPrintArea;

    private String availabilityOfPlasticControlSys;

    private String maximumPrintTemperature;

    private String printPlatformType;

    private String availabilityOfClosedCase;

    private String availabilityOfVentilationSystem;

    private String availabilityOfHEPAFilter;

    private String availabilityOfPrintPlatformControlSystem;

    private String typeOfFirstLayerHeightControlSystem;

    private String printFileFormat;

    private String availabilityOfBuiltSettingsFunction;

    private String printerControlType;

    private String availabilityOfAccessControlToPrinter;

    private String availabilityOfContinuePrintingAfterPowerOff;

    private String availabilityOfRemoteControl;

    private String availabilityOfDryingModeFunction;

    private String countryOfOrigin;

    private String price;

    private String filamentFeedType;

    private String filamentDiameter;

    private String accuracyPositioningX;

    private String accuracyPositioningY;

    private String accuracyPositioningZ;

    private String material;

    private String printBedMaterial;

    private String printBedCalibrationType;

    private Map<String, String> otherSpecs;

    public static List<String> toStringList(Printer printer) {
        return Arrays.asList(
                printer.getModel(),
                printer.getPrintAreaX().toString(),
                printer.getPrintAreaY().toString(),
                printer.getPrintAreaZ().toString(),
                printer.getMaxPrintSpeed(),
                printer.getPrintHeadMovementSpeed(),
                printer.getTypeCompatiblePlastic(),
                printer.getConnectInterface(),
                printer.getPlatformCalibration(),
                printer.getNumbsOfNozzlesOnPrintHead(),
                printer.getMinLayerThickness(),
                printer.getNozzleDiameter(),
                printer.getMaxPrintHeadTemp(),
                printer.getCoolingPrintArea(),
                printer.getAvailabilityOfPlasticControlSys(),
                printer.getMaximumPrintTemperature(),
                printer.getPrintPlatformType(),
                printer.getAvailabilityOfClosedCase(),
                printer.getAvailabilityOfVentilationSystem(),
                printer.getAvailabilityOfHEPAFilter(),
                printer.getAvailabilityOfPrintPlatformControlSystem(),
                printer.getTypeOfFirstLayerHeightControlSystem(),
                printer.getPrintFileFormat(),
                printer.getAvailabilityOfBuiltSettingsFunction(),
                printer.getPrinterControlType(),
                printer.getAvailabilityOfAccessControlToPrinter(),
                printer.getAvailabilityOfContinuePrintingAfterPowerOff(),
                printer.getAvailabilityOfRemoteControl(),
                printer.getAvailabilityOfDryingModeFunction(),
                printer.getCountryOfOrigin(),
                printer.getPrice(),
                printer.getFilamentFeedType(),
                printer.getFilamentDiameter(),
                printer.getAccuracyPositioningX(),
                printer.getAccuracyPositioningY(),
                printer.getAccuracyPositioningZ(),
                printer.getMaterial(),
                printer.getPrintBedMaterial(),
                printer.getPrintBedCalibrationType(),
                Optional.ofNullable(printer.getOtherSpecs())
                        .map(Objects::toString)
                        .orElse(StringUtils.EMPTY));
    }


}
