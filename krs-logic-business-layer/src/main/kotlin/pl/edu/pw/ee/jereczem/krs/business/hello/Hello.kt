package pl.edu.pw.ee.jereczem.krs.business.hello

import javax.ejb.Local

@Local
interface Hello {
    fun hello(username : String) : ResponseMessage
}