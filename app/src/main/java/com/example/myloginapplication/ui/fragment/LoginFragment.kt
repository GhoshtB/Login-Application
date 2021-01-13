package com.example.myloginapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myloginapplication.MainActivity
import com.example.myloginapplication.R
import com.example.myloginapplication.app.util
import com.example.myloginapplication.app.util.showToast
import com.example.myloginapplication.data.model.User
import com.example.myloginapplication.databinding.FragmentLoginBinding
import com.example.myloginapplication.ui.viewmodel.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters

    private var nUsers: List<User> = arrayListOf()
    lateinit var navController: NavController
    lateinit var binding: FragmentLoginBinding
    lateinit var mainViewModel: MainViewModel
    var dialog:AlertDialog? =null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_login, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        mainViewModel = (activity as MainActivity).mainViewModel
        mainViewModel.getUser()
        mainViewModel.users.observe(viewLifecycleOwner, {
            nUsers = it
            if (it.size > 0) {
                print("users ${it[0].email}  ${it[0].password}")
            }
            if (dialog!=null){
                dialog!!.dismiss()
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
//        view.findViewById<Button>(R.id.button).setOnClickListener(this)
//        view.findViewById<Button>(R.id.button2).setOnClickListener(this)
//        view.findViewById<TextView>(R.id.textView2).setOnClickListener(this)
        binding.button.setOnClickListener(this)
//        binding.btmS.button2.setOnClickListener(this)
        binding.textView2.setOnClickListener(this)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onClick(v: View?) {
        when (v) {
            binding.textView2 -> {

                showDialog()
//                var fragment = DialogFragment()
//                fragment.show(childFragmentManager,"Dialog Fragment")

//                toggleBottomSheet()
            }
            binding.button -> {
                if (binding.etEmail.text.toString().isEmpty() || binding.etPassword.text.toString()
                        .isEmpty()
                ) {
                    (activity as MainActivity).showToast("Empty Fields")
                } else if (!util.isValidEmail(binding.etEmail.text.toString())) {
                    binding.etEmail.setError("INvalid")
                } else if (!util.isValidPassword(binding.etPassword.text.toString())) {
                    binding.etPassword.setError("INvalid")
                } else if (nUsers.any { login ->
                        binding.etEmail.text.toString()
                            .equals(login.email) && binding.etPassword.text.toString()
                            .equals(login.password)
                    }) {
                    navController.navigate(R.id.fragmentHome)
                    (activity as MainActivity).eMail = binding.etEmail.text.toString()
                    (activity as MainActivity).password = binding.etPassword.text.toString()
                } else {
                    (activity as MainActivity).showToast("Incorrect Details")
                }

            }

        }
    }



    @SuppressLint("UseRequireInsteadOfGet")
    private fun showDialog() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity as MainActivity)
        val inflater = activity!!.layoutInflater
        val view: View = inflater.inflate(R.layout.fragment_comment_dialog, null)
        builder.setView(view)
        var etEmail = view.findViewById<EditText>(R.id.etEmail)
        var etConfirmPassword = view.findViewById<EditText>(R.id.etConfirmPassword)
        var etUserName = view.findViewById<EditText>(R.id.etUserName)
        var etPassword = view.findViewById<EditText>(R.id.etPassword)
        var button2 = view.findViewById<Button>(R.id.button2)
         dialog = builder.create()

        button2.setOnClickListener {
            if (etEmail.text.toString().isEmpty() || etConfirmPassword.text.toString()
                    .isEmpty() || etPassword.text.toString().isEmpty() || etUserName.text.toString()
                    .isEmpty()
                    ) {
                (activity as MainActivity).showToast("Empty Fields")
            } else if (!util.isValidEmail(etEmail.text.toString())) {
                etEmail.setError("INvalid")
            } else if (!util.isValidPassword(etPassword.text.toString())) {
                etPassword.setError("INvalid")
            } else if (!etConfirmPassword.text.toString()
                    .contentEquals(etPassword.text.toString())
            ) {
                etConfirmPassword.setError("Not Matching")
            } else {
                etEmail.setError(null)
                etConfirmPassword.setError(null)
                mainViewModel.insertUser(
                    User(
                        email = etEmail.text.toString(),
                        password = etPassword.text.toString(),
                        userName = etUserName.text.toString(),
                        phone = "",
                        name = ""
                    )
                )
                (activity as MainActivity).userName = etUserName.text.toString()

               /* mainViewModel.users.observe(viewLifecycleOwner, {
                    nUsers = it

                })*/
            }


        }

        dialog!!.show()
    }


}