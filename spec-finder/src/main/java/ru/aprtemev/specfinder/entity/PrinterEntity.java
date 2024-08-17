package ru.aprtemev.specfinder.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Data
@Document
public class PrinterEntity {

    @Id
    @NotBlank
    private String model;

    @NotNull
    @Min(0)
    private Integer printAreaX;

    @NotNull
    @Min(0)
    private Integer printAreaY;

    @NotNull
    @Min(0)
    private Integer printAreaZ;

    private Integer maxPrintSpeed;

    private Integer printHeadMovementSpeed;

    private List<String> typeCompatiblePlastic;

    private List<String> connectInterface;

    private List<String> platformCalibration;

    private Integer numbsOfNozzlesOnPrintHead;

    private BigDecimal minLayerThickness;

    private BigDecimal nozzleDiameter;

    private Integer maxPrintHeadTemp;

    private List<String> coolingPrintArea;

    private String availabilityOfPlasticControlSys;

    private Integer maximumPrintTemperature;

    private List<String> printPlatformType;

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

    private BigInteger price;

    private String filamentFeedType;

    private BigDecimal filamentDiameter;

    private Integer accuracyPositioningX;

    private Integer accuracyPositioningY;

    private Integer accuracyPositioningZ;

    private String material;

    private String printBedMaterial;

    private List<String> printBedCalibrationType;

    private Map<String, String> otherSpecs;
}
