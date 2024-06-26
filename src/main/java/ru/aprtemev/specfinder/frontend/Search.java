package ru.aprtemev.specfinder.frontend;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Search {

    private String value;

    private String regexp;

}
