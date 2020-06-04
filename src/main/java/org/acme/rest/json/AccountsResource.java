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
import org.acme.Model.Accounts;

@Path("/public/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountsResource {

    private final Accounts accounts;

    public AccountsResource() {
        accounts = new Accounts();
        accounts.addItem(new Account("2e130efca455262edd661fe8d149f9750858c922b8d0a3d2d0f79982b3b997de", "current", "ARS",false));
        accounts.addItem(new Account("f61af3139acda53dfa1b39fc167d3994aa5c08d7f016ee3b2b04cdae1ae15940", "savings", "ARS",false));
    }

    @GET
    @Path("/list")
    public Accounts list() {
        return accounts;
    }

    @GET
    @Path("/{id}")
    public Account one(@PathParam("id") String id) {
        return new Account(id, "current", "ARS", false);
    }

    @POST
    public Response postOne(final Account acc) {
        accounts.addItem(acc);
        System.out.println(accounts);
        return Response.ok().build();
    }
}