package com.example.v11t;

import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
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
    private TextView readField, rfTitle;
    private ViewGroup.LayoutParams params;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_textview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        writeField = view.findViewById(R.id.writeView);
        readField = view.findViewById(R.id.readView);
        rfTitle = view.findViewById(R.id.readViewTitle);
        writeField.setEnabled(true);
        params = readField.getLayoutParams();
        super.onViewCreated(view, savedInstanceState);
    }

    public void changeTitle(String title) {
        rfTitle.setText(title);
    }

    public void changeFont(int fontsize) {
        readField.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontsize);
    }

    public void changeWidth(int width) {
        params.width = width;
        readField.setLayoutParams(params);
    }

    public void changeHeight(int height) {
        params.height = height;
        readField.setLayoutParams(params);
    }

    public void changeRowCount(int count) {
        readField.setLines(count);
    }

    public void changeEnabled() {
        readField.setText("");
        writeField.setEnabled(true);
        writeField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
    }

    //disable EditText field and pass text to TextView
    public void changeDisabled() {
        readField.setText(writeField.getText().toString());
        writeField.setEnabled(false);
        writeField.setInputType(InputType.TYPE_NULL);
    }


}
