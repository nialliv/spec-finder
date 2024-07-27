package ru.aprtemev.specfinder.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.aprtemev.specfinder.entity.PrinterEntity;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum PrinterFieldsContainer {
    PRINT_AREA_X("Область печати по оси X", PrinterEntity::setPrintAreaX),
    PRINT_AREA_Y("Область печати по оси Y", PrinterEntity::setPrintAreaY),
    PRINT_AREA_Z("Область печати по оси Z", PrinterEntity::setPrintAreaZ),

    MAX_PRINT_SPEED("Максимальная скорость печати", PrinterEntity::setMaxPrintSpeed),
    PRINT_HEAD_MOVEMENT_SPEED("Скорость перемещения печатающей головки", PrinterEntity::setPrintHeadMovementSpeed),
    TYPE_COMPATIBLE_PLASTIC("Тип совместимого с 3D-принтером пластика", PrinterEntity::setTypeCompatiblePlastic),
    CONNECT_INTERFACE("Интерфейс подключения", PrinterEntity::setConnectInterface),
    PLATFORM_CALIBRATION("Калибровка платформы", PrinterEntity::setPlatformCalibration),
    NUMBS_OF_NOZZLES_ON_PRINT_HEAD("Количество сопел на печатающей головке", PrinterEntity::setNumbsOfNozzlesOnPrintHead),
    MIN_LAYER_THICKNESS("Минимальная толщина слоя", PrinterEntity::setMinLayerThickness),
    NOZZLE_DIAMETER("Диаметр сопла", PrinterEntity::setNozzleDiameter),
    MAX_PRINT_HEAD_TEMP("Максимальная температура печатающей головки", PrinterEntity::setMaxPrintHeadTemp),
    COOLING_PRINT_AREA("Охлаждение зоны печати", PrinterEntity::setCoolingPrintArea),
    AVAILABILITY_OF_PLASTIC_CONTROL_SYS("Наличие системы контроля наличия пластика", PrinterEntity::setAvailabilityOfPlasticControlSys),
    MAXIMUM_PRINT_TEMPERATURE("Максимальная температура платформы для печати", PrinterEntity::setMaximumPrintTemperature),
    PRINT_PLATFORM_TYPE("Тип платформы для печати", PrinterEntity::setPrintPlatformType),
    AVAILABILITY_OF_CLOSED_CASE("Наличие закрытого корпуса", PrinterEntity::setAvailabilityOfClosedCase),
    AVAILABILITY_OF_VENTILATION_SYSTEM("Наличие системы вентиляции внутреннего пространства корпуса", PrinterEntity::setAvailabilityOfVentilationSystem),
    AVAILABILITY_OF_HEPA_FILTER("Наличие HEPA фильтра", PrinterEntity::setAvailabilityOfHEPAFilter),
    AVAILABILITY_OF_PRINT_PLATFORM_CONTROL_SYSTEM("Наличие системы контроля платформы печати", PrinterEntity::setAvailabilityOfPrintPlatformControlSystem),
    TYPE_OF_FIRST_LAYER_HEIGHT_CONTROL_SYSTEM("Тип системы контроля высоты первого слоя", PrinterEntity::setTypeOfFirstLayerHeightControlSystem),
    PRINT_FILE_FORMAT("Формат файлов для печати", PrinterEntity::setPrintFileFormat),
    AVAILABILITY_OF_BUILT_SETTINGS_FUNCTION("Наличие функции встроенной настройки печати", PrinterEntity::setAvailabilityOfBuiltSettingsFunction),
    PRINTER_CONTROL_TYPE("Тип управления принтером", PrinterEntity::setPrinterControlType),
    AVAILABILITY_OF_ACCESS_CONTROL_TO_PRINTER("Наличие контроля доступа к принтеру", PrinterEntity::setAvailabilityOfAccessControlToPrinter),
    AVAILABILITY_OF_CONTINUE_PRINTING_AFTER_POWER_OFF("Наличие функции продолжения печати после отключения питания", PrinterEntity::setAvailabilityOfContinuePrintingAfterPowerOff),
    AVAILABILITY_OF_REMOTE_CONTROL("Наличие функции удаленного управления принтером", PrinterEntity::setAvailabilityOfRemoteControl),
    AVAILABILITY_OF_DRYING_MODE_FUNCTION("Наличие функции встроенного режима сушки", PrinterEntity::setAvailabilityOfDryingModeFunction),
    COUNTRY_OF_ORIGIN("Страна происхождения", PrinterEntity::setCountryOfOrigin),
    PRICE("Цена", PrinterEntity::setPrice),
    FILAMENT_FEED_TYPE("Тип подачи филамента", PrinterEntity::setFilamentFeedType),
    FILAMENT_DIAMETER("диаметр филамента", PrinterEntity::setFilamentDiameter),
    ACCURACY_POSITIONING_X("Точность позиционирования по оси Х", PrinterEntity::setAccuracyPositioningX),
    ACCURACY_POSITIONING_Y("Точность позиционирования по оси Y", PrinterEntity::setAccuracyPositioningY),
    ACCURACY_POSITIONING_Z("Точность позиционирования по оси Z", PrinterEntity::setAccuracyPositioningZ),
    MATERIAL("Материал корпуса", PrinterEntity::setMaterial),
    PRINT_BED_MATERIAL("Материал печатного стола", PrinterEntity::setPrintBedMaterial),
    PRINT_BED_CALIBRATION_TYPE("Тип калибровки печатного стола", PrinterEntity::setPrintBedCalibrationType);

    private final String fieldName;
    private final BiConsumer<PrinterEntity, String> fieldSetterConsumer;

    private static final Map<String, BiConsumer<PrinterEntity, String>> requiredParamsConsumers =
            Arrays.stream(PrinterFieldsContainer.values())
                    .collect(Collectors.toMap(PrinterFieldsContainer::getFieldName, PrinterFieldsContainer::getFieldSetterConsumer));

    public static PrinterFieldsContainer getContainerByFieldName(String fieldName) {
        return Arrays.stream(PrinterFieldsContainer.values())
                .filter(container -> fieldName.contains(container.getFieldName()))
                .findAny()
                .orElse(null);
    }
}
