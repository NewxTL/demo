package com.ftpDemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Newx on 2017/8/24.
 */
public class main {

    private Logger logger = Logger.getLogger(main.class);

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
        FavFTPUtil favFTPUtil = new FavFTPUtil();
        Boolean flag = favFTPUtil.downloadFileList("192.168.15.1", 21, "123456", "123456", "/paymentStatus", "D://heliostest/paymentStatus");
//        boolean b = favFTPUtil.downloadFile("192.168.15.1", 21, "123456", "123456", "/paymentStatus","GCNA1455.PAYMENTS.201606241320160I.QLPAY012R.8080.CSV-BANSTA.RPT", "D://heliostest/paymentStatus");
    }

    private static String getFileName(String companyId, String tag){
        String fileName = tag + companyId;
        return fileName + ".txt";
    }

    public static void archiveFile(String localFileName, String archiveFileName){
        File file = new File(localFileName);
//        if (file.exists()){
            File archiveFile = new File(archiveFileName);
            file.renameTo(archiveFile);
//        }
    }
}
