package com.deutschebahn.rest.data.junit;

import com.deutschebahn.rest.data.dal.OperationOfficeFactory;
import com.deutschebahn.rest.data.entity.OperationOffice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AnfrageDerBetriebsstelleMittelsAbkuerzungApplicationTests {

    private Set<OperationOffice> operationOffices;

    @BeforeEach
    public void setup() {
        operationOffices = OperationOfficeFactory.getOperationOffices();

    }

    @Test
    @DisplayName("JUnit-Test, um zu bestätigen, ob das Programm die Daten wie geplant einlesen kann.")
    void givenOperationOffices_whenCheckData_AAMP_Test() {
        Predicate<OperationOffice> operationOfficePredicate = oo -> Objects.equals(oo.getCode(), "AAMP");
        assertThat(operationOffices.stream().filter(operationOfficePredicate).findFirst()).isNotEmpty();
        System.out.println("Der in der E-Mail angegebene Code wird in die Datenstruktur aufgenommen!");
    }

    @Test
    @DisplayName("JUnit-Test zur Validierung, ich möchte die Länge von Abkürzung begrenzen")
    void givenOperationOffices_whenCheckLength_thenReturnMaxDigits_Test() {
        Predicate<OperationOffice> operationOfficePredicate = oo -> oo.getCode().length() > 5;
        assertThat(operationOffices.stream().filter(operationOfficePredicate).findFirst()).isEmpty();
        System.out.println("Die Validierung kann auf 5 Ziffern basieren." + "Benutzerfehler (zusätzliches Leerzeichen) müssen ebenfalls berücksichtigt werden!");
    }

    @Test
    @DisplayName("JUnit-Test zur Validierung, ich möchte wissen, ob das erste Zeichen ein Buchstabe oder eine Zahl ist")
    void givenOperationOffices_whenCheckFirstChar_Test() {


        Function<? super OperationOffice, ?> getTheFirstDigit = new Function<OperationOffice, Character>() {
            @Override
            public Character apply(OperationOffice operationOffice) {
                return operationOffice.getCode().charAt(0);
            }
        };

        Optional<?> first = operationOffices.stream().map(getTheFirstDigit).filter(c -> Character.isDigit((char) c)).findFirst();


        Optional<String> stringOptional = operationOffices.stream().map(OperationOffice::getCode).filter(oo -> Character.isLetter(oo.charAt(0))).findFirst();


        assertThat(first).isEmpty();
        assertThat(stringOptional).isNotEmpty();
        System.out.println("Es gibt keine Abkürzungen,deren erstes Zeichen eine Zahl ist !");
    }


}
