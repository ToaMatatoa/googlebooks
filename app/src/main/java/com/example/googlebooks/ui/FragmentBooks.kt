package com.example.googlebooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
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

    private var bookAdapter: BookAdapter? = null
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

        bookAdapter = BookAdapter()

        viewModel.liveDataRemoteProvider.observe(viewLifecycleOwner, Observer { books ->
            bookAdapter?.addBooks(books)
            binding.ivNoBooksPlaceholder.visibility = if (books.isNotEmpty())
                INVISIBLE else VISIBLE
            if (binding.etSearch.text.isEmpty())
                bookAdapter?.addBooks(emptyList())
        })

        viewModel.getBooks(viewModel.getEnteredText())

        binding.imgMenu.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentBooks_to_fragmentSettings, null)
        }

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            binding.rvList.adapter = bookAdapter
        }

        binding.etSearch.setText(viewModel.getEnteredText())

        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.getBooks(text.toString())
            viewModel.saveEnteredText(text.toString())
            binding.imgClose.visibility = if (text!!.isNotEmpty())
                VISIBLE else INVISIBLE
        }

        binding.etSearch.setOnFocusChangeListener { view, _ ->
            if (view.isFocused) {
                binding.etSearch.setHintTextColor(resources.getColor(R.color.light_gray))
            } else binding.etSearch.setHintTextColor(resources.getColor(R.color.white))
        }

        binding.imgClose.setOnClickListener {
            binding.etSearch.setText("")
        }

        binding.rvList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}