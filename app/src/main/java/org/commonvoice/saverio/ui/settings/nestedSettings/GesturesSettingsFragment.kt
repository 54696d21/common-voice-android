package org.commonvoice.saverio.ui.settings.nestedSettings

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import org.commonvoice.saverio.databinding.FragmentGesturesSettingsBinding
import org.commonvoice.saverio.ui.viewBinding.ViewBoundFragment
import org.commonvoice.saverio.utils.setupOnSwipeRight
import org.commonvoice.saverio_lib.preferences.MainPrefManager
import org.koin.android.ext.android.inject

class GesturesSettingsFragment : ViewBoundFragment<FragmentGesturesSettingsBinding>() {

    override fun inflate(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGesturesSettingsBinding {
        return FragmentGesturesSettingsBinding.inflate(layoutInflater, container, false)
    }

    private val mainPrefManager by inject<MainPrefManager>()

    override fun onStart() {
        super.onStart()

        withBinding {
            buttonBackSettingsSubSectionGestures.setOnClickListener {
                activity?.onBackPressed()
            }

            if (mainPrefManager.areGesturesEnabled)
                nestedScrollSettingsGestures.setupOnSwipeRight(requireContext()) { activity?.onBackPressed() }

            switchSettingsSubSectionGestures.setOnCheckedChangeListener { _, isChecked ->
                mainPrefManager.areGesturesEnabled = isChecked

                if (isChecked) {
                    nestedScrollSettingsGestures.setupOnSwipeRight(requireContext()) { activity?.onBackPressed() }
                } else {
                    nestedScrollSettingsGestures.setupOnSwipeRight(requireContext()) { }
                }
            }
            switchSettingsSubSectionGestures.isChecked = mainPrefManager.areGesturesEnabled

            buttonGesturesLearnMore.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://bit.ly/3phQ0lP")))
            }
        }

        setTheme()
    }

    fun setTheme() {
        withBinding {
            theme.setElement(layoutSettingsGestures)

            theme.setElements(requireContext(), settingsSectionGestures)

            theme.setElement(requireContext(), 3, settingsSectionGestures)

            theme.setTitleBar(requireContext(), titleSettingsSubSectionGestures, textSize = 20F)
        }
    }
}