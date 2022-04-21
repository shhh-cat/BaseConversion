package com.blackcat.baseconversion;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blackcat.baseconversion.databinding.ActivityMainBinding;
import com.blackcat.baseconversion.model.BaseName;
import com.blackcat.baseconversion.model.Number;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binding;
    private TextInputLayout src,des,input;
    private MaterialAutoCompleteTextView srcTextView, desTextView;
    private MaterialButton convertBtn,resetBtn,reverseBtn;
    private MaterialTextView outputTitle, outputContent;
    private String srcBase = "10", desBase = "2";
    private String inputString = "";
    private final String[] bases = new String[] {
      "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"
    };

    @SuppressLint("SetTextI18n")
    private void convert() {
        if (inputString.isEmpty())
            new MaterialAlertDialogBuilder(this, com.google.android.material.R.style.ThemeOverlay_Material3_MaterialAlertDialog_Centered)
                    .setTitle("Error")
                    .setMessage("Input is empty").show();
        else {
            outputTitle.setText("Output ["+ BaseName.get(Integer.parseInt(srcBase)) + " to " + BaseName.get(Integer.parseInt(desBase)) +"]:");
            Number number = new Number(inputString,Integer.parseInt(srcBase));
            outputContent.setText(number.toBase(Integer.parseInt(desBase)));
        }
    }

    private void reverse() {
        String temp = outputContent.getText().toString();
        input.getEditText().setText(temp);
        String tempBase = srcTextView.getText().toString();
        srcTextView.setText(desTextView.getText().toString(),false);
        desTextView.setText(tempBase,false);
        srcBase = srcTextView.getText().toString();
        desBase = desTextView.getText().toString();
        convert();
    }

    private void reset() {
        input.getEditText().setText("");
        outputContent.setText("");
        outputTitle.setText("Output:");
        srcTextView.setText("10",false);
        desTextView.setText("2",false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        src = binding.chooseSourceBase;
        des = binding.chooseDestinationBase;
        srcTextView = binding.chooseSourceBaseTextField;
        desTextView = binding.chooseDestinationBaseTextField;
        input = binding.inputTextField;
        convertBtn = binding.convertButton;
        resetBtn = binding.resetButton;
        reverseBtn = binding.reverseButton;
        outputTitle = binding.outputTitleTextview;
        outputContent = binding.outputContentTextview;


        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.item_dropdown,bases);
        srcTextView.setAdapter(adapter);
        desTextView.setAdapter(adapter);

        srcTextView.setText("10",false);
        desTextView .setText("2",false);
        outputContent.setText("");

        convertBtn.setOnClickListener(this);
        reverseBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);

        src.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                srcBase = editable.toString().trim();
            }
        });

        des.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //
            }

            @Override
            public void afterTextChanged(Editable editable) {
                desBase = editable.toString().trim();
            }
        });

        input.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable == null)
                    inputString = "";
                else
                    inputString = editable.toString().trim();
            }
        });



    }

    @Override
    public void onClick(View view) {
        if (view.getId() == convertBtn.getId()) convert();
        if (view.getId() == resetBtn.getId()) reset();
        if (view.getId() == reverseBtn.getId()) reverse();

    }
}