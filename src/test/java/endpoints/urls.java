package endpoints;

public class urls {

    //COVID the Game -villain
   public static String LocalhostURI = "http://localhost:3000";
    public static String MainServer = "https://supervillan-81ce46d107ff.herokuapp.com";

    //user module
    public static String getuserurl = MainServer+"/v1/user/{name}";


    //PetStore
    public static String petbaseURl = "https://petstore.swagger.io/v2";

    //user module
    public static String petgetuserurl = petbaseURl+"/user/{username}";
    public static String petpostuserurl = petbaseURl+"/user";
    public static String petupdateuserurl = petbaseURl+"/user/{username}";
    public static String petdeleteuserurl = petbaseURl+"/user/{username}";

   public static String petpostuserurlwitharraylist = petbaseURl+"/user/createWithArray";

    public static String petgetuserloginurl = petbaseURl+"/user/login";


}
