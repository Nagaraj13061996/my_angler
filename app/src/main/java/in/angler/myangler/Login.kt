package `in`.angler.myangler

import `in`.angler.myangler.databinding.ActivityLoginBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        login()
    }

    private fun login() {
         val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val userNameInput: String = binding.userName.text.toString()
                val passwordInput: String = binding.password.text.toString()



                binding.login.isEnabled =
                    userNameInput.isNotEmpty() && userNameInput.length >= 6 && passwordInput.isNotEmpty() && passwordInput.length >= 6


            }

            override fun afterTextChanged(s: Editable) {}
        }

        binding.userName.addTextChangedListener(textWatcher)
        binding.password.addTextChangedListener(textWatcher)
        binding.login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }

    }

}