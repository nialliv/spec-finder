package ru.aprtemev.specfinder.dto.frontend;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Search {

    private String value;

    private String regexp;

}
