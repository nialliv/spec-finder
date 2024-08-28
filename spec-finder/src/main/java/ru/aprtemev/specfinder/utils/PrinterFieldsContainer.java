package ru.aprtemev.specfinder.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import ru.aprtemev.specfinder.entity.PrinterEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum PrinterFieldsContainer {
    PRINT_AREA_X("Область печати по оси X", "printAreaX",
            (entity, param) -> entity.setPrintAreaX(getParamAsInt(param))),
    PRINT_AREA_Y("Область печати по оси Y", "printAreaY",
            (entity, param) -> entity.setPrintAreaY(getParamAsInt(param))),
    PRINT_AREA_Z("Область печати по оси Z", "printAreaZ",
            (entity, param) -> entity.setPrintAreaZ(getParamAsInt(param))),

    MAX_PRINT_SPEED("Максимальная скорость печати", "maxPrintSpeed",
            (entity, param) -> entity.setMaxPrintSpeed(getParamAsInt(param))),
    PRINT_HEAD_MOVEMENT_SPEED("Скорость перемещения печатающей головки", "printHeadMovementSpeed",
            (entity, param) -> entity.setPrintHeadMovementSpeed(getParamAsInt(param))),
    TYPE_COMPATIBLE_PLASTIC("Тип совместимого с 3D-принтером пластика", "typeCompatiblePlastic",
            (entity, param) -> PrinterFieldsContainer.setListField(entity, param, PrinterEntity::setTypeCompatiblePlastic)),
    CONNECT_INTERFACE("Интерфейс подключения", "connectInterface",
            (entity, param) -> PrinterFieldsContainer.setListField(entity, param, PrinterEntity::setConnectInterface)),
    PLATFORM_CALIBRATION("Калибровка платформы", "platformCalibration",
            (entity, param) -> PrinterFieldsContainer.setListField(entity, param, PrinterEntity::setPlatformCalibration)),
    NUMBS_OF_NOZZLES_ON_PRINT_HEAD("Количество сопел на печатающей головке", "numbsOfNozzlesOnPrintHead",
            (entity, param) -> entity.setNumbsOfNozzlesOnPrintHead(getParamAsInt(param))),
    MIN_LAYER_THICKNESS("Минимальная толщина слоя", "minLayerThickness",
            (entity, param) -> entity.setMinLayerThickness(BigDecimal.valueOf(getParamAsDouble(param)))),
    NOZZLE_DIAMETER("Диаметр сопла", "nozzleDiameter",
            (entity, param) -> entity.setNozzleDiameter(BigDecimal.valueOf(getParamAsDouble(param)))),
    MAX_PRINT_HEAD_TEMP("Максимальная температура печатающей головки", "maxPrintHeadTemp",
            (entity, param) -> entity.setMaxPrintHeadTemp(getParamAsInt(param))),
    COOLING_PRINT_AREA("Охлаждение зоны печати", "coolingPrintArea",
            (entity, param) -> PrinterFieldsContainer.setListField(entity, param, PrinterEntity::setCoolingPrintArea)),
    AVAILABILITY_OF_PLASTIC_CONTROL_SYS("Наличие системы контроля наличия пластика", "availabilityOfPlasticControlSys",
            PrinterEntity::setAvailabilityOfPlasticControlSys),
    MAXIMUM_PRINT_TEMPERATURE("Максимальная температура платформы для печати", "maximumPrintTemperature",
            (entity, param) -> entity.setMaxPrintHeadTemp(getParamAsInt(param))),
    PRINT_PLATFORM_TYPE("Тип платформы для печати", "printPlatformType",
            (entity, param) -> PrinterFieldsContainer.setListField(entity, param, PrinterEntity::setPrintPlatformType)),
    AVAILABILITY_OF_CLOSED_CASE("Наличие закрытого корпуса", "availabilityOfClosedCase",
            PrinterEntity::setAvailabilityOfClosedCase),
    AVAILABILITY_OF_VENTILATION_SYSTEM("Наличие системы вентиляции внутреннего пространства корпуса", "availabilityOfVentilationSystem",
            PrinterEntity::setAvailabilityOfVentilationSystem),
    AVAILABILITY_OF_HEPA_FILTER("Наличие HEPA фильтра", "availabilityOfHEPAFilter",
            PrinterEntity::setAvailabilityOfHEPAFilter),
    AVAILABILITY_OF_PRINT_PLATFORM_CONTROL_SYSTEM("Наличие системы контроля платформы печати", "availabilityOfPrintPlatformControlSystem",
            PrinterEntity::setAvailabilityOfPrintPlatformControlSystem),
    TYPE_OF_FIRST_LAYER_HEIGHT_CONTROL_SYSTEM("Тип системы контроля высоты первого слоя", "typeOfFirstLayerHeightControlSystem",
            PrinterEntity::setTypeOfFirstLayerHeightControlSystem),
    PRINT_FILE_FORMAT("Формат файлов для печати", "printFileFormat",
            PrinterEntity::setPrintFileFormat),
    AVAILABILITY_OF_BUILT_SETTINGS_FUNCTION("Наличие функции встроенной настройки печати", "availabilityOfBuiltSettingsFunction",
            PrinterEntity::setAvailabilityOfBuiltSettingsFunction),
    PRINTER_CONTROL_TYPE("Тип управления принтером", "printerControlType",
            PrinterEntity::setPrinterControlType),
    AVAILABILITY_OF_ACCESS_CONTROL_TO_PRINTER("Наличие контроля доступа к принтеру", "availabilityOfAccessControlToPrinter",
            PrinterEntity::setAvailabilityOfAccessControlToPrinter),
    AVAILABILITY_OF_CONTINUE_PRINTING_AFTER_POWER_OFF("Наличие функции продолжения печати после отключения питания", "availabilityOfContinuePrintingAfterPowerOff",
            PrinterEntity::setAvailabilityOfContinuePrintingAfterPowerOff),
    AVAILABILITY_OF_REMOTE_CONTROL("Наличие функции удаленного управления принтером", "availabilityOfRemoteControl",
            PrinterEntity::setAvailabilityOfRemoteControl),
    AVAILABILITY_OF_DRYING_MODE_FUNCTION("Наличие функции встроенного режима сушки", "availabilityOfDryingModeFunction",
            PrinterEntity::setAvailabilityOfDryingModeFunction),
    COUNTRY_OF_ORIGIN("Страна происхождения", "countryOfOrigin",
            PrinterEntity::setCountryOfOrigin),
    PRICE("Цена", "price",
            (entity, param) -> entity.setPrice(BigInteger.valueOf(getParamAsInt(param)))),
    FILAMENT_FEED_TYPE("Тип подачи филамента", "filamentFeedType",
            PrinterEntity::setFilamentFeedType),
    FILAMENT_DIAMETER("Диаметр филамента", "filamentDiameter",
            (entity, param) -> entity.setFilamentDiameter(BigDecimal.valueOf(getParamAsDouble(param)))),
    ACCURACY_POSITIONING_X("Точность позиционирования по оси Х", "accuracyPositioningX",
            (entity, param) -> entity.setAccuracyPositioningX(getParamAsInt(param))),
    ACCURACY_POSITIONING_Y("Точность позиционирования по оси Y", "accuracyPositioningY",
            (entity, param) -> entity.setAccuracyPositioningY(getParamAsInt(param))),
    ACCURACY_POSITIONING_Z("Точность позиционирования по оси Z", "accuracyPositioningZ",
            (entity, param) -> entity.setAccuracyPositioningZ(getParamAsInt(param))),
    MATERIAL("Материал корпуса", "material",
            PrinterEntity::setMaterial),
    PRINT_BED_MATERIAL("Материал печатного стола", "printBedMaterial",
            PrinterEntity::setPrintBedMaterial),
    PRINT_BED_CALIBRATION_TYPE("Тип калибровки печатного стола", "printBedCalibrationType",
            (entity, param) -> PrinterFieldsContainer.setListField(entity, param, PrinterEntity::setPrintBedCalibrationType));

    private static final String OTHER_SPECS = "otherSpecs";

    private final String fieldNameFromDoc;
    private final String fieldNameFromDb;
    private final BiConsumer<PrinterEntity, String> fieldSetterConsumer;
    private static final Map<String, BiConsumer<PrinterEntity, String>> requiredParamsConsumers =
            Arrays.stream(PrinterFieldsContainer.values())
                    .collect(Collectors.toMap(PrinterFieldsContainer::getFieldNameFromDoc, PrinterFieldsContainer::getFieldSetterConsumer));

    public static PrinterFieldsContainer getContainerByFieldName(String fieldName) {
        return Arrays.stream(PrinterFieldsContainer.values())
                .filter(container -> fieldName.contains(container.getFieldNameFromDoc()))
                .findAny()
                .orElse(null);
    }

    public static String getFieldNameByRus(String rusField) {
        return Arrays.stream(values())
                .filter(container -> container.getFieldNameFromDoc().equals(rusField))
                .map(PrinterFieldsContainer::getFieldNameFromDb)
                .findAny()
                .orElse(OTHER_SPECS);
    }

    private static Integer getParamAsInt(String param) {
        return StringUtils.isBlank(param) ? 0 : Integer.parseInt(param);
    }

    private static Double getParamAsDouble(String param) {
        return StringUtils.isBlank(param) ? 0 : Double.parseDouble(param);
    }

    private static void setListField(PrinterEntity entity, String params, BiConsumer<PrinterEntity, List<String>> consumer) {
        List<String> listParams = Arrays.stream(params.split(","))
                .map(String::trim)
                .toList();
        consumer.accept(entity, listParams);
    }
}
