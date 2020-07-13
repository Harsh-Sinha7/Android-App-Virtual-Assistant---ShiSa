package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentification;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

public class Translator extends AppCompatActivity {
    private TextView mSourceLang, mTransText;
    private EditText mSourceText;
    private Button mTranslatedBtn;
    private String sourceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);
        mSourceLang =findViewById(R.id.detectText);
        mSourceText = findViewById(R.id.sourceText);
        mTranslatedBtn = findViewById(R.id.translateButton);
        mTransText = findViewById(R.id.translateText);

        mTranslatedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identifyLanguage();
            }
        });
    }
    private void identifyLanguage() {
        sourceText = mSourceText.getText().toString();

        FirebaseLanguageIdentification identifier = FirebaseNaturalLanguage.getInstance().getLanguageIdentification();
        mSourceLang.setText("Detecting the Language");

        identifier.identifyLanguage(sourceText).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                //check ki language supported by firebase or not.
                // if not returns und
                if(s.equals("und")){
                    Toast.makeText(Translator.this,"Language Entered is Not Supported", Toast.LENGTH_LONG ).show();
                }
                else{
                    getLanguageCode(s);
                }

            }
        });
    }
    private void getLanguageCode(String language) {
        int langCode ;
        switch(language){
            //hi is bcp-47 code
            //lanCode is integer Code
            case "hi":
                langCode = FirebaseTranslateLanguage.HI;
                mSourceLang.setText("Hindi");
                break;
            case "de" :
                langCode = FirebaseTranslateLanguage.DE;
                mSourceLang.setText("German / Deutsch");
                break;
            case "fr" :
                langCode = FirebaseTranslateLanguage.FR;
                mSourceLang.setText("French");
                break;
            case "zh" :
                langCode = FirebaseTranslateLanguage.ZH;
                mSourceLang.setText("Chinese");
                break;
            case "el" :
                langCode = FirebaseTranslateLanguage.EL;
                mSourceLang.setText("Greek");
                break;
            case "gu" :
                langCode = FirebaseTranslateLanguage.GU;
                mSourceLang.setText("Gujrati");
                break;
            case "it" :
                langCode = FirebaseTranslateLanguage.IT;
                mSourceLang.setText("Italian");
                break;
            case "nl" :
                langCode = FirebaseTranslateLanguage.NL;
                mSourceLang.setText("Dutch");
                break;
            default :
                langCode = 0;
                mSourceLang.setText("Not found");

        }
        translateText(langCode);
    }
    private void translateText(int langCode) {

        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(langCode)
                .setTargetLanguage(FirebaseTranslateLanguage.EN)
                .build();


        final FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance()
                .getTranslator(options);

        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().build();
        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                translator.translate(sourceText).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s1) {
                        mTransText.setText(s1);
                    }
                });
            }
        });
    }
}
