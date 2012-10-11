package org.spout.flo.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.MimetypesFileTypeMap;

import org.joda.time.DateTime;

import com.narrowtux.blueberry.HttpException;
import com.narrowtux.blueberry.handler.HttpRequestHandler;
import com.narrowtux.blueberry.http.HttpExchange;
import com.narrowtux.blueberry.http.headers.HttpStatusCode;

public class ResourceFileHandler extends HttpRequestHandler {
	private static int BUFFER_SIZE = 512;
	private static String BASE_PATH = "src/main/resources/"; //TODO configuration
	private static MimetypesFileTypeMap MIMETYPES = new MimetypesFileTypeMap();
	
	static {
		MIMETYPES.addMimeTypes("application/javascript js JS");
		MIMETYPES.addMimeTypes("text/css css CSS");
		MIMETYPES.addMimeTypes("image/jpeg jpg jpeg JPG JPEG");
		MIMETYPES.addMimeTypes("image/png png PNG");
	}
	
	public ResourceFileHandler() {
		setFilter("/static/");
	}
	
	@Override
	public void handle(HttpExchange exchange) throws IOException, HttpException {
		String path = exchange.getRequestedUri().getPath().replaceFirst(getFilter(), "");
		File file = new File(BASE_PATH, path);
		if (file.exists()) {
			DateTime modified = new DateTime(file.lastModified());
			
			InputStream in = new FileInputStream(file);
			exchange.getResponseHeaders().setHeader("Content-Type", MIMETYPES.getContentType(file));
			exchange.getResponseHeaders().setHeader("Last-Modified", modified);
			byte [] buf = new byte[BUFFER_SIZE];
			while (true) {
				int cnt = in.read(buf);
				if (cnt > 0) {
					exchange.getOutputStream().write(buf, 0, cnt);
				} else {
					break;
				}
			}
			exchange.sendResponseHeaders(HttpStatusCode.HTTP_200_OK, 0);
			exchange.close();
			in.close();
			
		} else {
			throw new HttpException(HttpStatusCode.HTTP_404_NOT_FOUND, "The file with the URL "+exchange.getRequestedUri().getPath() + " was not found on the server.");
		}
	}

}
