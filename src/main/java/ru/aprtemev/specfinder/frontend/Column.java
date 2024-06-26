package ru.aprtemev.specfinder.frontend;

import lombok.Data;
import lombok.NoArgsConstructor;

// TODO refactor in other package
@Data
@NoArgsConstructor
public class Column {

    private String data;

    private String name;

    private Boolean searchable;

    private Boolean orderable;

    private Search search;

    public Column(String data) {
        this.data = data;
    }
}
