package com.loop.babushka.screen.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.loop.babushka.R;
import com.loop.babushka.screen.home.MainActivity;
import com.loop.babushka.utils.CommonUtils;
import com.loop.babushka.utils.Const;
import com.loop.babushka.utils.StoreDataHelper;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Alyona on 03.02.2018.
 */

public class PhoneLoginFragment  extends Fragment {
    public static final String TAG = "PhoneFragment";
    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";
    private static final String KEY_PHONE_VALUE = "key_phone_value";

    private Boolean isVerificationInProgress = false;
    private String phoneValue;
    private String phoneCode;
    private String isoCode;
    private String mVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private StoreDataHelper storeDataHelper;
    private Unbinder unbinder;

    @BindView(R.id.phone_login_layout)
    ConstraintLayout loginLayout;
    @BindView(R.id.code_login_layout)
    ConstraintLayout codeLayout;
    @BindView(R.id.next_btn)
    TextView nextBtn;
    @BindView(R.id.phone_code)
    TextView phoneCodeTv;
    @BindView(R.id.flag_view)
    TextView flagTv;
    @BindView(R.id.et_login_number)
    EditText phoneEt;
    @BindView(R.id.enter_btn)
    TextView loginBtn;
    @BindView(R.id.et_login_code)
    EditText codeEt;

    public static PhoneLoginFragment newInstance(String phoneCode, String isoCode) {
        Bundle args = new Bundle();
        args.putString(Const.ARG_PHONE_CODE, phoneCode);
        args.putString(Const.ARG_ISO_CODE, isoCode);
        PhoneLoginFragment fragment = new PhoneLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        Bundle args = getArguments();
        phoneCode = args.getString(Const.ARG_PHONE_CODE);
        isoCode = args.getString(Const.ARG_ISO_CODE);
        storeDataHelper = new StoreDataHelper(getActivity());
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification codeTv.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);
                // [START_EXCLUDE silent]
                isVerificationInProgress = false;
                // [END_EXCLUDE]

                // [START_EXCLUDE silent]
                // Update the UI and attempt sign in with the phone credential
                //updateUI(STATE_VERIFY_SUCCESS, credential);
                // [END_EXCLUDE]
                logInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.d(TAG, "onVerificationFailed", e);
                // [START_EXCLUDE silent]
                isVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                    phoneEt.setError("Invalid phone number.");
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Log.d(TAG, "Quota exceeded");

                    // [END_EXCLUDE]
                }

                // Show a message and update the UI
                // [START_EXCLUDE]
                //updateUI(STATE_VERIFY_FAILED);
                // [END_EXCLUDE]
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification codeTv has been sent to the provided phone number, we
                // now need to ask the user to enter the codeTv and then construct a credential
                // by combining the codeTv with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);
                Log.d(TAG, "onTokenSent:" + token);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                //mResendToken = token;

                // [START_EXCLUDE]
                // Update UI
                //updateUI(STATE_CODE_SENT);
                // [END_EXCLUDE]
            }
        };
        // [END phone_auth_callbacks]


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.phone_login_fragment, container, false);
        unbinder = ButterKnife.bind(this, v);
        Log.d(TAG, "OnCreateView");

        phoneCodeTv.setText(getString(R.string.phone_code, phoneCode));
        flagTv.setText(getString(R.string.emoji_flag, CommonUtils.getUnicodeFlag(isoCode)));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "OnActivityCreated");

        if (savedInstanceState != null) {
            isVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
            phoneValue = savedInstanceState.getString(KEY_PHONE_VALUE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "OnStart");
        Log.d(TAG, "is Verif onStart " + String.valueOf(isVerificationInProgress));
        if (isVerificationInProgress) {
            //request sms codeTv from  Firebase Auth Api
            requestSmsCode(phoneValue);
            //requestSmsCode("+380932025749");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, isVerificationInProgress);
        outState.putString(KEY_VERIFY_IN_PROGRESS, phoneValue);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "OnDestroyView");
        unbinder.unbind();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy");
    }

    @OnClick(R.id.next_btn)
    public void goNext() {
        if (!CommonUtils.isNetworkAvailable(getActivity())) {
            CommonUtils.showShortToast(getString(R.string.dialog_no_inter_message), getActivity());
            return;
        }
        phoneValue = phoneEt.getText().toString().trim();
        if (!TextUtils.isEmpty(phoneValue) && phoneValue.length() == 9) {
            phoneValue = phoneCode + phoneValue;
            CommonUtils.showShortToast(phoneValue, getActivity());
            requestSmsCode(phoneValue);
            //requestSmsCode("+380930127647");
            //requestSmsCode(TEMP_PHONE_VALUE);

            updateUi();
        } else {
            phoneEt.setError(getResources().getString(R.string.txt_phone_error));
            phoneEt.requestFocus();
        }

    }

    @OnClick(R.id.enter_btn)
    public void login() {
        if (!CommonUtils.isNetworkAvailable(getActivity())) {
            CommonUtils.showShortToast(getString(R.string.dialog_no_inter_message), getActivity());
            return;
        }
        String codeValue = codeEt.getText().toString().trim();
        if (!TextUtils.isEmpty(codeValue)) {
            //send sms codeTv to Firebase Auth Api
            sendSmsCode(codeValue, mVerificationId);
        } else {
            codeEt.setError(getResources().getString(R.string.txt_phone_error));
            codeEt.requestFocus();
        }
    }

    private void updateUi() {
        loginLayout.setVisibility(View.GONE);
        codeLayout.setVisibility(View.VISIBLE);
    }

    private void requestSmsCode(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,               // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),
                mCallbacks);        // OnVerificationStateChangedCallbacks
        isVerificationInProgress = true;
    }
    private void sendSmsCode(String code, String verificationId) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        logInWithPhoneAuthCredential(credential);
    }

    private void logInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithCredential:success");

                        FirebaseUser user = task.getResult().getUser();
                        Task<GetTokenResult> tokenTask = user.getIdToken(false);
                        String userToken = tokenTask.getResult().getToken();
                        Log.d(TAG, "user firebase token " + userToken);

                        if (credential.getSmsCode() != null) {
                            codeEt.setText(credential.getSmsCode());
                        }

                     //   storeDataHelper.storeUserToPrefs(response.body());
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        CommonUtils.showShortToast(task.getException().toString(), getActivity());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification codeTv entered was invalid
                        }
                    }
                });
    }

}
