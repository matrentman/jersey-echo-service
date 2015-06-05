package com.empty.ws.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/")
public class EchoService
{
	@POST
    @Path("/echoService")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response echoService(InputStream jsonPayload)
	{
		int statusCode = 200;
		String echoString = null;
		
        StringBuilder sb = new StringBuilder();
        try 
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(jsonPayload));
            String line = null;
            while ((line = in.readLine()) != null) 
            {
                sb.append(line);
            }
        } catch (Exception e) 
        {
            System.out.println("Error Parsing JSON payload!");
            echoString = "The JSON payload could not be parsed!";
            statusCode = 500;
        }
        
        if (statusCode == 200)
        {
        	System.out.println("Data Received: " + sb.toString());
        	echoString = sb.toString();
        }
 
        return Response.status(statusCode).entity(echoString).build();
    }
}