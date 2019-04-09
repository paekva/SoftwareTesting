class UserFactory {
    fun createValidUser(): User{
        return User("katjapka@list.ru", "rfnz98grf", "Katerina")
    }

    fun createUserWithInvalidPassword(): User{
        return User("katjapka@list.ru", "111", "Katerina")
    }
}

class User(val email: String, val password: String, val login: String)