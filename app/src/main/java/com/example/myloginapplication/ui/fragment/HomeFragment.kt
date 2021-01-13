package com.example.myloginapplication.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myloginapplication.MainActivity
import com.example.myloginapplication.R
import com.example.myloginapplication.app.util
import com.example.myloginapplication.app.util.showToast
import com.example.myloginapplication.data.model.User
import com.example.myloginapplication.ui.adapter.UserADapter
import com.example.myloginapplication.ui.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters

    private var nusers: List<User> = arrayListOf()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    lateinit var bottomSheet: ConstraintLayout
    lateinit var navController: NavController
   private var flag:Int=0
    private var usersId:Int=-1
    var userADapter = UserADapter({
        button2.setText("Update Details")
        toggleBottomSheet()
        etPersonName.setText(name)
        editTextTextPassword.setText(phone)
        flag=1
        usersId=userId
    },{user->
        mainViewModel.deleteUser(user)
    })
    lateinit var views: View
    lateinit var rvUser: RecyclerView
    lateinit var fabUser: FloatingActionButton
    lateinit var fabLogOff: FloatingActionButton
    lateinit var button2: Button
    lateinit var etPersonName: EditText
    lateinit var editTextTextPassword: EditText


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
//        lbinding = DataBindingUtil.inflate(inflater, R.layout.layout_persistent_bottom_sheet, container, false)
        views = inflater.inflate(R.layout.fragment_home, container, false)
//        bottomSheet = views.findViewById(R.id.bottomSheet)
        fabUser = views.findViewById(R.id.fabUser)
        fabLogOff = views.findViewById(R.id.fabLogOff)
        button2 = views.findViewById(R.id.button2)
        etPersonName = views.findViewById(R.id.etPersonName)
        editTextTextPassword = views.findViewById(R.id.editTextTextPassword)
        mainViewModel = (activity as MainActivity).mainViewModel
//            view.findViewById<ConstraintLayout>(R.id.bottomSheet)
        rvUser = views.findViewById(R.id.rvUser)
        rvUser.apply {
            layoutManager = LinearLayoutManager(activity!!.applicationContext)
            setHasFixedSize(true)
            adapter = userADapter
        }
        bottomSheetBehavior = BottomSheetBehavior.from(views.findViewById(R.id.bottomSheet))
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {

                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
        mainViewModel.users.observe(viewLifecycleOwner, {
            nusers = it
            print("users ${it[0].email}  ${it[0].password}")
            userADapter.apply {
                users = it.filter { user ->
                    user.email.equals((activity as MainActivity).eMail) && user.password.equals((activity as MainActivity).password) && !user.phone.isEmpty() && !user.name.isNullOrBlank()
                }
                notifyDataSetChanged()
            }
        })
//        return inflater.inflate(R.layout.fragment_home, container, false)
        return views
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        fabUser.setOnClickListener(this)
        fabLogOff.setOnClickListener(this)
        button2.setOnClickListener(this)
    }

    fun toggleBottomSheet() {
        if (bottomSheetBehavior.getState() !== BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)

        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)

        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button2 -> {
                if (etPersonName.text.toString().isEmpty() || editTextTextPassword.text.toString().isEmpty()){
                    (activity as MainActivity).showToast("Empty Fields")
                }else{
                    if (flag==0){
                        mainViewModel.insertUser(
                            User(
                                name = etPersonName.text.toString(),
                                phone = editTextTextPassword.text.toString(),
                                password = (activity as MainActivity).password,
                                email = (activity as MainActivity).eMail,
                                userName = (activity as MainActivity).userName
                            )
                        )
                        etPersonName.setText("")
                        editTextTextPassword.setText("")
                    }else{
                        var user= User(
                            name = etPersonName.text.toString(),
                            phone = editTextTextPassword.text.toString(),
                            password = (activity as MainActivity).password,
                            email = (activity as MainActivity).eMail,
                            userName = (activity as MainActivity).userName,
                        )
                        user.userId=usersId
                        mainViewModel.insertUser(
                            user
                        )
                    }
                }

                mainViewModel.getUser()
                toggleBottomSheet()
//                var login = binding!!.btmS!!.login
//                mainViewModel.insertLogin(Login(email = login!!.email,password = login!!.password))

            }
            R.id.fabUser -> {
                flag=0
                toggleBottomSheet()
                button2.setText("Add Details")
            }
            R.id.fabLogOff->{
                (activity as MainActivity).password=""
                (activity as MainActivity).eMail=""
                (activity as MainActivity).userName=""
                navController.navigate(R.id.fragmentOne)
            }
        }
    }

}