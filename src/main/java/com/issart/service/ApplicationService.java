package com.issart.service;

import com.issart.datasource.entity.RsUser;
import com.issart.exception.InvalidParameterException;
import com.issart.exception.ServiceException;
import com.issart.exception.Error;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("/service")
public class ApplicationService {
    private static final Map<String, RsUser> authorizedUsers = new HashMap<String, RsUser>();
    private final UserService userService = new UserService(new SecurityService());

    @PUT
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setUser(@QueryParam("userName") String login,
                            @QueryParam("password") String password,
                            @QueryParam("admin") String admin,
            @Context HttpServletRequest request) {


        return null;
    }

}
