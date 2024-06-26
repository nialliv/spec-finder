package ru.aprtemev.specfinder.utils;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.aprtemev.specfinder.dto.PrinterResponseDto;
import ru.aprtemev.specfinder.frontend.Direction;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public final class PrinterComparators {

    static Map<Key, Comparator<PrinterResponseDto>> map = new HashMap<>();

    static {
        map.put(new Key("model", Direction.asc), Comparator.comparing(PrinterResponseDto::getModel));
        map.put(new Key("model", Direction.desc), Comparator.comparing(PrinterResponseDto::getModel)
                .reversed());

        map.put(new Key("area_x", Direction.asc), Comparator.comparing(PrinterResponseDto::getPrintAreaX));
        map.put(new Key("area_x", Direction.desc), Comparator.comparing(PrinterResponseDto::getPrintAreaX)
                .reversed());

        map.put(new Key("area_y", Direction.asc), Comparator.comparing(PrinterResponseDto::getPrintAreaY));
        map.put(new Key("area_y", Direction.desc), Comparator.comparing(PrinterResponseDto::getPrintAreaY)
                .reversed());

        map.put(new Key("area_z", Direction.asc), Comparator.comparing(PrinterResponseDto::getPrintAreaZ));
        map.put(new Key("area_z", Direction.desc), Comparator.comparing(PrinterResponseDto::getPrintAreaZ)
                .reversed());

        //TODO add compare with spec field ?
    }

    private PrinterComparators() {
    }

    public static Comparator<PrinterResponseDto> getComparator(String name, Direction dir) {
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
