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

       // validateNotNullParameter("userName", login);
       // validateNotNullParameter("password", password);

      /*
        String uuid = UUID.randomUUID().toString();
        try {
            RsUser rsUser = userStore.getUser(login);
            if (rsUser == null) {
                // add new rsUser
                rsUser = dataSource.getIRSUserDAO().addUser(login, password);
            } else {
                // check password and set cookie
                if (!rsUser.getUserPasswordHash().equals(password)) {
                    throw new ServiceException(Error.WRONG_PASSWORD, login);
                }
            }
            authorizedUsers.put(uuid, rsUser);
            return Response.ok()
                    .Entity(rsUser)
                    .cookie(new NewCookie("uuid", uuid))
                    .build();
        } catch (ServiceException ex) {
            log.error(ex.getMessage(), ex);
            return Response.status(ex.getError().code).Entity(ex.getMessage()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.status(500).Entity(e.getMessage()).build();
        }*/
        return null;
    }
    /*
    try{

    } catch (InvalidParameterException e) {
            logger.error(e.getError().getMessage(), e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getError()).build();
        } catch (EntityNotFoundException e) {
            logger.error(e.getError().getMessage(), e);
            return Response.status(Response.Status.NOT_FOUND).entity(e.getError()).build();
        } catch (Exception e) {
            logger.error("Internal Error", e);
            return Response.status(
                Response.Status.INTERNAL_SERVER_ERROR).entity(new Error(e.getMessage(), -1)
            ).build();
        }
     }
    * */
/*
    private RsUser getUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new ServiceException(Error.USER_IS_NOT_AUTHORIZED);
        }
        RsUser rsUser = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("uuid")) {
                rsUser = authorizedUsers.get(cookie.getValue());
            }
        }
        if (cookies == null) {
            throw new ServiceException(Error.USER_IS_NOT_AUTHORIZED);
        }
        return rsUser;
    }

    private void validateNotNullParameter(String paramName, Object paramValue) {
        if (paramValue == null) {
            throw new InvalidParameterException(paramName);
        }
    }*/
}
