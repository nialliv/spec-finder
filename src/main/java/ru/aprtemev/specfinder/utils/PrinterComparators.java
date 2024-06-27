package ru.aprtemev.specfinder.utils;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.aprtemev.specfinder.dto.Printer;
import ru.aprtemev.specfinder.dto.frontend.Direction;

public final class PrinterComparators {

    static Map<Key, Comparator<Printer>> map = new HashMap<>();

    static {
        map.put(new Key("model", Direction.asc), Comparator.comparing(Printer::getModel));
        map.put(new Key("model", Direction.desc), Comparator.comparing(Printer::getModel)
                .reversed());

        map.put(new Key("area_x", Direction.asc), Comparator.comparing(Printer::getPrintAreaX));
        map.put(new Key("area_x", Direction.desc), Comparator.comparing(Printer::getPrintAreaX)
                .reversed());

        map.put(new Key("area_y", Direction.asc), Comparator.comparing(Printer::getPrintAreaY));
        map.put(new Key("area_y", Direction.desc), Comparator.comparing(Printer::getPrintAreaY)
                .reversed());

        map.put(new Key("area_z", Direction.asc), Comparator.comparing(Printer::getPrintAreaZ));
        map.put(new Key("area_z", Direction.desc), Comparator.comparing(Printer::getPrintAreaZ)
                .reversed());

        //TODO add compare with spec field ?
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
