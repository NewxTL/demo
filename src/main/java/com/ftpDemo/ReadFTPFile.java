package com.ftpDemo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;


public class ReadFTPFile {
    private Logger logger = Logger.getLogger(ReadFTPFile.class);

    /**
     * 去 服务器的FTP路径下上读取文件
     * @param ftpUserName ftp登录名
     * @param ftpPassword ftp登录密码
     * @param ftpPath ftp需要读取文件的绝对路径
     * @param ftpHost ftp服务器域名
     * @param ftpPort ftp服务器密码
     * @param fileName
     * @param enCoding
     * @return
     */
    public String readConfigFileForFTP(String ftpUserName, String ftpPassword,
                                       String ftpPath, String ftpHost, int ftpPort,
                                       String fileName, String enCoding) {
        StringBuffer resultBuffer = new StringBuffer();
        FileInputStream inFile = null;
        InputStream in = null;
        FTPClient ftpClient = null;
        logger.info("开始读取绝对路径" + ftpPath + "文件!");
        try {
            ftpClient = FTPUtil.getFTPClient(ftpHost, ftpPassword, ftpUserName,
                    ftpPort);
            ftpClient.setControlEncoding(enCoding); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);
            in = ftpClient.retrieveFileStream(fileName);
        } catch (FileNotFoundException e) {
            logger.error("没有找到" + ftpPath + "文件");
            e.printStackTrace();
            return "下载配置文件失败，请联系管理员.";
        } catch (SocketException e) {
            logger.error("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件读取错误。");
            e.printStackTrace();
            return "配置文件读取失败，请联系管理员.";
        }
        if (in != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String data = null;
            try {
                while ((data = br.readLine()) != null) {
                    resultBuffer.append(data + "\n");
                }
            } catch (IOException e) {
                logger.error("文件读取错误。");
                e.printStackTrace();
                return "配置文件读取失败，请联系管理员.";
            }finally{
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            logger.error("in为空，不能读取。");
            return "配置文件读取失败，请联系管理员.";
        }
        return resultBuffer.toString();
    }
}
