package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUploader {

	public String imageUploader(MultipartFile ctx, String fileName) {
		try {
			InputStream inputStream = ctx.getInputStream();
			OutputStream output = new FileOutputStream(
					new File("C:\\Users\\Nafees Abdullah\\Desktop\\images\\" + ctx.getOriginalFilename()));
			int read = 0;
			System.out.println("-> 3");
			while ((read = inputStream.read()) != -1) {
				output.write(read);
			}
			output.close();
			System.out.println("saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ctx.getOriginalFilename();

	}

	private String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
