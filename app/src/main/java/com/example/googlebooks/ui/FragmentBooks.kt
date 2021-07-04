package com.example.googlebooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.googlebooks.R
import com.example.googlebooks.databinding.FragmentBooksBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.closestKodein
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        kodeinTrigger.trigger()

        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}