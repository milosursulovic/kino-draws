package com.example.mozzartkino.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mozzartkino.R
import com.example.mozzartkino.databinding.FragmentWebClientBinding

class WebClientFragment : Fragment() {
    private lateinit var binding: FragmentWebClientBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWebClientBinding.bind(view)
        val args: WebClientFragmentArgs by navArgs()
        val url = args.url

        binding.wvInfo.run {
            webViewClient = WebViewClient()
            if (url != "") {
                loadUrl(url)
            }
        }
    }

}