package org.acme.rest.json;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.Model.Account;

@Path("/public/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountsResource {

    private final Set<Account> accounts = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public AccountsResource() {
        accounts.add(new Account("2e130efca455262edd661fe8d149f9750858c922b8d0a3d2d0f79982b3b997de", "current", "ARS",
                false));
    }

    @GET
    public Set<Account> list() {
        return accounts;
    }

    @GET
    @Path("/{id}")
    public Account one(@PathParam("id") String id) {
        return new Account(id, "current", "ARS", false);
    }

    @POST
    public Response postOne(final Account acc) {
        accounts.add(acc);
        System.out.println(accounts);
        return Response.ok().build();
    }
}