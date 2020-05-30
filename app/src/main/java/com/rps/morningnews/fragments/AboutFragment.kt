package com.rps.morningnews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rps.morningnews.R
import com.rps.morningnews.databinding.AboutFragmentBinding

class AboutFragment: Fragment() {

    lateinit var aboutFragmentBinding: AboutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutFragmentBinding = DataBindingUtil.inflate<AboutFragmentBinding>(
            inflater,
            R.layout.about_fragment,
            container,
            false
        )

        return aboutFragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAboutContent()
    }

    private fun setAboutContent() {
        var content = "This Application  will give you updates on daily activities which all are happening around us." +
                "\n The applicaction shows Top-Headlines with share option on each news and allows user to share the news link via whatsapp ," +
                "\n message etc... \n   Other than this the app also shows the categories options where you can see a list of categories such as " +
                " Sports/Entertainment/Health etc.... and once the user clicks on any category the corresponding categories news will be loaded." +
                "\n All these data are coming from newsapi.org website."
        aboutFragmentBinding.tvContent.text = content
    }

    companion object {
        @JvmStatic
        fun newInstance(): AboutFragment = AboutFragment()

    }
}