/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions;

import dev.qadenz.automation.conditions.impl.AttributeOfElement;
import dev.qadenz.automation.conditions.impl.CountOfElement;
import dev.qadenz.automation.conditions.impl.CssPropertyOfElement;
import dev.qadenz.automation.conditions.impl.DirectTextOfElement;
import dev.qadenz.automation.conditions.impl.DirectTextOfElementAsDate;
import dev.qadenz.automation.conditions.impl.DirectTextOfElementAsDateTime;
import dev.qadenz.automation.conditions.impl.DirectTextOfElementAsDouble;
import dev.qadenz.automation.conditions.impl.DirectTextOfElementAsInteger;
import dev.qadenz.automation.conditions.impl.DirectTextOfElementAsTime;
import dev.qadenz.automation.conditions.impl.DirectTextOfListElementsInOrder;
import dev.qadenz.automation.conditions.impl.DirectTextOfListElementsUnordered;
import dev.qadenz.automation.conditions.impl.EnabledStateOfElement;
import dev.qadenz.automation.conditions.impl.EnabledStateOfElements;
import dev.qadenz.automation.conditions.impl.PresenceOfAlert;
import dev.qadenz.automation.conditions.impl.PresenceOfElement;
import dev.qadenz.automation.conditions.impl.PresenceOfElements;
import dev.qadenz.automation.conditions.impl.SelectedMenuOption;
import dev.qadenz.automation.conditions.impl.SelectedMenuOptions;
import dev.qadenz.automation.conditions.impl.SelectedStateOfElement;
import dev.qadenz.automation.conditions.impl.TextOfAlert;
import dev.qadenz.automation.conditions.impl.TextOfElement;
import dev.qadenz.automation.conditions.impl.TextOfElementAsDate;
import dev.qadenz.automation.conditions.impl.TextOfElementAsDateTime;
import dev.qadenz.automation.conditions.impl.TextOfElementAsDouble;
import dev.qadenz.automation.conditions.impl.TextOfElementAsInteger;
import dev.qadenz.automation.conditions.impl.TextOfElementAsTime;
import dev.qadenz.automation.conditions.impl.TextOfElements;
import dev.qadenz.automation.conditions.impl.TextOfInputElement;
import dev.qadenz.automation.conditions.impl.TextOfListElementsInOrder;
import dev.qadenz.automation.conditions.impl.TextOfListElementsUnordered;
import dev.qadenz.automation.conditions.impl.TextOfSelectMenuOptions;
import dev.qadenz.automation.conditions.impl.VisibilityOfElement;
import dev.qadenz.automation.conditions.impl.VisibilityOfElements;
import dev.qadenz.automation.expectations.Expectation;
import dev.qadenz.automation.expectations.ListExpectation;
import dev.qadenz.automation.expectations.NumericExpectation;
import dev.qadenz.automation.expectations.TemporalExpectation;
import dev.qadenz.automation.ui.Locator;
import dev.qadenz.automation.ui.LocatorGroup;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A collection of {@link Condition}s to be used for validation or synchronization operations.
 *
 * @author Tim Slifer
 */
public class Conditions {
    
    /**
     * A Condition to evaluate the value of an attribute on an element.
     *
     * @param locator The mapped UI element.
     * @param attributeName The name of the attribute to be evaluated.
     * @param expectation The expectation for the attribute value.
     *
     * @return The Condition.
     */
    public static Condition attributeOfElement(Locator locator, String attributeName, Expectation<String> expectation) {
        return new AttributeOfElement(locator, attributeName, expectation);
    }
    
    /**
     * A Condition to evaluate the number of times an element appears on a page.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the number of instances of the element.
     *
     * @return The Condition.
     */
    public static Condition countOfElement(Locator locator, NumericExpectation<Integer> expectation) {
        return new CountOfElement(locator, expectation);
    }
    
