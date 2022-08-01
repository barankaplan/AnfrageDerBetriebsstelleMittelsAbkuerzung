package com.deutschebahn.rest.data.dal;

import com.deutschebahn.rest.data.entity.OperationOffice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

public class OperationOfficeFactory {


    private final Set<OperationOffice> OPERATION_OFFICES = new LinkedHashSet<>();

    private OperationOfficeFactory() {
    }


    private static OperationOffice getOperationOffice(String line) {

        var operationOfficeInfo = line.split("[;]");//regex
        var code = operationOfficeInfo[1];
        var name = operationOfficeInfo[2];
        var shortName = operationOfficeInfo[3];
        String typ;
        if (operationOfficeInfo[4].isEmpty()) {
            typ = operationOfficeInfo[5];
        } else {
            typ = operationOfficeInfo[4];

        }
        OperationOffice operationOffice = new OperationOffice().setCode(code).setName(name).setShortName(shortName).setTyp(firstLetterCaps(typ));
        return operationOffice;
    }

    private static String firstLetterCaps(String data) {
        String firstLetter = data.substring(0, 1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }


    private static Optional<OperationOfficeFactory> loadFromTextFile(String path) throws IOException {
        Optional<OperationOfficeFactory> result = Optional.empty();

        try (var br = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8)) {
            if (br.readLine() == null) return result;

            var operationOfficeFactory = new OperationOfficeFactory();
            String line;

            while ((line = br.readLine()) != null)
                operationOfficeFactory.OPERATION_OFFICES.add(getOperationOffice(line));

            result = Optional.of(operationOfficeFactory);
        }

        return result;
    }


    public static Optional<OperationOffice> getOperationOfficeByCode(String code) {
        try {
            var productFactory = OperationOfficeFactory.loadFromTextFile("src/main/resources/Kaplan-DBNetz.csv");

            Set<OperationOffice> operationOffices = null;
            if (productFactory.isPresent()) {
                operationOffices = productFactory.get().OPERATION_OFFICES;
            }

            Predicate<OperationOffice> operationOfficePredicate = oo -> Objects.equals(oo.getCode(), code.toUpperCase(Locale.ROOT));

            var operationOffice = operationOffices.stream().filter(operationOfficePredicate).findFirst();

            return operationOffice;
        } catch (Throwable ex) {
            ex.printStackTrace();
            return Optional.empty();

        }
    }

    public static Set<OperationOffice> getOperationOffices() {
        try {
            var productFactory = OperationOfficeFactory.loadFromTextFile("src/main/resources/Kaplan-DBNetz.csv");

            var operationOffices = productFactory.get().OPERATION_OFFICES;

            return operationOffices;

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return Collections.emptySet();
    }


}
