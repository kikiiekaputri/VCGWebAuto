public class ObsoleteBeliLangsungCase {
    public static void main(String[] args) {

        ObsoleteBeliLangsungWeb blw = new ObsoleteBeliLangsungWeb();

        blw.doLogin("buyerb@mailinator.com","ciwkiciw");

        String catatan = "testing, 123.";
        int qty = 2;
        String[] pinDigits = {"1","2","3","4","5","6"};
        blw.doBeliLangsung(catatan,qty,pinDigits);
        blw.tearDown();
    }
}
