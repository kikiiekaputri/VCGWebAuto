import org.openqa.selenium.WebDriver;

public class BeliLangsungCase {
    public static void main(String[] args) {

        BeliLangsungWeb blw = new BeliLangsungWeb();

        blw.doLogin("buyerb@mailinator.com","ciwkiciw");

        String catatan = "testing, 123.";
        int qty = 2;
        String[] pinDigits = {"1","2","3","4","5","6"};
        blw.doBeliLangsung(catatan,qty,pinDigits);
        blw.tearDown();
    }
}
