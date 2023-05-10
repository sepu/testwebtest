package pages;

public class WebTestPage extends BasePage { // Esta pagina hereda de BasePage

    private String chartsOption = "//body/div[@id='wrapper']/nav[1]/div[2]/div[1]/ul[1]/li[3]/a[1]";
    private String tablesOption = "//body/div[@id='wrapper']/nav[1]/div[2]/div[1]/ul[1]/li[4]/a[1]";
    private String formsOption = "//body/div[@id='wrapper']/nav[1]/div[2]/div[1]/ul[1]/li[5]/a[1]";
    private String textInput = "//body/div[@id='wrapper']/div[@id='page-wrapper']/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/input[1]";
    private String textArea = "//body/div[@id='wrapper']/div[@id='page-wrapper']/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[5]/textarea[1]";
    
    public WebTestPage (){ // Crear un super constructor porque una interfaz está extendiendo otra interfaz
        super(driver); // Usa la instancia que se creó en BasePage cuando usemos esta clase
    }

    public void navigateToWebTest(){
        navigateTo("http://localhost");
    }

    public void clickCharts(){
        clickElement(chartsOption);
    }

    public void clickTables(){
        clickElement(tablesOption);
    }

    public void clickForms(){
        clickElement(formsOption);
    }

    public void enterTerm(String term){
        write(textInput, term);
    }

    public void enterPhrase(String phrase){
        write(textArea, phrase);
    }
    
}
