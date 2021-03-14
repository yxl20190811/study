


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class TMySpringBoot  extends HttpServlet {

    private Properties m_properties = new Properties();
    private Map<String, Object> m_ioc = new HashMap<String, Object>();
    private Map<String, Method> m_HandMapping;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            doRealDispatch(req, resp);
        }catch (IOException ex){
            resp.getWriter().write("500: Exception Detail: " + Arrays.toString(ex.getStackTrace()));
        }
    }

    private void doRealDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("request scheme: " + req.getScheme());
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url.replaceAll(contextPath, "");
        url.replaceAll("/+", "/");
        Method m = m_HandMapping.get(url);
        if(null == m){
            resp.getWriter().print("404, Not Find");
            return;
        }
        String beanName = m.getDeclaringClass().getSimpleName();
        Change2BeanNam(beanName);
        Object oThis = m_ioc.get(beanName);
        if(null == oThis){
            resp.getWriter().print("404, Not Find");
            return;
        }
        Map<String, String[]> params = req.getParameterMap();
        try {
            m.invoke(oThis, new Object[]{req, resp, params.get("name")[0]});
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        resp.getWriter().print("404, Not Find");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //读取配置文件
        try {
            LoadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //找到包目录
        String PackageName = this.getClass().getPackageName();
        String PackagePath = PackageName.replaceAll("\\.", "/");
        //如果配置文件中有配置上层目录，则将上层目录加到包目录中
        String ScannerDir = m_properties.getProperty("ScannerDir");
        if(null != ScannerDir){
           PackagePath = PackagePath + "/" + ScannerDir + "/";
        }
        PackagePath = PackagePath + "/" + ".." + "/";
        PackagePath = PackagePath.replaceAll("/+", "/");

        //遍历包的上层目录下的所有的文件, 得到java类文件名类表
        List<String> FileNameLst = new ArrayList<String>();
        //FindFileFromPath((PackagePath, FileNameLst);
        //FindFileFromPath();
        List<String> ClassFileNameLst = new ArrayList<String>();
        FindClasFileName(FileNameLst, ClassFileNameLst);
        //反向映射所有的类，对于注解是MyRestController， MyService的类就作为单实例加入ioc列表中
        //遍历ioc的MyRestController对象的方法，对于MyGetMapping注释的方法， 放到“命令分发映射表中”
        LoadIoc(ClassFileNameLst);
        //遍历ioc的对象的变量， 对于其中有需要注入的变量，则注入
        DoAutowired();
        //遍历@MyAspect对象的方法，对于MyPointcut方法，找到注解内容对应的类， 将该类使用ProxyHandler<T>的实例，替代其单例对象；
        //  并且遍历@xxx的方法， 将其注册为，被且方法的替代方法
        DoRestController();
    }

    private void DoRestController() {
        for(Map.Entry<String, Object> it:m_ioc.entrySet()) {
            Class<?> clazz = it.getValue().getClass();
            if(clazz.isAnnotationPresent(MyRestController.class)){
                continue;
            }
            String BaseUrl = "";
            MyRequestMapping myR = clazz.getAnnotation(MyRequestMapping.class);
            if(null != myR){
                BaseUrl = BaseUrl + myR.value();
            }

            Method[] ms = clazz.getMethods();
            for(Method m: ms){
                MyGetMapping g = m.getAnnotation(MyGetMapping.class);
                if(null == g){
                    continue;
                }
                String url = "/" + BaseUrl + "/" + g.value() + "/";
                url.replaceAll("/+", "/");
                m_HandMapping.put(url, m);
                System.out.println("Mapping:" + url + "->" + m);
            }
        }
    }

    private void DoAutowired() {
        for(Map.Entry<String, Object> it:m_ioc.entrySet()){
            Field[] fields = it.getValue().getClass().getDeclaredFields();
            for(Field f:fields){
                if(!f.isAnnotationPresent(MyAutowire.class)){
                    continue;
                }
                MyAutowire n = f.getAnnotation(MyAutowire.class);
                String BeanName = n.value();
                if("".equals(BeanName)){
                    BeanName = f.getType().getSimpleName();
                }
                Change2BeanNam(BeanName);
                Object ins = m_ioc.get(BeanName);
                if(null == ins){
                    continue;
                }
                try {
                    f.set(it.getValue(), ins);
                }catch (IllegalAccessException ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private void Change2BeanNam(String ClassNam){

    }
    private void LoadIoc(List<String> ClassFileNameLst) {
        for(String ClassName: ClassFileNameLst){
            try {
                Class<?>  clazz = Class.forName(ClassName);
                if(clazz.isAnnotationPresent(MyRestController.class)) {
                    String BeanName = clazz.getSimpleName();
                    Change2BeanNam(BeanName);
                    Object ins = clazz.newInstance();
                    m_ioc.put(BeanName, ins);
                }
                else if(clazz.isAnnotationPresent(MyService.class)){
                    String BeanName = clazz.getSimpleName();
                    MyService myService = clazz.getAnnotation(MyService.class);
                    if(!"".equals(myService.value())){
                        BeanName = myService.value();
                    }
                    Change2BeanNam(BeanName);
                    Object ins = clazz.newInstance();
                    m_ioc.put(BeanName, ins);
                    //遍历clazz实现了的接口，将接口名也注册
                    for(Class<?> i:clazz.getInterfaces()){
                        String BeanName1 = i.getSimpleName();
                        Change2BeanNam(BeanName1);
                        if(m_ioc.containsKey(BeanName1)){
                            continue;
                        }
                        m_ioc.put(BeanName1, ins);
                    }

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private void LoadConfig() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        m_properties.load(is);;
        is.close();
    }

    private void FindClasFileName(List<String> FileNameLst, List<String> classFileNameLst) {
        for(String s: FileNameLst){
            if(s.endsWith(".class")) {
                String ClassName = s.replace(".class", "");
                ClassName = ClassName.replaceAll("/", "\\.");
                classFileNameLst.add(ClassName);
            }
        }
    }

    private void FindFileFromPath(String path, List<String> FileNameLst) {
        URL url =this.getClass().getClassLoader().getResource(path);
        File classPath = new File(url.getFile());
        for(File f: classPath.listFiles()){
            if(f.isDirectory()){
                FindFileFromPath(path + "/" + f.getName(), FileNameLst);
            }
            else {
                FileNameLst.add( path + "/" + f.getName());
            }
        }
    }
}
