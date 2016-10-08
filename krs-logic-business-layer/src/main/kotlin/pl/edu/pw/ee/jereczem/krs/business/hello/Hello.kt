package pl.edu.pw.ee.jereczem.krs.business.hello

import pl.edu.pw.ee.jereczem.krs.rto.hello.HelloResponse
import javax.ejb.Local

@Local
interface Hello {
    fun hello(username : String) : HelloResponse
}