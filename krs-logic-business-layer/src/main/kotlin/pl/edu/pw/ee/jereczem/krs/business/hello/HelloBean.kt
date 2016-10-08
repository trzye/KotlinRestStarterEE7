package pl.edu.pw.ee.jereczem.krs.business.hello

import pl.edu.pw.ee.jereczem.krs.rto.hello.HelloResponse
import javax.ejb.Stateless

@Stateless
open class HelloBean : Hello {

    val userDAO = UserDAO()

    override fun hello(username : String) : HelloResponse = HelloResponse("Hello ${userDAO.getUserByUsername(username)}")

}

