package com.example.v11t;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TextViewFragment extends Fragment {

    private EditText writeField;
    private TextView readField;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_textview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        writeField = view.findViewById(R.id.writeView);
        readField = view.findViewById(R.id.readView);
        writeField.setEnabled(true);
        super.onViewCreated(view, savedInstanceState);
    }

    public void changeEnabled(){
        writeField.setEnabled(true);
    }
    public void changeDisabled(){
        writeField.setEnabled(false);
    }
}
