package ru.aprtemev.specfinder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class Printer {

    private String model;

    @JsonProperty("print_area_x")
    private String printAreaX;

    @JsonProperty("print_area_y")
    private String printAreaY;

    @JsonProperty("print_area_z")
    private String printAreaZ;

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

}
