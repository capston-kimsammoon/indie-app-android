package com.kimthreemun.indieconcertapp.common.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kimthreemun.indieconcertapp.R
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.JustifyContent

fun Fragment.showOptionBottomSheet(
    title: String,
    options: List<String>,
    isMultiSelect: Boolean = false,
    preSelected: Set<String> = emptySet(),
    onSelect: (selected: List<String>) -> Unit
) {
    val dialog = BottomSheetDialog(requireContext())
    val view = LayoutInflater.from(requireContext())
        .inflate(R.layout.bottom_sheet_options_flexbox, null)

    val titleView = view.findViewById<TextView>(R.id.tvBottomSheetTitle)
    val container = view.findViewById<FlexboxLayout>(R.id.flexOptionContainer)

    titleView.text = title
    val selectedSet = preSelected.toMutableSet()

    // ✅ Flexbox 속성 적용
    container.flexDirection = FlexDirection.ROW
    container.flexWrap = FlexWrap.WRAP
    container.justifyContent = JustifyContent.FLEX_START

    options.forEach { option ->
        val chip = TextView(requireContext()).apply {
            text = option
            setPadding(32, 16, 32, 16)
            background = ContextCompat.getDrawable(
                requireContext(),
                if (selectedSet.contains(option))
                    R.drawable.bg_chip_selected
                else
                    R.drawable.bg_chip_unselected
            )
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

            layoutParams = FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 48, 24)
            }

            setOnClickListener {
                if (isMultiSelect) {
                    if (selectedSet.contains(option)) {
                        selectedSet.remove(option)
                        background = ContextCompat.getDrawable(
                            requireContext(), R.drawable.bg_chip_unselected
                        )
                    } else {
                        selectedSet.add(option)
                        background = ContextCompat.getDrawable(
                            requireContext(), R.drawable.bg_chip_selected
                        )
                    }
                    onSelect(selectedSet.toList())
                } else {
                    onSelect(listOf(option))
                    dialog.dismiss()
                }
            }
        }

        container.addView(chip)
    }

    dialog.setContentView(view)
    dialog.show()
}
