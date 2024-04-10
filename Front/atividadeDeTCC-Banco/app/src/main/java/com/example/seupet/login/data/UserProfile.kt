import android.content.Context
import android.content.SharedPreferences

class UserProfile(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("userProfile", Context.MODE_PRIVATE)

    fun saveEmail(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.apply()
    }

    fun getEmail(): String? {
        return sharedPreferences.getString("email", null)
    }
}