package prre.ttrr.com.azmetov_ramil.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prre.ttrr.com.azmetov_ramil.R;

public class ErrorDialogFragment extends DialogFragment implements View.OnClickListener {
    private OnErrorDialogButtonClickListener mListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_error_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle(getString(R.string.error));
        view.findViewById(R.id.fragment_error_btn_repeat).setOnClickListener(this);
        view.findViewById(R.id.fragment_error_btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_error_btn_repeat: {
                if (mListener != null) {
                    mListener.onOkClicked();
                }
                dismiss();
                break;
            }
            case R.id.fragment_error_btn_cancel: {
                if (mListener != null) {
                    mListener.onCancelClicked();
                }
                dismiss();
                break;
            }
        }
    }

    public void setListener(OnErrorDialogButtonClickListener listener) {
        mListener = listener;
    }

    public interface OnErrorDialogButtonClickListener {
        void onOkClicked();

        void onCancelClicked();
    }
}
