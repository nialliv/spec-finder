package ru.aprtemev.specfinder.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Printer {

    private String model;

    @JsonProperty("print_area_x")
    private Integer printAreaX;

    @JsonProperty("print_area_y")
    private Integer printAreaY;

    @JsonProperty("print_area_z")
    private Integer printAreaZ;


    private Integer maxPrintSpeed;

    private Integer printHeadMovementSpeed;

    private String typeCompatiblePlastic;

    private String connectInterface;

    private String platformCalibration;

    private Integer numbsOfNozzlesOnPrintHead;

    private Double minLayerThickness;

    private Double nozzleDiameter;

    private Integer maxPrintHeadTemp;

    private String coolingPrintArea;

    private String availabilityOfPlasticControlSys;

    private Integer maximumPrintTemperature;

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

    private Integer price;

    private String filamentFeedType;

    private Double filamentDiameter;

    private Integer  accuracyPositioningX;

    private Integer  accuracyPositioningY;

    private Integer  accuracyPositioningZ;

    private String material;

    private String printBedMaterial;

    private String printBedCalibrationType;

    private Map<String, String> otherSpecs;
}
