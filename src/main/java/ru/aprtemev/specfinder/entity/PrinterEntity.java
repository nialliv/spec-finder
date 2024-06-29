package ru.aprtemev.specfinder.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class PrinterEntity {

    @Id
    @NotBlank
    private String model;

    @NotNull
    @Min(0)
    private String printAreaX;

    @NotNull
    @Min(0)
    private String printAreaY;

    @NotNull
    @Min(0)
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
