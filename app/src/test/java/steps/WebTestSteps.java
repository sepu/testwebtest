package steps;

import io.cucumber.java.en.*;
import pages.WebTestPage;


public class WebTestSteps {

    WebTestPage webtest = new WebTestPage(); // Crear instancia de la página de Webtest
    
    @Given("^I am on webtest page$")   // Empezar con ^ y terminar con $ es para decirle que es esa frase exacta sin nada adicional
    public void navigateToWebTest(){
        webtest.navigateToWebTest();
    }

    @And("^click in Charts$")   
    public void clickCharts() throws InterruptedException{
        webtest.clickCharts();
        Thread.sleep(1000);
    }

    @And("^click in Tables$")   
    public void clickTables() throws InterruptedException{
        webtest.clickTables();
        Thread.sleep(1000);
    }

    @And("^click in Forms$")   
    public void clickForms() throws InterruptedException{
        webtest.clickForms();
        Thread.sleep(1000);
    }

    @Then("^type text in first field (.+)$")   
    public void enterTerm(String term) throws InterruptedException{
        webtest.enterTerm(term);
        Thread.sleep(1000);
    }

      


}
