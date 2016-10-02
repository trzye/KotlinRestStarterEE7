import javax.ws.rs.Path
import javax.ws.rs.GET

@Path("/helloworld")
class RestHelloWorld{

    @GET
    fun helloWorld() = "Hello, world"

}