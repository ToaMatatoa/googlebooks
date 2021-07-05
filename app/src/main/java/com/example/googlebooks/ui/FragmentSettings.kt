package com.example.googlebooks.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.googlebooks.Constants
import com.example.googlebooks.Constants.CHECK_ALL
import com.example.googlebooks.Constants.CHECK_BY_AUTHOR
import com.example.googlebooks.Constants.CHECK_BY_GENRE
import com.example.googlebooks.Constants.CHECK_BY_PUBLISHER
import com.example.googlebooks.Constants.CHECK_BY_TITLE
import com.example.googlebooks.R
import com.example.googlebooks.databinding.FragmentSettingsBinding
import org.kodein.di.Constant
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class FragmentSettings : Fragment(), KodeinAware {

    override val kodeinContext = kcontext<Fragment>(this)
    private val parentKodein: Kodein by closestKodein()
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
    }
    override val kodeinTrigger = KodeinTrigger()

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodeinTrigger.trigger()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        kodeinTrigger.trigger()

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSettings_to_fragmentBooks, null)
        }

        setSelectedFilter(viewModel.getBooksSetting())

        binding.tvSearchAll.setOnClickListener {
            setSelectedFilter(CHECK_ALL)
            if (binding.rbSearchAll.isChecked) viewModel.saveBooksSetting(CHECK_ALL)
        }

        binding.tvSearchAuthor.setOnClickListener {
            setSelectedFilter(CHECK_BY_AUTHOR)
            if (binding.rbSearchAuthor.isChecked) viewModel.saveBooksSetting(CHECK_BY_AUTHOR)
        }

        binding.tvSearchTitle.setOnClickListener {
            setSelectedFilter(CHECK_BY_TITLE)
            if (binding.rbSearchTitle.isChecked) viewModel.saveBooksSetting(CHECK_BY_TITLE)
        }

        binding.tvSearchGenre.setOnClickListener {
            setSelectedFilter(CHECK_BY_GENRE)
            if (binding.rbSearchGenre.isChecked) viewModel.saveBooksSetting(CHECK_BY_GENRE)
        }

        binding.tvSearchPublisher.setOnClickListener {
            setSelectedFilter(CHECK_BY_PUBLISHER)
            if (binding.rbSearchPublisher.isChecked) viewModel.saveBooksSetting(CHECK_BY_PUBLISHER)
        }
    }

    private fun setSelectedFilter(filter: String) = binding.rgSettingsCheck.check(
        when (filter) {
            CHECK_ALL -> R.id.rb_search_all
            CHECK_BY_AUTHOR -> R.id.rb_search_author
            CHECK_BY_TITLE -> R.id.rb_search_title
            CHECK_BY_GENRE -> R.id.rb_search_genre
            else -> R.id.rb_search_publisher
        }
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

