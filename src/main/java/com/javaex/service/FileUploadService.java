package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileUploadDao;

@Service
public class FileUploadService {

	@Autowired
	private FileUploadDao fileDao;

	public String restore(MultipartFile file) {

		String saveDir = "D:\\JAVAstudy\\upload";
		// 파일 정보 수집
		// original 파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);

		// 확장자 (.뭐뭐뭐)
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);

		// 저장될 파일 이름
		// 파일명 중복을 피하기 위해서 UUID 사용
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);

		// 파일위치 (path)
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);

		// 파일 사이즈.getSize() (default로 byte단위)
		/*
		 * 8bit -> 1BYTE 
		 * 1024byte -> 1KB 
		 * 1024KB -> 1MB 
		 * 1024MB -> 1GB 
		 * 1024GB -> 1TB
		 */
		long fileSize = file.getSize();
		int kbSize = (int) Math.ceil(fileSize / 1024.0);
		System.out.println(fileSize + " 바이트");
		System.out.println(kbSize + "kb");
		
		// 사용자가 업로드한 파일 카피
		try {
			
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir + "\\" + saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			bout.write(fileData);
			
			if(bout != null) {
				bout.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return saveName;
	}
	//dao로 연결해주기
}
