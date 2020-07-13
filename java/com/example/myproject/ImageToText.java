package com.example.myproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;

public class ImageToText extends AppCompatActivity {
    private TextView textV;
    private ImageView imageV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_text);
        textV = findViewById(R.id.textView);
        imageV = findViewById(R.id.imageView2);
        Button uploadBtn = findViewById(R.id.upload);
        Button copyBtn = findViewById(R.id.copy);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });
        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String copiedText = textV.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText("Image Copied", copiedText);
                clipboardManager.setPrimaryClip(data);
                Toast.makeText(ImageToText.this, "Text Copied", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != ImageToText.RESULT_OK) {
            return;
        } else {
            Log.i("Info", "else loop ke andar hai tu");

            try {
                imageV.setImageURI(data.getData());
                FirebaseVisionImage image = FirebaseVisionImage
                        .fromFilePath(ImageToText.this, data.getData());
                FirebaseVisionTextRecognizer recognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
                recognizer.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        String text = firebaseVisionText.getText();
                        textV.setText("");
                        textV.setMovementMethod(new ScrollingMovementMethod());
                        for (FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks()) {
                            Log.i("Info", "For loop ke andar");
                            textV.append("\n\n" + block.getText());
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
