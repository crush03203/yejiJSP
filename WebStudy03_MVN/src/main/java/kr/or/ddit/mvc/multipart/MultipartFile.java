package kr.or.ddit.mvc.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface MultipartFile {
	public byte[] getBytes() throws IOException ;
	public String getContentType();
	public InputStream getInputStream() throws IOException;
	public String getName();
	public String getOriginalFilename();
	public long getSize();
	public boolean isEmpty(); //아무것도 입력하지 않아도 카트는 형성된다
	public void transferTo(File dest) throws IOException ; //
}
