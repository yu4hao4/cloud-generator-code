package cn.generator.util;//package cn.generator.util;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.OutputFormat;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.XMLWriter;
//
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author 喻浩
// * @create 2020-05-26-7:28
// */
//public class XmlUtil {
//    /**如果没打包后运行则debug为true*/
//    public static boolean debug = false;
//
//    /**
//     * 用以解析xml文件的类
//     */
//    private SAXReader reader = new SAXReader();
//    /**
//     * 解析xml文件，放入到 document 对象中
//     */
//    private Document document;
//
//    /**
//     * xml 文件读取
//     */
//    private File file = new File(initProjectPathAndDebug()+"/PatientInfo.xml");
//
//    /**
//     * dom4j 加载xml文件
//     */
//    {
////        File file = new File("src/PatientInfo.xml");
//        try {
//            document = reader.read(file);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 获得路径位置
//     * @return
//     */
//    private static String initProjectPathAndDebug(){
//        java.net.URL url = PatientInfoDao.class.getProtectionDomain().getCodeSource().getLocation();
//        String filePath = null;
//        try {
//            filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (filePath.endsWith(".jar"))  {
//            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
//        }
//        //如果以bin目录接，则说明是开发过程debug测试查询，返回上一层目录
//        if (filePath.endsWith("bin/") || filePath.endsWith("bin\\") )  {
//            debug = true;
//            filePath = filePath.substring(0, filePath.lastIndexOf("bin"));
//        }
//        java.io.File file = new java.io.File(filePath);
//        filePath = file.getAbsolutePath();
//        return filePath;
//    }
//
//    /**
//     * 将病人信息按照指定格式导入到txt文件中
//     * @param fileName
//     * @return
//     * @throws IllegalAccessException
//     * @throws IOException
//     * @throws InstantiationException
//     * @throws DocumentException
//     * @throws InvocationTargetException
//     * @throws ClassNotFoundException
//     */
//    public boolean exportPatientInfo(String fileName) throws IllegalAccessException, IOException, InstantiationException, DocumentException, InvocationTargetException, ClassNotFoundException {
//        boolean bool = false;
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
//        List<Patient> allPatientInfo = getAllPatientInfo();
////        for(Patient patient : allPatientInfo){
////            bw.write("------------------------------------------");
////            bw.newLine();
////            bw.write("编号："+patient.getId());
////            bw.newLine();
////            bw.write("就诊日期："+patient.getConsultationDate());
////            bw.newLine();
////            bw.write("姓名："+patient.getName());
////            bw.newLine();
////            bw.write("性别："+patient.getSex());
////            bw.newLine();
////            bw.write("年龄："+patient.getAge());
////            bw.newLine();
////            bw.write("电话："+patient.getTelephone());
////            bw.newLine();
////            bw.write("医生："+patient.getDoctor());
////            bw.newLine();
////            bw.write("诊断："+patient.getDiagnose());
////            bw.newLine();
////            bw.write("肺功能："+patient.getPulmonaryFunction());
////            bw.newLine();
////            bw.write("FENO检查："+patient.getCheckFENO());
////            bw.newLine();
////            bw.write("支气管舒张试验："+patient.getTestBronchodilation());
////            bw.newLine();
////            bw.write("血常规："+patient.getTestRoutineBlood());
////            bw.newLine();
////            bw.write("其他检查："+patient.getTestOther());
////            bw.newLine();
////            bw.write("COPD分级："+patient.getClassificationCOPD());
////            bw.newLine();
////            bw.write("用药情况："+patient.getUseDrug());
////            bw.newLine();
////            bw.write("就诊次数："+patient.getVisitsNumber());
////            bw.newLine();
////            bw.write("------------------------------------------");
////            bw.newLine();
////        }
//        //刷新并关闭流将内容写入文件中
//        bw.flush();
//        bw.close();
//        bool = true;
//        return bool;
//    }
//
//    /**
//     * 修改病人访问次数，根据姓名和电话，唯一确定一个病人
//     * 在每次查询的时候，修改他的就诊次数
//     * @param name  姓名
//     * @param telephone 电话
//     * @return
//     * @throws IllegalAccessException
//     * @throws DocumentException
//     * @throws IOException
//     * @throws InstantiationException
//     * @throws ParserConfigurationException
//     * @throws InvocationTargetException
//     * @throws ClassNotFoundException
//     */
//    public void modifyPatientInfo(String name,String telephone) throws IllegalAccessException, DocumentException, IOException, InstantiationException, ParserConfigurationException, InvocationTargetException, ClassNotFoundException {
//        document = reader.read(file);
//        //获得hosiptal根节点
//        Element rootElement = document.getRootElement();
//        //获得hosiptal的子节点
//        List<Element> elements = rootElement.elements();
//        //遍历hosiptal的子节点 patient
////        for(Element e : elements ){
////            //获得每个patient下的子节点
////            List<Element> patientInfoElments = e.elements();
////            //遍历每个patient下的子节点  也就是每个patient的录入信息
////            for(Element patientInfo : patientInfoElments){
////                String pName = patientInfo.getName();
////                String pValue = patientInfo.getText();
////                // 判断当前patient的姓名是否符合
////                if(pName.toLowerCase().contains("name") && pValue.equals(name)){
////                    //再次遍历当前patient，判断电话号码是否相等
////                    for(Element patientInfo1 : patientInfoElments){
////                        String paName = patientInfo1.getName();
////                        String paValue = patientInfo1.getText();
////                        if(paName.toLowerCase().contains("telephone") && paValue.equals(telephone)){
////                            //再次遍历当前patient，让就诊次数加一
////                            for(Element patientInfo2 : patientInfoElments){
////                                String patiName = patientInfo2.getName();
////                                String patiValue = patientInfo2.getText();
////                                if(patiName.toLowerCase().contains("visit")){
////                                    int result = Integer.parseInt(patiValue) + 1;
////                                    patientInfo2.setText(String.valueOf(result));
////                                    writeFile();
////                                    return;
////                                }
////                            }
////                        }
////                    }
////                }
////            }
////        }
//    }
//
//
//    /**
//     * 根据姓名和电话查找对应的病人
//     * @param name
//     * @param telephone
//     * @return
//     * @throws Exception
//     */
//    public Patient searchPatient(String name,String telephone) throws Exception {
//        deletePatient();
//        Patient patient = null;
//        List<Patient> allPatientInfo = getAllPatientInfo();
//        for(Patient p : allPatientInfo){
//            if(name.equals(p.getName()) && telephone.equals(p.getTelephone())){
//                patient = p;
//                //修改病人的就诊次数
//                modifyPatientInfo(name, telephone);
//                break;
//            }
//        }
//        return patient;
//    }
//
//
//    /**
//     * 添加用户到xml文件中
//     * @param patient   病人对象
//     * @return
//     * @throws IOException
//     * @throws ClassNotFoundException
//     * @throws IllegalAccessException
//     * @throws InstantiationException
//     * @throws InvocationTargetException
//     */
//    public boolean addPatient(Patient patient) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
//        boolean bool = false;
//        Element rootElement = document.getRootElement();
//        Element newPatient = rootElement.addElement("patient");
//        //设置 节点内的属性
////        newPatient.addAttribute("id", "1");
////        Patient patient = new Patient("1", "1", "1", Integer.parseInt("1"), "1","1", "1", "1", "1", "1", "1", "1", "1", "1", "1", Integer.parseInt("1"));
//        Class clazz = Class.forName("disease.beans.Patient");
//        Object instance = patient;
//        Method[] methods = clazz.getMethods();
//        for(Method m : methods){
//            if(m.getName().contains("get") && !m.getName().equals("getClass")){
//                Object invoke = m.invoke(instance);
//                Element element = newPatient.addElement("patient-"+m.getName().substring(3,4).toLowerCase()+m.getName().substring(4));
//                element.setText(String.valueOf(invoke));
//            }
//        }
//        System.out.println(rootElement.getName());
//        writeFile();
//        bool = true;
//        return bool;
//    }
//
//    /**
//       * 输出为文件
//       * @param doc
//       * @throws IOException
//       */
//    public void writeFile()throws IOException {
//        FileOutputStream osWrite=new FileOutputStream(initProjectPathAndDebug()+"/PatientInfo.xml");//创建输出流
//        //获取输出的指定格式
//        OutputFormat format = OutputFormat.createPrettyPrint();
//        // 设置是否缩进
//        format.setIndent(true);
//        // 以四个空格方式实现缩进
//        format.setIndent("    ");
//        // 设置是否换行
//        format.setNewlines(true);
//        //设置编码 ，确保解析的xml为UTF-8格式
//        format.setEncoding("UTF-8");
//        // //XMLWriter 指定输出文件以及格式
//        XMLWriter writer = new XMLWriter(osWrite,format);
//        //把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)
//        writer.write(document);
//        writer.flush();
//        writer.close();
//    }
//}
