package com.example.minispingboot;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class TMiniSpringboot //extends HttpServlet
{
    private Map<String, Object> m_ioc = new HashMap<String, Object>();
    private List<String> m_FileNameLst = new ArrayList<String>();
    private List<String> m_ClassNameLst = new ArrayList<String>();

    private Properties m_properties = new Properties();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
    }


    public void init(/*ServletConfig config*/) throws ServletException {
        //super.init(config);
        //读取配置文件
        LoadConfigFile();
        //读取指定文件夹下的文件名
        String PackageName = this.getClass().getPackageName();
        String PackagePath = PackageName.replaceAll("\\.", "/");
        FindFileNameFromPath(PackagePath);
        //从将文件名列表得到类名称的列表
        ScannerClassName();
        //加载所有的类
        
        //创建所有类的单实例
        //反向注入
        //切面处理
        //初始化命令分发器
    }

    private void ScannerClassName() {
        for(String s: m_FileNameLst){
            if(s.endsWith(".class")) {
                String ClassName = s.replace(".class", "");
                ClassName = ClassName.replaceAll("/", "\\.");
                m_ClassNameLst.add(ClassName);
            }
        }
    }

    private void FindFileNameFromPath(String path) {
        URL url =this.getClass().getClassLoader().getResource(path);
        File classPath = new File(url.getFile());
        for(File f: classPath.listFiles()){
            if(f.isDirectory()){
                FindFileNameFromPath(path + "/" + f.getName());
            }
            else {
                m_FileNameLst.add( path + "/" + f.getName());
            }
        }
    }

    private void LoadConfigFile()  {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream("application.properties");

            m_properties.load(is);

        }catch (IOException ex){
            ex.printStackTrace();
        }
        try {
            is.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
