package com.nsisc.emconnector.core.web.gwt.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public abstract class AbstractFileUploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload();

		// Parse the request
		FileItemIterator iter;
		try {
			iter = upload.getItemIterator(req);
		} catch (FileUploadException e) {
			throw new ServletException(e);
		}
		try {
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				if (item.isFormField()) {
					processFormField(item, req, resp);
				} else {
					processUploadedFile(item, req, resp);
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException(e);
		}
	}
	
	public abstract void processUploadedFile(FileItemStream item, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

	public abstract void processFormField(FileItemStream item, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	
}
