package com.demo.nytimesapp.presentations.signup

import android.util.Patterns
import com.demo.core.bases.BaseViewModel
import com.demo.core.bases.BaseViewState
import com.demo.core.bases.ResultWrapper
import com.demo.nytimesapp.domain.signup.model.User
import com.demo.nytimesapp.domain.signup.usecases.GetSignUpDataUseCase
import com.demo.nytimesapp.domain.signup.usecases.SetSignUpDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val setSignUpDataUseCase: SetSignUpDataUseCase,
    private val getSignUpDataUseCase: GetSignUpDataUseCase
) : BaseViewModel() {

    private var _signUpSuccess: MutableSharedFlow<Boolean?> =
        MutableSharedFlow(replay = 1)
    val signUpSuccess: SharedFlow<Boolean?> =
        _signUpSuccess.asSharedFlow()


    private var _validationError: MutableSharedFlow<ValidationViewState> =
        MutableSharedFlow(replay = 1)
    val validationError: SharedFlow<ValidationViewState> =
        _validationError.asSharedFlow()

    fun doValidationTheSignUp(
        fullName: String,
        email: String,
        nationalId: String,
        phoneNumber: String,
        dateOfBirth: String,
        password: String
    ) {
        launchCoroutine(coroutineExceptionHandler) {
            when {
                fullName.isEmpty() -> {
                    _validationError.emit(ValidationViewState.FullNameEmpty)
                }
                email.isEmpty() -> {
                    _validationError.emit(ValidationViewState.EmailEmpty)
                }
                ! Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    _validationError.emit(ValidationViewState.EmailNotMatch)
                }
                nationalId.isEmpty() -> {
                    _validationError.emit(ValidationViewState.NationalIdEmpty)
                }
                phoneNumber.isEmpty() -> {
                    _validationError.emit(ValidationViewState.PhoneNumberEmpty)
                }
                dateOfBirth.isEmpty() -> {
                    _validationError.emit(ValidationViewState.DateOfBirthEmpty)
                }
                password.isEmpty() -> {
                    _validationError.emit(ValidationViewState.PasswordEmpty)
                }

                else -> {
                    val user = User(
                        fullName = fullName,
                        email = email,
                        dateOfBirth = dateOfBirth,
                        nationalId = nationalId,
                        phoneNumber = phoneNumber,
                        password = password
                    )
                    isUserSignedUp(
                        user
                    )
                }
            }
        }
    }

    private fun isUserSignedUp(
        user: User
    ) {
        launchCoroutine(coroutineExceptionHandler) {
            getSignUpDataUseCase(user.email)
                ?.onStart {
                    _state.emit(BaseViewState.Loading)
                }
                ?.onCompletion {
                    _state.emit(BaseViewState.DataLoaded)
                }?.collect { result ->
                    when (result) {
                        is ResultWrapper.Success -> {
                            if (result.data == null) {
                                doSignUp(user)
                            } else {
                                _validationError.emit(ValidationViewState.EmailAlreadyExist)
                            }
                        }
                        is ResultWrapper.Error -> {
                            _state.emit(
                                BaseViewState.Error(
                                    result.error
                                )
                            )
                        }
                    }
                }
        }
    }

    private fun doSignUp(
        user: User
    ) {
        launchCoroutine(coroutineExceptionHandler) {
            setSignUpDataUseCase(user)
                ?.onStart {
                    _state.emit(BaseViewState.Loading)
                }
                ?.onCompletion {
                    _state.emit(BaseViewState.DataLoaded)
                }
                ?.collect { result ->
                    when (result) {
                        is ResultWrapper.Success -> {
                            _signUpSuccess.emit(true)
                        }
                        is ResultWrapper.Error -> {
                            _state.emit(
                                BaseViewState.Error(
                                    result.error
                                )
                            )
                        }
                    }
                }
        }
    }

}