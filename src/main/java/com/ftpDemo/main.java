package com.ftpDemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Newx on 2017/8/24.
 */
public class main {

    private static Logger log = Logger.getLogger(main.class);

    public static void main(String[] args) throws IOException {
//        String tempStr = "";
//        for (int i = 0; i  < 5; i++){
//            tempStr = tempStr + "第" + i + "次测试";
//            tempStr = tempStr + "\n";
//        }
//        String localPath = "D://heliostest/";
//        String fileName = getFileName("_365", "erp");
//        WriteFTPFile writeFTPFile = new WriteFTPFile();
//        writeFTPFile.write(fileName, tempStr, localPath);
//        FavFTPUtil favFTPUtil = new FavFTPUtil();
//        Boolean flag = favFTPUtil.uploadFileFromProduction("192.168.15.1", 21, "123456", "123456", "/erp/", localPath + fileName);
//        System.out.println(flag);
//        archiveFile("D://heliostest/erp_365.txt", "D://helios/erp/erp_365.txt");


//        FavFTPUtil favFTPUtil = new FavFTPUtil();
//        Boolean flag = favFTPUtil.downloadFileList("192.168.15.1", 21, "123456", "123456", "/paymentStatus", "D://heliostest/paymentStatus");

        List<String[]> list = getList();
        for (String[] strArr:list){
            if (strArr[0].equals("D")) {
                System.out.println("businessCode: " + strArr[1]);
                System.out.println("status: " + strArr[3]);
            }
        }

    }

    private static String getFileName(String companyId, String tag) {
        String fileName = tag + companyId;
        return fileName + ".txt";
    }

    public static void archiveFile(String localFileName, String archiveFileName) {
        File file = new File(localFileName);
        if (file.exists()) {
            File archiveFile = new File(archiveFileName);
            System.out.println("archiveFileName:　" + archiveFileName);
            file.renameTo(archiveFile);
        }
    }

    private static List<String[]> getList() {
        String localPath = "D://heliostest/paymentStatus";
        File root = new File(localPath);
        File[] files = root.listFiles();
        List list = new LinkedList<>();
        for (File file : files) {
            try {
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String str = "";
                while ((str = br.readLine()) != null) {
                    String temp = str.replaceAll("\"", "");
                    String[] tempArray = temp.split(",");
                    System.out.println("String[]: " + tempArray);
                    list.add(tempArray);
                }
                br.close();
                isr.close();
                fis.close();
                String archivePath = "D://heliostest/archive/paymentStatus";
                log.info("==============待归档文件地址:" + localPath + "/" + file.getName());
                log.info("==============文档归档地址：" + archivePath + "/" + file.getName());
                archiveFile(localPath + "/" + file.getName(), archivePath + "/" + file.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean write(String fileName, String fileContext,
                                String writeTempFielPath, String enCoding) {
        try {
            log.info("开始写本地数据文件");
            File f = new File(writeTempFielPath + "/" + fileName);
            if (!f.exists()) {
                if (!f.createNewFile()) {
                    log.info("文件不存在，创建失败!");
                }
            }
            FileOutputStream fos = new FileOutputStream(f, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, enCoding);
            osw.write(fileContext.replaceAll("\n", "\r\n"));
            osw.flush();
            osw.close();
            return true;
        } catch (Exception e) {
            log.error("写文件没有成功");
            e.printStackTrace();
            return false;
        }
    }

}