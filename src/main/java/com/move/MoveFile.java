package com.move;

import java.io.File;

/**
 * 移动文件到指定的目录
 *
 * @author www.zuidaima.com
 *
 */
public class MoveFile {

    public static void main(String[] args) {
        try {
            File afile = new File("C:/Users/Newx/Desktop/SQL.txt");
            if (afile.renameTo(new File("C:/Users/Newx/Desktop/test/" + afile.getName()))) {
                System.out.println("File is moved successful!");
            } else {
                System.out.println("File is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}