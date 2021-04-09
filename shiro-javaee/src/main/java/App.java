import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class App {
    public static void main(String[] args) {
        String userName = "root";
        String password = "admin";

        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        System.out.println(subject.hasRole("admin"));
        System.out.println(subject.isPermitted("shop:clean-house"));

    }
}
