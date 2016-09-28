package io.github.sruby.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public class UploadAction
{
	
	private String allowSuffix = "jpg,png,gif,jpeg";//�����ļ���ʽ
	
	private long allowSize = 2L;//�����ļ���С
	
	private String fileName;
	
	private String[] fileNames;
	
	public String getAllowSuffix()
	{
		return allowSuffix;
	}
	
	public void setAllowSuffix(String allowSuffix)
	{
		this.allowSuffix = allowSuffix;
	}
	
	public long getAllowSize()
	{
		return allowSize * 1024 * 1024;
	}
	
	public void setAllowSize(long allowSize)
	{
		this.allowSize = allowSize;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	public String[] getFileNames()
	{
		return fileNames;
	}
	
	public void setFileNames(String[] fileNames)
	{
		this.fileNames = fileNames;
	}
	
	private String getFileNameNew()
	{
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return fmt.format(new Date());
	}
	
	public void uploads(MultipartFile[] files, String destDir, HttpServletRequest request) throws Exception
	{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		try
		{
			fileNames = new String[files.length];
			int index = 0;
			for (MultipartFile file : files)
			{
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				int length = getAllowSuffix().indexOf(suffix);
				if (length == -1)
				{
					throw new Exception("���ϴ������ʽ���ļ�");
				}
				if (file.getSize() > getAllowSize())
				{
					throw new Exception("���ϴ����ļ���С�Ѿ�������Χ");
				}
				String realPath = request.getSession().getServletContext().getRealPath("/");
				File destFile = new File(realPath + destDir);
				if (!destFile.exists())
				{
					destFile.mkdirs();
				}
				String fileNameNew = getFileNameNew() + "." + suffix;//
				File f = new File(destFile.getAbsoluteFile() + "\\" + fileNameNew);
				file.transferTo(f);
				f.createNewFile();
				fileNames[index++] = basePath + destDir + fileNameNew;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	public void upload(MultipartFile file, String destDir, HttpServletRequest request) throws Exception
	{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		try
		{
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			int length = getAllowSuffix().indexOf(suffix);
			if (length == -1)
			{
				throw new Exception("���ϴ������ʽ���ļ�");
			}
			if (file.getSize() > getAllowSize())
			{
				throw new Exception("���ϴ����ļ���С�Ѿ�������Χ");
			}
			
			String realPath = request.getSession().getServletContext().getRealPath("/");
			File destFile = new File(realPath + destDir);
			if (!destFile.exists())
			{
				destFile.mkdirs();
			}
			String fileNameNew = getFileNameNew() + "." + suffix;
			File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
			file.transferTo(f);
			f.createNewFile();
			fileName = basePath + destDir + fileNameNew;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}
