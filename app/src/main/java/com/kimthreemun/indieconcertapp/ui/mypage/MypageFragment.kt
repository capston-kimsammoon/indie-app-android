package com.kimthreemun.indieconcertapp.ui.mypage

import android.app.AlertDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.core.content.ContextCompat

import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R

class MypageFragment : Fragment() {

    private val viewModel: MypageViewModel by viewModels()

    private lateinit var tvUserName: TextView
    private lateinit var ivNameEdit: ImageView

    private lateinit var ivProfile: ImageView
    private lateinit var ivProfileEdit: ImageView

    private lateinit var btnNotification: Button
    private lateinit var btnLocation: Button

    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            Glide.with(this)
                .load(it)
                .circleCrop()
                .into(ivProfile)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNotification = view.findViewById(R.id.btnNotification)
        btnLocation = view.findViewById(R.id.btnLocation)

        tvUserName = view.findViewById(R.id.tvUserName)
        ivNameEdit = view.findViewById(R.id.ivNameEdit)
        ivProfile = view.findViewById(R.id.ivProfile)
        ivProfileEdit = view.findViewById(R.id.ivProfileEdit)

        viewModel.isNotificationOn.observe(viewLifecycleOwner, Observer {
            btnNotification.text = if (it) "ON" else "OFF"
            btnNotification.isSelected = it
        })

        viewModel.isLocationOn.observe(viewLifecycleOwner, Observer {
            btnLocation.text = if (it) "ON" else "OFF"
            btnLocation.isSelected = it
        })

        btnNotification.setOnClickListener {
            viewModel.toggleNotification()
        }

        btnLocation.setOnClickListener {
            viewModel.toggleLocation()
        }

        ivNameEdit.setOnClickListener {
            showEditNameDialog()
        }

        ivProfileEdit.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }
    }

    private fun showEditNameDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_name, null)
        val editText = dialogView.findViewById<EditText>(R.id.etNewName)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("닉네임 수정")
            .setView(dialogView)
            .setPositiveButton("확인", null)
            .setNegativeButton("취소", null)
            .create()

        dialog.setOnShowListener {
            // 버튼 색상 설정
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.theme_orange)) // 원하는 색

            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                ?.setTextColor(ContextCompat.getColor(requireContext(), R.color.theme_orange)) // 원하는 색

            // 버튼 클릭 리스너 설정
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val newName = editText.text.toString().trim()
                if (newName.isNotEmpty()) {
                    tvUserName.text = newName
                    dialog.dismiss()
                } else {
                    Toast.makeText(requireContext(), "이름을 입력하세요", Toast.LENGTH_SHORT).show()
                }
            }
        }

        dialog.show()
    }

}
