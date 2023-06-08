import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ObsoleteLoginWebCases {

    public static void main(String[] args) throws IOException, ParseException {
        ConfigReader cr = new ConfigReader("src/input/sellers.json");
        String emailInput = cr.getEmail(1);
        String passwordInput = cr.getPassword(1);

        ObsoleteLoginWeb lg = new ObsoleteLoginWeb();

        //case 1 : login dengan email & password valid
        lg.doLogin("kikiiekaputri@gmail.com","ciwkiciw");
        lg.tearDown();
        //case 2 : login dengan format email tidak valid
        lg.doLogin("k","ciwkiciw");
        lg.tearDown();
        //case 3 : login dengan email valid & password TIDAK valid
        lg.doLogin("kikiiekaputri@gmail.com","ciwkiciwwww");
        lg.tearDown();
        //case 4 : login dengan email tidak terdaftar & password valid
        lg.doLogin("kikiiekaputriiii@gmail.com","ciwkiciw");
        lg.tearDown();

        System.out.println(lg.getMessages());
    }
}
