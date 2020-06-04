package org.acme.rest.json;

import java.util.*;
import java.util.stream.Collectors;

import javax.inject.Inject;
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
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/public/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountsResource {

    private final Accounts accounts;
    private CipherService cipher;
    @ConfigProperty(name = "cipher.key")
    private String cipherKey;

    @Inject
    public AccountsResource(CipherService cipher) {
        this.cipher = cipher;
        accounts = new Accounts();
        accounts.addItem(new Account("JY4SCLEWRHVVRLL6KN23QETEFBAZHNMJHOXC7OAEBNNQWKTVL57Q====", "current", "ARS",false));
        accounts.addItem(new Account("EMQCHXFT3LN3574FNDPVDUUVJ3VX4XH2RNZ7ESRHO4E46772N5IQ====", "savings", "ARS",false));
        accounts.addItem(new Account("ROWESSXYQJO2Y7O6FLEV3NTD7VAM4RULX7I6ML5BW6YVHMELQU2Q====", "savings", "ARS",false));
        accounts.addItem(new Account("ROWESSXYQJO2Y7O6FLEV3NTD7VKHGRZE4A3QCGDNLO3DDFTFAV7Q====", "current", "ARS",false));
    }

    @GET
    @Path("/list")
    public Accounts list() {
        return accounts;
    }
    @POST
    @Path("/list")
    public Accounts Postlist(String accessToken) {
        System.out.println("Access Token: "+accessToken);
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

    @POST
    @Path("/encrypt")
    public ArrayList<Account> encrypt(ArrayList<String> cbus) throws Exception {
        return (ArrayList<Account>) cbus.stream().map(c -> {
            try {
                return new Account(cipher.encrypt(c, cipherKey),"","",false);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

    }
}