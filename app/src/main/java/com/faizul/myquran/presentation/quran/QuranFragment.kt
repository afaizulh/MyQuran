package com.faizul.myquran.presentation.quran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.faizul.myquran.adapter.ListSurahAdapter
import com.faizul.myquran.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {
    private lateinit var binding: FragmentQuranBinding
    private val quranViewModel: QuranViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuranBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quranViewModel.getListSurah()
        val mAdapter = ListSurahAdapter()

        quranViewModel.listSurah.observe(viewLifecycleOwner) { surah ->
            mAdapter.setData(surah.listSurah)
            binding.rvQuran.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            binding.progressBar.visibility = View.GONE
        }
    }
}