package ru.aprtemev.specfinder.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

@Getter
@AllArgsConstructor
public enum BooleanParseContainer {
    GT(">", (param, value) -> Criteria.where(param).gt(value)),
    LT("<", (param, value) -> Criteria.where(param).lt(value)),
    EQ("=", (param, value) -> Criteria.where(param).is(value)),
    GTE(">=", (param, value) -> Criteria.where(param).gte(value)),
    LTE("<=", (param, value) -> Criteria.where(param).lte(value)),;

    private final String value;
    private final BiFunction<String, Object, Criteria> biFunction;

    public static Optional<BiFunction<String, Object, Criteria>> getFilterMethod(String condition) {
        return Arrays.stream(values())
                .filter(container -> container.getValue().equals(condition))
                .map(BooleanParseContainer::getBiFunction)
                .findAny();
    }
}
