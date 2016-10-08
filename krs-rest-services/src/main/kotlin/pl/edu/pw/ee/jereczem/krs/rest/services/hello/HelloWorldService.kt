package pl.edu.pw.ee.jereczem.krs.rest.services.hello

import pl.edu.pw.ee.jereczem.krs.business.hello.Hello
import javax.ejb.EJB
import javax.ejb.Stateless
import javax.ws.rs.Path
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.SecurityContext

@Stateless
@Path("/hello_world")
open class HelloWorldService {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    open fun helloWorld(@Context securityContext: SecurityContext) = "Hello, world!"

}