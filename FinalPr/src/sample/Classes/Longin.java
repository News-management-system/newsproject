package sample.Classes;

/**
 * Created by FU YUANQING on 5/17/2018.
 */
public class Longin {
    private static Longin longin;
    private  int loginid;
    private  String email;
    private String password;


        /* public Longin(int loginid,String email,int password){
             this.email=email;
             this.loginid=loginid;
             this.password=password;


         }*/
        private Longin(){

        }
        public static Longin getInstance() {
            if (longin == null) {
                longin = new Longin();
            }
            return longin;
        }

    public int getLoginid(int id) {
        return loginid;
    }

    public void setLoginid(int loginid) {
        this.loginid = loginid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
