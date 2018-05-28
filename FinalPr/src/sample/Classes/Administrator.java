package sample.Classes;


/**
 * Created by FU YUANQING on 5/17/2018.
 */
public class Administrator {
              private  Longin admminisid;
              private  String name;
              private Longin email;
              private Longin password;

    public Administrator(Longin admminisid, String name, Longin email, Longin password) {
        this.admminisid = admminisid;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Longin getAdmminisid() {
        return admminisid;
    }

    public void setAdmminisid(Longin admminisid) {
        this.admminisid = admminisid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Longin getEmail() {
        return email;
    }

    public void setEmail(Longin email) {
        this.email = email;
    }

    public Longin getPassword() {
        return password;
    }

    public void setPassword(Longin password) {
        this.password = password;
    }
}

