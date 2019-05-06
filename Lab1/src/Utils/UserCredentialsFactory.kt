package Utils

class UserCredentialsFactory {
    fun getCorrectEmail(): String {
        return "paekva@yandex.ru"
    }

    fun getNewCorrectEmail(): String {
        return "paa@mail.ru"
    }

    fun getUnregisteredEmail(): String {
        return "someEmail@yandex.ru"
    }

    fun getIncorrectFormatEmail(): String {
        return "paekvayandexru"
    }

    fun getCorrectPassword(): String {
        return "rfnz98grf"
    }

    fun getNewCorrectPassword(): String {
        return "qpwoe3RiJs+1"
    }

    fun getIncorrectPassword(): String {
        return "12345"
    }

    fun getCorrectName(): String {
        return "Rkfldw1"
    }

    fun getCorrectAge(): String {
        return "21"
    }

    fun getRestrictedSymbolsString(): String {
        return "$5/*-)2"
    }

    fun getUsedByOtheUserName(): String {
        return "Kate"
    }

    fun getEmptyString(): String {
        return ""
    }
}

