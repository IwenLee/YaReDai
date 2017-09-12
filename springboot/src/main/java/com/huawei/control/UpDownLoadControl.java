package com.huawei.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UpDownLoadControl {

	private static final Logger logger = LoggerFactory.getLogger("UpDownLoadControl");
	
	@ResponseBody
	@RequestMapping(value = "/fileDownload")
	public String fileDownload(HttpServletResponse response){
		File file = new File("D://360/ThreadWaitNotify.java");
		FileInputStream fileInputStream = null;
		if(!file.exists())
		System.out.println("file is not exist!");
		try {
			fileInputStream = new FileInputStream(file);
			ServletOutputStream outputStream = response.getOutputStream();
			byte[] b = new byte[1024];
			int i = 0; 
			while(i != -1){
				outputStream.write(b);
				i=fileInputStream.read(b);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fileInputStream!=null)
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return "seccess";
	}
	
	/**
	 * 复制文件到流中,文件下载的一种方法
	 * @param response
	 * @param fileNameUrl
	 * @return
	 */
	@RequestMapping(value = "/writeToResponse")
	public String writeToResponse(HttpServletResponse response){
		String fileNameUrl = "D://360/ThreadWaitNotify.java";//需要下载的文件路径
		response.setContentType("application/x-download");
		File file = new File(fileNameUrl.replace("/", File.separator).replace("\\\\", File.separator));
		if(!file.exists()){
			System.out.println("file is not wxistting");
			return "filed";
		}
		try(ServletOutputStream sos = response.getOutputStream()) {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
			Files.copy(file.toPath(), sos);
		} catch (Exception e) {
			System.err.println("copy file is error : " +e);
		}
		return "success";
	}
	
	/**
	 * 下载文件//http://localhost:8080/downLoad?url=http://localhost:8080/fileDownload
	 * @param url远程http连接
	 */
	@ResponseBody
	@RequestMapping(value = "/downLoad" ,method = RequestMethod.GET)
	public String downloadFile(@RequestParam String url){
		String tarUrl = "C://service_log/a.java";
		InputStream is = null;
		try {
			URL address = new URL(url);
			HttpURLConnection con = (HttpURLConnection)address.openConnection();
			con.setConnectTimeout(15000);
			con.setReadTimeout(15000);
			is = con.getInputStream();
			int len = tarUrl.lastIndexOf("\\");
			if(len == -1)
			len = tarUrl.lastIndexOf("/");	
			String parentPath = tarUrl.substring(0, len);			
			File parentPathFile = new File(parentPath);
			if(!parentPathFile.exists()){
				if(!parentPathFile.mkdirs()){
					System.err.println("error");
				}
			}
			Files.copy(is, Paths.get(tarUrl));
		} catch (IOException e) {
			System.out.println("copy file to local failed" +e);
			return "filed";
		}finally{
			try {
				if(is!=null)
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "seccuss";
	}
	
	/**
	 * 单个文件上传
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public @ResponseBody String fileUpload(@RequestParam("file") MultipartFile file){
		
		long startTime = System.currentTimeMillis();
		String fileOriginalName = file.getOriginalFilename();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(file.getInputStream(),1024);
			bos = new BufferedOutputStream(new FileOutputStream(new File(fileOriginalName)),1024);
			byte[] b = new byte[bis.available()];
			while(bis.read(b) != -1){
				bos.write(b, 0, b.length);
			}
			bos.flush();
		} catch (IOException e) {
			logger.error("file upload filed!");
		}finally{
			try {
				bos.close();
				bis.close();
			} catch (IOException e) {
				logger.debug("close %s and %s stream error!", "bos", "bis");
			}
		}
		System.out.println("上传使用的总时间 ：" + (System.currentTimeMillis() - startTime));
		return "success";
	}
	/**
	 * 多个文件上传
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/files/upload", method = RequestMethod.POST)
	public @ResponseBody String filesUpload(@RequestParam("files") MultipartFile[] files){
		
		long startTime = System.currentTimeMillis();
		
		if(files.length > 0){
			for (MultipartFile file : files) {
				String fileOriginalName = file.getOriginalFilename();
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				try {
					bis = new BufferedInputStream(file.getInputStream(),1024);
					bos = new BufferedOutputStream(new FileOutputStream(new File(fileOriginalName)),1024);
					byte[] b = new byte[bis.available()];
					while(bis.read(b) != -1){
						bos.write(b, 0, b.length);
					}
					bos.flush();
				} catch (IOException e) {
					logger.error("file upload filed!");
				}finally{
					try {
						bos.close();
						bis.close();
					} catch (IOException e) {
						logger.debug("close %s and %s stream error!", "bos", "bis");
					}
				}
			}
		}else{
			return "没有可处理的文件";
		}
		
		System.out.println("上传使用的总时间 ：" + (System.currentTimeMillis() - startTime));
		
		return "success";
	}
}