    /**
     * A Condition to evaluate the value of a CSS property on an element.
     *
     * @param locator The mapped UI element.
     * @param cssPropertyName The name of the property to be evaluated.
     * @param expectation The expectation for the property value.
     *
     * @return The Condition.
     */
    public static Condition cssPropertyOfElement(Locator locator, String cssPropertyName,
            Expectation<String> expectation) {
        return new CssPropertyOfElement(locator, cssPropertyName, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on
     * the DOM.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition directTextOfElement(Locator locator, Expectation<String> expectation) {
        return new DirectTextOfElement(locator, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on
     * the DOM, as a formatted LocalDate.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition directTextOfElementAsDate(Locator locator, DateTimeFormatter dateTimeFormatter,
            TemporalExpectation<LocalDate> expectation) {
        return new DirectTextOfElementAsDate(locator, dateTimeFormatter, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on
     * the DOM, as a formatted LocalDateTime.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition directTextOfElementAsDateTime(Locator locator, DateTimeFormatter dateTimeFormatter,
            TemporalExpectation<LocalDateTime> expectation) {
        return new DirectTextOfElementAsDateTime(locator, dateTimeFormatter, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on
     * the DOM, as a formatted LocalTime.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition directTextOfElementAsTime(Locator locator, DateTimeFormatter dateTimeFormatter,
            TemporalExpectation<LocalTime> expectation) {
        return new DirectTextOfElementAsTime(locator, dateTimeFormatter, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on
     * the DOM, as a formatted Double.
     *
     * @param locator The mapped UI element.
     * @param decimalFormat The expected numeric format.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition directTextOfElementAsDouble(Locator locator, DecimalFormat decimalFormat,
            NumericExpectation<Double> expectation) {
        return new DirectTextOfElementAsDouble(locator, decimalFormat, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element, excluding the text of any descendant elements on
     * the DOM, as a formatted Integer.
     *
     * @param locator The mapped UI element.
     * @param numberFormat The expected numeric format.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition directTextOfElementAsInteger(Locator locator, NumberFormat numberFormat,
            NumericExpectation<Integer> expectation) {
        return new DirectTextOfElementAsInteger(locator, numberFormat, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of each instance of an element, excluding the text of any
     * descendant elements on the DOM, as an ordered list.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in each instance of the element.
     *
     * @return The Condition.
     */
    public static Condition directTextOfListElementsInOrder(Locator locator, ListExpectation expectation) {
        return new DirectTextOfListElementsInOrder(locator, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of each instance of an element, excluding the text of any
     * descendant elements on the DOM, as an unordered list.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in each instance of the element.
     *
     * @return The Condition.
     */
    public static Condition directTextOfListElementsUnordered(Locator locator, ListExpectation expectation) {
        return new DirectTextOfListElementsUnordered(locator, expectation);
    }
    
    /**
     * A Condition for evaluating element to be enabled.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether the element is to be enabled.
     *
     * @return The Condition.
     */
    public static Condition enabledStateOfElement(Locator locator, Expectation<Boolean> expectation) {
        return new EnabledStateOfElement(locator, expectation);
    }
    
    /**
     * A Condition for evaluating whether a group of elements is enabled.
     *
     * @param locatorGroup The mapped UI elements.
     * @param expectation The expectation of whether each element is to be enabled.
     *
     * @return The Condition.
     */
    public static Condition enabledStateOfElements(LocatorGroup locatorGroup, Expectation<Boolean> expectation) {
        return new EnabledStateOfElements(locatorGroup, expectation);
    }
    
    /**
     * A Condition for evaluating whether a JavaScript Alert is present.
     *
     * @param expectation The expectation of whether the alert is to be present.
     *
     * @return The Condition.
     */
    public static Condition presenceOfAlert(Expectation<Boolean> expectation) {
        return new PresenceOfAlert(expectation);
    }
    
    /**
     * A Condition for evaluating whether an element is present on the DOM, regardless of if the element is visible on
     * the page.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether the element is to be present.
     *
     * @return The Condition.
     */
    public static Condition presenceOfElement(Locator locator, Expectation<Boolean> expectation) {
        return new PresenceOfElement(locator, expectation);
    }
    
    /**
     * A Condition for evaluating whether a group of elements is present on the DOM, regardless of if the elements are
     * visible on the page.
     *
     * @param locatorGroup The mapped UI elements.
     * @param expectation The expectation of whether each element is to be present.
     *
     * @return The Condition.
     */
    public static Condition presenceOfElements(LocatorGroup locatorGroup, Expectation<Boolean> expectation) {
        return new PresenceOfElements(locatorGroup, expectation);
    }
    
    /**
     * A Condition for evaluating the currently selected option of a Select menu element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the option to be selected.
     *
     * @return The Condition.
     */
    public static Condition selectedMenuOption(Locator locator, Expectation<String> expectation) {
        return new SelectedMenuOption(locator, expectation);
    }
    
    /**
     * A Condition for evaluating each of the currently selected options of a Select menu element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in each option.
     *
     * @return The Condition.
     */
    public static Condition selectedMenuOptions(Locator locator, ListExpectation expectation) {
        return new SelectedMenuOptions(locator, expectation);
    }
    
    /**
     * A Condition for evaluating an element to be selected. This applies only elements such as checkboxes, radio
     * options, and {@code <option>} child of a {@code <select>} elements.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether the element is to be selected.
     *
     * @return The Condition.
     */
    public static Condition selectedStateOfElement(Locator locator, Expectation<Boolean> expectation) {
        return new SelectedStateOfElement(locator, expectation);
    }
    
    /**
     * A Condition for evaluating the text shown on a JavaScript Alert.
     *
     * @param expectation The expectation for the text to be shown in the alert.
     *
     * @return The Condition.
     */
    public static Condition textOfAlert(Expectation<String> expectation) {
        return new TextOfAlert(expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition textOfElement(Locator locator, Expectation<String> expectation) {
        return new TextOfElement(locator, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element as a formatted LocalDate.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition textOfElementAsDate(Locator locator, DateTimeFormatter dateTimeFormatter,
            TemporalExpectation<LocalDate> expectation) {
        return new TextOfElementAsDate(locator, dateTimeFormatter, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element as a formatted LocalDateTime.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition textOfElementAsDateTime(Locator locator, DateTimeFormatter dateTimeFormatter,
            TemporalExpectation<LocalDateTime> expectation) {
        return new TextOfElementAsDateTime(locator, dateTimeFormatter, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element as a formatted LocalTime.
     *
     * @param locator The mapped UI element.
     * @param dateTimeFormatter The expected date format.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition textOfElementAsTime(Locator locator, DateTimeFormatter dateTimeFormatter,
            TemporalExpectation<LocalTime> expectation) {
        return new TextOfElementAsTime(locator, dateTimeFormatter, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element as a formatted Double.
     *
     * @param locator The mapped UI element.
     * @param decimalFormat The expected numeric format.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition textOfElementAsDouble(Locator locator, DecimalFormat decimalFormat,
            NumericExpectation<Double> expectation) {
        return new TextOfElementAsDouble(locator, decimalFormat, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of an element as a formatted Integer.
     *
     * @param locator The mapped UI element.
     * @param numberFormat The expected numeric format.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition textOfElementAsInteger(Locator locator, NumberFormat numberFormat,
            NumericExpectation<Integer> expectation) {
        return new TextOfElementAsInteger(locator, numberFormat, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of each instance of an element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in each instance of the element.
     *
     * @return The Condition.
     */
    public static Condition textOfElements(Locator locator, Expectation<String> expectation) {
        return new TextOfElements(locator, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of each instance of an element as an ordered list.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in each instance of the element.
     *
     * @return The Condition.
     */
    public static Condition textOfListedElementsInOrder(Locator locator, ListExpectation expectation) {
        return new TextOfListElementsInOrder(locator, expectation);
    }
    
    /**
     * A Condition to evaluate the visible inner text of each instance of an element as an unordered list.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in each instance of the element.
     *
     * @return The Condition.
     */
    public static Condition textOfListedElementsUnordered(Locator locator, ListExpectation expectation) {
        return new TextOfListElementsUnordered(locator, expectation);
    }
    
    /**
     * A Condition to evaluate the values of each {@code <option>} child of a {@code <select>} element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in each option.
     *
     * @return The Condition.
     */
    public static Condition textOfSelectMenuOptions(Locator locator, ListExpectation expectation) {
        return new TextOfSelectMenuOptions(locator, expectation);
    }
    
    /**
     * A Condition to evaluate the text shown inside an {@code <input>} element.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation for the text to be shown in the element.
     *
     * @return The Condition.
     */
    public static Condition textOfInputElement(Locator locator, Expectation<String> expectation) {
        return new TextOfInputElement(locator, expectation);
    }
    
    /**
     * A Condition for evaluating the visibility of an element. An element determined to be visible is present on the
     * DOM, has a height and width greater than zero, and is not styled to be hidden.
     *
     * @param locator The mapped UI element.
     * @param expectation The expectation of whether the element is to be visible.
     *
     * @return The Condition.
     */
    public static Condition visibilityOfElement(Locator locator, Expectation<Boolean> expectation) {
        return new VisibilityOfElement(locator, expectation);
    }
    
    /**
     * A Condition to evaluate the visibility of a group of elements. An element determined to be visible is present on
     * the DOM, has a height and width greater than zero, and is not styled to be hidden.
     *
     * @param locatorGroup The mapped UI elements.
     * @param expectation The expectation of whether each element is to be visible.
     *
     * @return The Condition.
     */
    public static Condition visibilityOfElements(LocatorGroup locatorGroup, Expectation<Boolean> expectation) {
        return new VisibilityOfElements(locatorGroup, expectation);
    }
}
