package com.exadel.controller;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exadel.processor.AssyncProcessor;

@WebServlet(urlPatterns = { "/longpolling" }, asyncSupported = true)
public class LongPollingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final AsyncContext asyncContext = request.startAsync();
		asyncContext.setTimeout(300000);
		AssyncProcessor.addAsyncContext(asyncContext);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String message = req.getParameter("message");
		 AssyncProcessor.notifyAllClients(message);
		 resp.setStatus(HttpServletResponse.SC_OK);
	}

}
