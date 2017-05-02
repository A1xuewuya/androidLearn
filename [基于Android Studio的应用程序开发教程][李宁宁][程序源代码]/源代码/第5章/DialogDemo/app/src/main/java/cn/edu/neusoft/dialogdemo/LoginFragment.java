package cn.edu.neusoft.dialogdemo;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends DialogFragment  implements OnEditorActionListener {

    public interface loginDialogListener {
        void onFinishEditDialog(String inputText);
    }
    private EditText usernameET;
    private EditText passwordET;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container);
        //loginButton = (Button) view.findViewById(R.id.login_button);
        usernameET = (EditText)view.findViewById(R.id.username);
        passwordET = (EditText)view.findViewById(R.id.password);
        getDialog().setTitle("Hello");
        usernameET.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        passwordET.setOnEditorActionListener(this);

        return view;
    }
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            loginDialogListener activity = (loginDialogListener) getActivity();
            activity.onFinishEditDialog(usernameET.getText().toString()+passwordET.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }

}
