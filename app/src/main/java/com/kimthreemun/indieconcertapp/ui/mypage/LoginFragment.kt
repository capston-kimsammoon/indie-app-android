package com.kimthreemun.indieconcertapp.ui.mypage

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.dto.user.KakaoLoginRequest
import com.kimthreemun.indieconcertapp.data.remote.api.AuthService
import com.kimthreemun.indieconcertapp.data.remote.api.RetrofitClient
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val authService: AuthService = RetrofitClient.authService

    private val kCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
            sendAccessTokenToBackend(token.accessToken)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton = view.findViewById<ImageButton>(R.id.btn_kakao_login)

        loginButton.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡 로그인 실패", error)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) return@loginWithKakaoTalk
                        UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = kCallback)
                    } else if (token != null) {
                        Log.i(TAG, "카카오톡 로그인 성공 ${token.accessToken}")
                        sendAccessTokenToBackend(token.accessToken)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = kCallback)
            }
        }
    }

    private fun sendAccessTokenToBackend(accessToken: String) {
        lifecycleScope.launch {
            try {
                val response = authService.loginWithKakao(KakaoLoginRequest(accessToken))

                if (response.isSuccessful) {
                    response.body()?.let { loginResponse ->
                        Log.i(TAG, "서버 로그인 성공: userId=${loginResponse.userId}, jwt=${loginResponse.jwtToken}")
                        // TODO: 여기에서 토큰을 저장하거나 로그인 완료 UI로 전환하면 됨
                        goToMyPage()
                    } ?: run {
                        Log.e(TAG, "서버 응답은 성공했지만 body가 null임")
                    }
                } else {
                    Log.e(TAG, "서버 응답 실패: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "서버 로그인 실패", e)
            }
        }
    }

    private fun goToMyPage() {
        findNavController().navigate(R.id.mypageFragment)
    }

}
