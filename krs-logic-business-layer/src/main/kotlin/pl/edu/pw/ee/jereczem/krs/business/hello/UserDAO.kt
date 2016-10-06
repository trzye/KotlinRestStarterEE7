package pl.edu.pw.ee.jereczem.krs.business.hello

class UserDAO {
    fun  getUserByUsername(username: String): User {
        return User(username, "sampleRole")
    }
}