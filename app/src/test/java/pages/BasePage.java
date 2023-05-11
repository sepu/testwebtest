package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {  // Creamos la clase BasePage

    protected static WebDriver driver; // Importo y creo una sola vez el webdriver aquí y ya no tengo que importarlo mas porque se hereda
    private static WebDriverWait wait; // Creo método de espera

    static {  // Esto se ejecuta al principio de todo
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*"); // Añado este argumento para permitir al driver cargar urls externas
        driver = new ChromeDriver(chromeOptions);  // Le pasamos al driver la options
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 segundos de espera para encontrar los elementos en la pagina
    }
    
    public BasePage(WebDriver driver){  // Constructor de esta clase que instancia el webdriver
        BasePage.driver = driver;       // Cada vez que instanciemos esta pagina, carga el driver
        PageFactory.initElements(driver, this); // Aquí inicializamos los webelements de PageFactory para ser usados en cualquier sitio
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
    }

    public static void navigateTo(String url){   // Función navegar que recibe el argumento con la URL
        driver.get(url);
    }

    public static void closeBrowser(){ // Limpia el driver. Cierra el navegador. La llamamos en el Runner.
        driver.quit();
    }

    private WebElement Find(String locator) { 
        // Nos creamos nuestro propio finder de webelements con un wait, de forma que siempre va a esperar a que el elemento
        // haya cargado en la web y no tengamos que estar poniendo wait por todos sitios. es privado porque no hace falta hacerlo en cada pagina
        // lo hacemos una sola vez
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))); // siempre por XPATH para todo el framework
    }

    public void clickElement(String locator){  
        // Aquí usamos el Find definido como privado arriba, siempre va a recibir un locator XPATH 
        Find(locator).click();
    }

    public void goToLinkText(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }

    public void write(String locator, String textToWrite){ // Escribir en un campo
        Find(locator).clear();
        Find(locator).sendKeys(textToWrite);
    }

    public void selectFromDropdownByValue(String locator, String valueToSelect){
        Select dropdown = new Select (Find(locator));
        dropdown.selectByValue(valueToSelect);
    }
    
    public void selectFromDropdownByIndex(String locator, int valueToSelect){
        Select dropdown = new Select (Find(locator));
        dropdown.selectByIndex(valueToSelect);
    }  
    
    public void selectFromDropdownByText(String locator, String valueToSelect){
        Select dropdown = new Select (Find(locator));
        dropdown.selectByVisibleText(valueToSelect);
    } 

    public String getValueFromTable(String locator, int row, int column){  // Funcion para traer el valor de un elemento de una tabla dada su fila y columna
        String cellINeed = locator+"/table/tbody/tr["+row+"]/td["+column+"]";
        return Find(cellINeed).getText();
    }

    public void setValueOnTable(String locator, int row, int column, String stringToSend){ // Funcion para escribir un valor en una tabla editable data fila y columna
        String cellToFill = locator+"/table/tbody/tr["+row+"]/td["+column+"]";
        Find(cellToFill).sendKeys(stringToSend);
    }

    public void switchToiFrame(int iFrameIndex){ // Funcion que permite cambiar entre un iFrame y otro
        driver.switchTo().frame(iFrameIndex);
    }

    public void switchToParentFrame(){ // Funcion que permite ir al iFrame padre
        driver.switchTo().parentFrame();
    }

    public void dismissAlert(){ // Ejemplo de Try Catch para el control de errores.
        try{
        driver.switchTo().alert().dismiss(); // Función para rechazar una alerta popup
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }
    }

    // ASERCIONES ********************************************
    // Funcion que nos devuelve un texto. Esto es motivado por lo explicado al final de la GooglePage. Para manejar lo que devuelvan aserciones
    // Esto lo podemos usar para las aserciones 
    public String textFromElement(String locator){
        return Find(locator).getText(); // Devuelve el texto de un webElement (siempre que tenga texto, sino peta) se puede usar con assertEqual
    }

    public boolean elementIsDisplayed(String locator){ // Devuelve si un elemento esta siendo mostrado o no. se puede usar con assertTrue/assertFalse
        return Find(locator).isDisplayed();
    }

    public boolean elementEnabled(String locator){ // Devuelve si un elemento está habilitado. se puede usar con assertTrue/assertFalse
        return Find(locator).isEnabled();
    }

    public boolean elementIsSelected(String locator){ // Devuelve si un elemento está seleccionado (selects y radio buttons). se puede usar con assertTrue/assertFalse
        return Find(locator).isSelected();
    }

    // LIST DE ELEMENTOS
    public List<WebElement> bringMeAllElements(String locator){
        return driver.findElements(By.className(locator));
    }

    //Lista de webelements y click en el indicado por index
    public void clickElementFromList(String locator, int index){
        List<WebElement> results = driver.findElements(By.xpath(locator));
        results.get(index).click(); 
    } 
}
