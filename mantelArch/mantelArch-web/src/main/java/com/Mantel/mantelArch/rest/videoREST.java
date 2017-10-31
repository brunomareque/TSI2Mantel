package com.Mantel.mantelArch.rest;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/video")
public class videoREST {

	@GET
	@Produces("video/mp4")
	public Response getFile() {
	  File file = new File("C:\\Users\\Admin\\Downloads\\Juuni_Taisen_Opening_Panorama_Panama_Town_Rapture(youtube.com).mp4");
	  return Response.ok(file, "video/mp4").build();
	}
}
