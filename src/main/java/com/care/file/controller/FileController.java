package com.care.file.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.file.service.FileServiceImpl;

@Controller
public class FileController {
	@Autowired FileServiceImpl fs;
	
	@GetMapping("form")
	public String form() {
		return "uploadForm";
	}
	@PostMapping("upload")
	public String upload(
			MultipartHttpServletRequest mul ) {
		//System.out.println(mul.getParameter("id"));
		//System.out.println(mul.getParameter("name"));
		//MultipartFile m = mul.getFile("file");
		//System.out.println( m.getOriginalFilename() );
		
		fs.fileProcess(mul);
		
		return "redirect:form";
	}
	@GetMapping("views")
	public String views(Model model) {
		fs.getData(model);
		return "result";
	}
	@GetMapping("download")
	public void download(@RequestParam String imgName,
			HttpServletResponse response) throws Exception {
		/*
		 Content-disposition : 파일 다운로드로 진행해 주세요
		 attachment : 붙일 것이다. 어떤걸?
		 fileName을 붙여서 다운로드 한다.
		 */
		response.addHeader("Content-disposition", 
				"attachment; fileName="+imgName);
		File file = 
		  new File(FileServiceImpl.IMAGE_REPO+"/"+imgName);
		FileInputStream f = new FileInputStream(file);
		//왼쪽의 값을 오른쪽으로 복사해준다
		FileCopyUtils.copy(f, response.getOutputStream() );
		f.close();
	}
	@GetMapping("form02")
	public String form02() {
		return "form02";
	}
	@PostMapping("upload02")
	public String upload02(
						MultipartHttpServletRequest mul) {
		fs.fileProcess02(mul);
		return "redirect:form02";
	}
}










