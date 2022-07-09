package com.care.file.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.file.dto.FileDTO;
import com.care.file.mybatis.FileMapper;

@Service
public class FileServiceImpl {
	public final static String IMAGE_REPO = "C:/images"; //사진 저장 경로
	@Autowired FileMapper mapper;
	
	public void fileProcess(MultipartHttpServletRequest mul) {
		FileDTO dto = new FileDTO();
		dto.setId( mul.getParameter("id") );
		dto.setName( mul.getParameter("name") );
		
		MultipartFile file = mul.getFile("file");
		if(file.getSize() != 0) { //file.isEmpty()
			SimpleDateFormat f = 
					new SimpleDateFormat("yyyyMMddHHmmss-"); //가져온 현재 시간 형식 맞추기
			String sysFileName = f.format( new Date() );  //new Date() 현재 시간 가져오기
			System.out.println( "포멧팅 : "+sysFileName );
			
			// yyyyMMddHHmmss-img.jpg
			sysFileName += file.getOriginalFilename();
			
			dto.setImgName( sysFileName ); 
			
			File saveFile = new File( IMAGE_REPO+"/"+sysFileName );
			
			try {
				file.transferTo( saveFile );
			} catch ( Exception e) {
				e.printStackTrace();
			} 
		}else {
			dto.setImgName("nan");
		}
		mapper.saveData(dto);
	}
	public void getData(Model model) {
		model.addAttribute( "list", mapper.getData() );
	}
	public void fileProcess02(
						MultipartHttpServletRequest mul) {
		Iterator<String> fileNames = mul.getFileNames();
		while( fileNames.hasNext() ) {
			//System.out.println( fileNames.next() );
			MultipartFile file = mul.getFile( fileNames.next() );
			
			if(file.getSize() != 0) { //file.isEmpty()
				SimpleDateFormat f = 
						new SimpleDateFormat("yyyyMMddHHmmss-");
				String sysFileName = f.format( new Date() );
				sysFileName += file.getOriginalFilename();
				
				File saveFile = new File( IMAGE_REPO+"/"+sysFileName );
				
				try {
					file.transferTo( saveFile );
				} catch ( Exception e) {
					e.printStackTrace();
				} 
			}
		}
	}
}









