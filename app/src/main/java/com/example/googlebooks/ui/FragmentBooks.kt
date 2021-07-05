package com.example.googlebooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlebooks.R
import com.example.googlebooks.databinding.FragmentBooksBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class FragmentBooks : Fragment(R.layout.fragment_books), KodeinAware {

    override val kodeinContext = kcontext<Fragment>(this)
    private val parentKodein: Kodein by closestKodein()
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
    }
    override val kodeinTrigger = KodeinTrigger()

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private var adapterRV = Adapter()
    private val viewModel: ViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        kodeinTrigger.trigger()

        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgMenu.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentBooks_to_fragmentSettings, null)
        }

//        viewModel.getBooks()
//        viewModel.liveDataRemoteProvider.observe(viewLifecycleOwner, Observer {
//            adapterRV.addBooks(it)
//        })

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            binding.rvList.adapter = adapterRV
        }


        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            binding.imgClose.visibility = if (text!!.isNotEmpty())
                VISIBLE else INVISIBLE
        }

        binding.imgClose.setOnClickListener {
            binding.etSearch.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}