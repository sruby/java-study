package io.github.sruby.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * ͼƬ�ϴ���ѹ��
 * @author liuwf on 2016��11��26�� ����2:19:08
 */
public class UploadImageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private Logger logger = Logger.getLogger(UploadImageServlet.class);
	
	private String tempPath = "/uploadImageTemp";// ��ʱ�洢Ŀ¼
	
	private String savePath = "/userImage";// �洢Ŀ¼
	
	private String fileName = ""; //�ļ���
	
	private final static int PICTURE_WIDTH = 120; //�û�ͷ��ͼƬ���
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = new JSONObject();
		
		try
		{
			
			
			//��ȡ��ʱĿ¼
			String tempPathDir = request.getSession().getServletContext().getRealPath(this.tempPath);
			File tempPathDirFile = new File(tempPathDir);
			if (!tempPathDirFile.exists())
			{
				tempPathDirFile.mkdirs();
			}
			
			//�洢Ŀ¼
			String realDir = request.getSession().getServletContext().getRealPath(this.savePath);
			File realDirFile = new File(realDir);
			if (!realDirFile.exists())
			{
				realDirFile.mkdirs();
			}
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096); // ���û�������С��������4kb
			factory.setRepository(tempPathDirFile);// ���û�����Ŀ¼
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(4194304); // ��������ļ��ߴ磬������4MB
			
			List<FileItem> items = upload.parseRequest(request);// �õ����е��ļ�
			if (items == null || items.isEmpty())
			{
				throw new Exception("�ϴ����ļ�Ϊ�գ�");
			}
			
			Iterator<FileItem> iterator = items.iterator();
			
			while (iterator.hasNext())
			{
				FileItem fi = (FileItem) iterator.next();
				String fileName = fi.getName();
				if (fileName != null)
				{
					//ͼƬ�ļ���ʽУ�� (gif,jpg,jpeg,bmp,png)
					String suffixName = FilenameUtils.getExtension(fileName);
					if (!"gif".equalsIgnoreCase(suffixName) && !"jpg".equalsIgnoreCase(suffixName) && !"jpeg".equalsIgnoreCase(suffixName) && !"bmp".equalsIgnoreCase(suffixName)
							&& !"png".equalsIgnoreCase(suffixName))
					{
						throw new Exception("�ϴ���ͼƬ�ļ��Ƿ�");
					}
					
					//�����ļ���
					this.fileName = new Date().getTime() + "." + FilenameUtils.getExtension(fileName);
					
					/**
					 * ѹ��ͼƬ��д�����
					 */
					InputStream inputStream = fi.getInputStream();
					ImgCompress imgCompress = new ImgCompress(inputStream, realDir + File.separator + this.fileName);
					imgCompress.resizeByWidth(PICTURE_WIDTH);
					
					//����һ��Сͼ
					File savedFile = new File(realDir, this.fileName);
					File savedSmallFile = new File(realDir + "/small/", this.fileName);
					FileUtils.copyFile(savedFile, savedSmallFile);
					
					/**
					 * ��Ӧ
					 */
					jsonObj.put("picurl", this.fileName);
					jsonObj.put("result", "true");
				}
			}
		}
		catch (Exception e)
		{
			jsonObj.put("result", "false");
			jsonObj.put("message", "ͼƬ����ʧ��:"+e.getMessage());
			logger.error("ͼƬ�ϴ�ʧ��"+e.getMessage(), e);
		}
		finally
		{
			PrintWriter writer = null;
			try
			{
				writer = response.getWriter();
				jsonArr.add(jsonObj);
				writer.print(jsonArr.toString());
				writer.flush();
			}
			catch (Exception e2)
			{
			}
			finally
			{
				writer.close();
			}
			
		}
	}
	
}
