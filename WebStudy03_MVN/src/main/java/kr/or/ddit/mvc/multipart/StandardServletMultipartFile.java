package kr.or.ddit.mvc.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class StandardServletMultipartFile implements MultipartFile {
	
	private Part adaptee;
	
	public StandardServletMultipartFile(Part adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public byte[] getBytes() throws IOException{
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		InputStream is = getInputStream();
//		os.toByteArray();
//		FileUpload : 업로드 복사하는 거 쉬움
		return IOUtils.toByteArray(getInputStream()); //라이브러리
	}

	@Override
	public String getContentType() {
		return adaptee.getContentType();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return adaptee.getInputStream();
	}

	@Override
	public String getName() {
		return adaptee.getName();
	}

	@Override
	public String getOriginalFilename() {
		return adaptee.getSubmittedFileName();
	}

	@Override
	public long getSize() {
		return adaptee.getSize();
	}

	@Override
	public boolean isEmpty() {
		return StringUtils.isAllBlank(getOriginalFilename());
	}

	@Override
	public void transferTo(File dest) throws IOException{
		adaptee.write(dest.getCanonicalPath());
		
	}

}
