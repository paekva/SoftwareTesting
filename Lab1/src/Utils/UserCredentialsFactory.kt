package Utils

class UserCredentialsFactory {
    fun getCorrectCredentials(): UserCredentials {
        return UserCredentials()
    }

    fun getWrongEmailCredentials(): UserCredentials {
        return UserCredentials()
    }

    fun getWrongPasswordCredentials(): UserCredentials {
        return UserCredentials()
    }

}

