/*
 * This file is part of Aquaduct.
 *
 * Copyright (c) 2012 Spout LLC <http://www.spout.org/>
 * Aquaduct is licensed under the GNU Affero General Public License.
 *
 * Aquaduct is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Aquaduct is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spout.aquaduct.web;

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
	private static String BASE_PATH = "src/main/resources/"; // TODO configuration
	private static final MimetypesFileTypeMap MIMETYPES = new MimetypesFileTypeMap();

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
