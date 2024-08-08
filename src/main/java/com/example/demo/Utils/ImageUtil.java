package com.example.demo.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageUtil {
    public static String saveImage(HttpServletRequest req) throws IOException, ServletException {
        Part part = req.getPart("image");
        // lấy ra file name
        if(part.getSize() <= 0) return null;
        String[] fileNames = part.getSubmittedFileName().split("\\.");
        String fileName = fileNames[0] + System.currentTimeMillis() + "." + fileNames[1];
        File file = getFolderUpload(req);
        part.write(file.getPath() + File.separator + fileName);
        File fileNotTarget = getFolderUploadNotTarget(req);
        FileInputStream fileInputStream = new FileInputStream(file.getPath() + File.separator + fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(fileNotTarget.getPath() + File.separator + fileName);
        byte[] buffer = new byte[1024];
        while (fileInputStream.read(buffer) > 0) {
            fileOutputStream.write(buffer);
        }
        return fileName;
    }
    private static File getFolderUpload(HttpServletRequest req) throws MalformedURLException {
       URL urlImage = req.getServletContext().getResource("/Images");
       String url = urlImage != null ? urlImage.getPath() : req.getServletContext().getResource("/").getPath() + "Images";
       System.out.println(url);
//        String url = req.getServletContext().getRealPath("/Images");
        File folderUploads = new File(url);
        if(!folderUploads.exists()) {
            folderUploads.mkdirs();
        }
        //System.out.println(url.getPath() + " " + url.toString());
        //String absoluteUpload = folder.getAbsolutePath();
        //System.out.println(absoluteUpload);
        //File file = new File(absoluteUpload);
        return folderUploads;
    }

    private static File getFolderUploadNotTarget(HttpServletRequest request) throws MalformedURLException {
        URL urlImage = request.getServletContext().getResource("/");
        String[] fileNames = urlImage.getPath().split("/");
        System.out.println(Arrays.toString(fileNames));
        List<String> list = new ArrayList<>(Arrays.asList(fileNames).subList(0, fileNames.length - 2));
        System.out.println(list.toString());
        String url = String.join("/", list) + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "Images";
        File file = new File(url);
        if(!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static void deleteFileImage(HttpServletRequest req, String name) throws MalformedURLException {
        URL url = req.getServletContext().getResource("/Images");
        String fileName = url.getPath() + name;
        System.out.println(fileName);
        File file = new File(fileName);
        try {
            System.out.println(file.delete());
        } catch (Exception exception) {
            System.out.println("File can't delete");
        }
    }
}
