package ru.aprtemev.specfinder.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.service.DocFilterParser;
import ru.aprtemev.specfinder.utils.BooleanParseContainer;
import ru.aprtemev.specfinder.utils.PrinterFieldsContainer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class DocFilterParserImpl implements DocFilterParser {

    private static final String SEPARATOR = ":";
    private static final String SPACE = " ";
    private static final String DOT_WITH_COMMA = ";";
    private static final String REGEX_FOR_REMOVE_ALL_AFTER_COMMA = ",.*$";
    private static final String REGEX_NOT_NEEDED_LAST_SYMBOLS = "[.мсшт]+$|\\[.*]*$";
    private static final String SIMPLE_COMPARE_REGEX = "^[<>=]{1,2}\\s[\\d.]*$";
    private static final String INTERVAL_REGEX = "^[<>=]{1,2}\\s[\\d.]*\\s[<>=]{1,2}\\s[\\d.]*$";
    private static final String DOUBLE_REGEX = "^\\d+\\.\\d+$";
    private static final String INTEGER_REGEX = "^\\d+$";

    public Query parseDocToQueryFilter(MultipartFile file) {
        try {
            XWPFDocument document = new XWPFDocument(file.getInputStream());
            Query query = new Query();
            for(XWPFParagraph paragraph : document.getParagraphs()) {
                query.addCriteria(resolveParagraphs(paragraph));
            }
            return query;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Criteria resolveParagraphs(XWPFParagraph xwpfParagraph) {
        String line = xwpfParagraph.getText();
        String param = getParam(line);
        String value = getValue(line);
        return resolveTypeValueAndGetCriteria(param, value);
    }

    private Criteria resolveTypeValueAndGetCriteria(String param, String value) {
        //todo refactor this shit
        if(value.matches(INTERVAL_REGEX)) {
            String[] split = value.split(SPACE);

            Criteria firstPart = BooleanParseContainer.getFilterMethod(split[0])
                    .map(biFunc -> biFunc.apply(param, resolveTypeValue(split[1])))
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Error parse string value - [%s]", value)));

            Criteria secondPart = BooleanParseContainer.getFilterMethod(split[2])
                    .map(biFunc -> biFunc.apply(param, resolveTypeValue(split[3])))
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Error parse string value - [%s]", value)));

            return firstPart.andOperator(secondPart);
        }
        if(value.matches(SIMPLE_COMPARE_REGEX)) {

            String[] split = value.split(SPACE);

            return BooleanParseContainer.getFilterMethod(split[0])
                    .map(biFunc -> biFunc.apply(param, resolveTypeValue(split[1])))
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Error parse string value - [%s]", value)));
        }
        if(value.contains(DOT_WITH_COMMA)) {

            List<String> values = Arrays.asList(value.split(DOT_WITH_COMMA));

            return Criteria.where(param).in(values);
        }
        return Criteria.where(param).is(resolveTypeValue(value));
    }

    private Object resolveTypeValue(String valueAsString) {
        if(valueAsString.matches(DOUBLE_REGEX)) {
            return Double.parseDouble(valueAsString);
        }
        if(valueAsString.matches(INTEGER_REGEX)) {
            return Integer.parseInt(valueAsString);
        }
        return valueAsString;
    }

    private String getValue(String line) {
        return line.substring(line.indexOf(SEPARATOR) + 1)
                .replaceAll(REGEX_NOT_NEEDED_LAST_SYMBOLS, Strings.EMPTY)
                .trim();
    }

    private String getParam(String line) {
        String rusField = line.substring(0, line.indexOf(SEPARATOR))
                .replaceAll(REGEX_FOR_REMOVE_ALL_AFTER_COMMA, Strings.EMPTY);
        return PrinterFieldsContainer.getFieldNameByRus(rusField);
    }
}
