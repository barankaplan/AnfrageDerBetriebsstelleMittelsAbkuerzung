package com.deutschebahn.rest.data.dal;

import com.deutschebahn.rest.data.entity.OperationOffice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

public class OperationOfficeFactory {


    private static OperationOfficeFactory operationOfficeFactoryReadOnce= new OperationOfficeFactory();
    private static int readFromCollection;
    private static int readAlwaysFromFile;
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


    private static Optional<OperationOfficeFactory> loadFromCollectionOnce(String path) throws IOException {
        Optional<OperationOfficeFactory> result = Optional.empty();

        try (var br = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8)) {
            if (br.readLine() == null ) return result;

            String line;
            if (!operationOfficeFactoryReadOnce.OPERATION_OFFICES.isEmpty()){
                result = Optional.of(operationOfficeFactoryReadOnce);
                System.out.println("reading from collection:"+ readFromCollection);
                System.out.println("operationOfficeFactory size:"+operationOfficeFactoryReadOnce.OPERATION_OFFICES.size());
                return result;
            }

            while ((line = br.readLine()) != null){
                readFromCollection++;
                operationOfficeFactoryReadOnce.OPERATION_OFFICES.add(getOperationOffice(line));
            }

            result = Optional.of(operationOfficeFactoryReadOnce);
        }
        System.out.println("reading from csv:"+ readFromCollection);
        System.out.println("operationOfficeFactory size:"+operationOfficeFactoryReadOnce.OPERATION_OFFICES.size());

        return result;
    }
    private static Optional<OperationOfficeFactory> loadFromCSVFile(String path) throws IOException {
        Optional<OperationOfficeFactory> result = Optional.empty();

        try (var br = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8)) {
            if (br.readLine() == null) return result;

            var operationOfficeFactory = new OperationOfficeFactory();
            String line;

            while ((line = br.readLine()) != null){
                operationOfficeFactory.OPERATION_OFFICES.add(getOperationOffice(line));
                readAlwaysFromFile++;
            }
            result = Optional.of(operationOfficeFactory);
            System.out.println("reading from csv: "+ readAlwaysFromFile);
            System.out.println("operationOfficeFactory size: "+ result.get().OPERATION_OFFICES.size());
        }

        return result;
    }


    public static Optional<OperationOffice> getOperationOfficeByCodeFromCSV(String code) {
        try {


            var productFactory = OperationOfficeFactory.loadFromCSVFile("src/main/resources/Kaplan-DBNetz.csv");

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
    public static Optional<OperationOffice> getOperationOfficeByCodeFromCollection(String code) {
        try {


            var productFactory = OperationOfficeFactory.loadFromCollectionOnce("src/main/resources/Kaplan-DBNetz.csv");

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

    public static Set<OperationOffice> getOperationOfficesLoadFromCSVFile() {
        try {

            var operationOfficeFactory = OperationOfficeFactory.loadFromCSVFile("src/main/resources/Kaplan-DBNetz.csv");

            var operationOffices = operationOfficeFactory.get().OPERATION_OFFICES;

            return operationOffices;

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return Collections.emptySet();
    }
    public static Set<OperationOffice> getOperationOfficesLoadFromCollectionOnce() {
        try {

            var operationOfficeFactory = OperationOfficeFactory.loadFromCollectionOnce("src/main/resources/Kaplan-DBNetz.csv");

            var operationOffices = operationOfficeFactory.get().OPERATION_OFFICES;

            return operationOffices;

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return Collections.emptySet();
    }


}
