package pl.edu.pw.ee.jereczem.krs.business.hello

import javax.ejb.Stateless

@Stateless
open class HelloBean : Hello {

    val userDAO = UserDAO()

    override fun hello(username : String) : ResponseMessage = ResponseMessage("Hello ${userDAO.getUserByUsername(username)}")

}

data class ResponseMessage(val message: String)

