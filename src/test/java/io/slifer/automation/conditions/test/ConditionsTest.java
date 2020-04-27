package io.slifer.automation.conditions.test;

import io.slifer.automation.commands.BrowserCommands;
import io.slifer.automation.conditions.Condition;
import io.slifer.automation.conditions.Conditions;
import io.slifer.automation.conditions.Expectations;
import io.slifer.automation.config.AutomatedTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Testing Conditions class.
 *
 * @author Tim Slifer
 */
public class ConditionsTest extends AutomatedTest {
    
    @Test
    public void test_AttributeOfElement_InvalidChars() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Condition condition =
                Conditions.attributeOfElement(HerokuPage.checkbox1(), "type", Expectations.isEqualTo("text"));
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_AttributeOfElement_ValidChars() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Condition condition =
                Conditions.attributeOfElement(HerokuPage.checkbox1(), "type", Expectations.isEqualTo("checkbox"));
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_CountOfElement_InvalidNumber() {
        Condition condition = Conditions.countOfElement(HerokuPage.exampleLinks(), Expectations.isEqualTo(1));
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_CountOfElement_ValidNumber() {
        Condition condition = Conditions.countOfElement(HerokuPage.exampleLinks(), Expectations.isEqualTo(44));
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_EnabledStateOfElement_ElementDisabled() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Scripts.disableCheckboxes();
        Condition condition = Conditions.enabledStateOfElement(HerokuPage.checkbox1(), Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_EnabledStateOfElement_ElementEnabled() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Condition condition = Conditions.enabledStateOfElement(HerokuPage.checkbox1(), Expectations.isTrue());
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_EnabledStateOfElements_ElementsDisabled() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Scripts.disableCheckboxes();
        Condition condition = Conditions.enabledStateOfElements(HerokuPage.checkboxes(), Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_EnabledStateOfElements_ElementsEnabled() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Condition condition = Conditions.enabledStateOfElements(HerokuPage.checkboxes(), Expectations.isTrue());
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_PresenceOfAlert_AlertNotPresent() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("JavaScript Alerts"));
        Condition condition = Conditions.presenceOfAlert(Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_PresenceOfAlert_AlertPresent() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("JavaScript Alerts"));
        commands.click(HerokuPage.jsAlertButton());
        Condition condition = Conditions.presenceOfAlert(Expectations.isTrue());
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_PresenceOfElement_ElementNotPresent() {
        Condition condition = Conditions.presenceOfElement(HerokuPage.jsAlertButton(), Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_PresenceOfElement_ElementPresent() {
        Condition condition = Conditions.presenceOfElement(HerokuPage.pageFooter(), Expectations.isTrue());
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_PresenceOfElements_ElementsNotPresent() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Dropdown"));
        Condition condition = Conditions.presenceOfElements(HerokuPage.exampleLinksGroup(), Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_PresenceOfElements_ElementsPresent() {
        Condition condition = Conditions.presenceOfElements(HerokuPage.exampleLinksGroup(), Expectations.isTrue());
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_SelectedMenuOption_OptionNotSelected() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Dropdown"));
        Condition condition =
                Conditions.selectedMenuOption(HerokuPage.dropdownMenu(), Expectations.isEqualTo("Option 1"));
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_SelectedMenuOption_OptionSelected() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Dropdown"));
        commands.select(HerokuPage.dropdownMenu(), "Option 1");
        Condition condition =
                Conditions.selectedMenuOption(HerokuPage.dropdownMenu(), Expectations.isEqualTo("Option 1"));
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_SelectedStateOfElement_ElementNotSelected() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Condition condition = Conditions.selectedStateOfElement(HerokuPage.checkbox1(), Expectations.isFalse());
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_SelectedStateOfElement_ElementSelected() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Condition condition = Conditions.selectedStateOfElement(HerokuPage.checkbox2(), Expectations.isTrue());
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_TextOfAlert_InvalidChars() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("JavaScript Alerts"));
        commands.click(HerokuPage.jsAlertButton());
        Condition condition = Conditions.textOfAlert(Expectations.isEqualTo("Alert"));
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_TextOfAlert_ValidChars() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("JavaScript Alerts"));
        commands.click(HerokuPage.jsAlertButton());
        Condition condition = Conditions.textOfAlert(Expectations.isEqualTo("I am a JS Alert"));
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_TextOfElement_InvalidChars() {
        Condition condition = Conditions.textOfElement(HerokuPage.pageFooter(), Expectations.isEqualTo("Lorem Ipsum"));
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_TextOfElement_ValidChars() {
        Condition condition =
                Conditions.textOfElement(
                        HerokuPage.pageFooter(), Expectations.isEqualTo("Powered by Elemental Selenium"));
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_TextOfElements_InvalidChars() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("JavaScript Alerts"));
        Condition condition = Conditions.textOfElements(HerokuPage.jsAlertButtons(), Expectations.startsWith("Alert"));
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_TextOfElements_ValidChars() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("JavaScript Alerts"));
        Condition condition = Conditions.textOfElements(HerokuPage.jsAlertButtons(), Expectations.startsWith("Click"));
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_VisibilityOfElement_ElementNotVisible_NotPresent() {
        Condition condition = Conditions.visibilityOfElement(HerokuPage.loadedImage(), Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_VisibilityOfElement_ElementNotVisible_ZeroDimension() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Broken Images"));
        Scripts.hideLoadedImageByZeroHeight();
        Condition condition = Conditions.visibilityOfElement(HerokuPage.loadedImage(), Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_VisibilityOfElement_ElementNotVisible_StyleDisplayNone() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Scripts.hideCheckboxesByDisplayNone();
        Condition condition = Conditions.visibilityOfElement(HerokuPage.checkbox1(), Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_VisibilityOfElement_ElementNotVisible_StyleVisibilityHidden() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Scripts.hideCheckboxesByVisibilityHidden();
        Condition condition = Conditions.visibilityOfElement(HerokuPage.checkbox1(), Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_VisibilityOfElement_ElementNotVisible_AngularDirectiveHidden() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Scripts.hideCheckboxesByAngularDirective();
        Condition condition = Conditions.visibilityOfElement(HerokuPage.checkbox1(), Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_VisibilityOfElement_ElementVisible() {
        Condition condition = Conditions.visibilityOfElement(HerokuPage.pageFooter(), Expectations.isTrue());
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_VisibilityOfElements_ElementsNotVisible() {
        BrowserCommands commands = new BrowserCommands();
        commands.click(HerokuPage.exampleLink("Checkboxes"));
        Condition condition = Conditions.visibilityOfElements(HerokuPage.exampleLinksGroup(), Expectations.isTrue());
        Assert.assertFalse(condition.result(), condition.description() + condition.output());
    }
    
    @Test
    public void test_VisibilityOfElements_ElementsVisible() {
        Condition condition = Conditions.visibilityOfElements(HerokuPage.exampleLinksGroup(), Expectations.isTrue());
        Assert.assertTrue(condition.result(), condition.description() + condition.output());
    }
}
