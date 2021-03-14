
@MyRestController
public class TMyIt {
    @MyAutowire
    private TYyService m_yyService;

    @MyGetMapping("/hello")
    public String  hello(){
        return "hello";
    }
}
