import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class TMain {
    static final int port = 9080;
    static final String docBase = "c:\\";
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(docBase);
        tomcat.getHost().setAutoDeploy(false);

        String contextPath = "/book";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());
        tomcat.getHost().addChild(context);

        tomcat.addServlet(contextPath, "homeServlet", new TMySpringBoot());
        context.addServletMappingDecoded("/hello", "homeServlet");
        tomcat.start();
        tomcat.getServer().await();
    }
}
