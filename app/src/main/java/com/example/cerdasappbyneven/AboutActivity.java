package com.example.cerdasappbyneven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView jawaban, soal;
    Button nextbtn, backbtn;
    Integer flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        soal = findViewById(R.id.soal);
        jawaban = findViewById(R.id.jawaban);
        nextbtn = findViewById(R.id.nextbtn);
        backbtn = findViewById(R.id.backbtn);

        backbtn.setVisibility(View.GONE);
        backbtn.setClickable(false);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag++;
                if(flag==1)
                {
                    soal.setText("Penjelasan Aplikasi");
                    jawaban.setText("Aplikasi ini berfungsi sebagai platform untuk manager/atasan ataupun semua anak buahnya untuk melihat, menghapus, melakukan perubahan" +
                            " terkait data para pekerja yang ada pada suatu universitas. Aplikasi ini juga dapat dijadikan sebagai sebuah database para pekerja di universitas ini. Terdapat fitur registrasi dan login, beserta fitur role" +
                            " dan juga settings yang dapat dimanfaatkan.");
                }
                else if(flag==2)
                {
                    soal.setText("Proses Pembuatan Aplikasi");
                    jawaban.setText("...");
                }
                else if(flag==3)
                {
                    soal.setText("");
                    jawaban.setText("Saya mengerjakan ini sendirian.");
                }
                else if(flag==4)
                {
                    soal.setText("");
                    jawaban.setText("Library external yang digunakan hanya library Firebase, sisanya menggunakan methods/functions native.");
                }
                else if(flag==5)
                {
                    soal.setText("Downs and Ups");
                    jawaban.setText("Downs: Soal yang terlalu banyak dalam waktu yang tidak cukup sehingga tidak bisa 100% selesai dan maksimal," +
                            " sarana yang tidak mendukung karena walaupun sudah install ke Smartphone, build dan installingnya memakan waktu yang sangat lama" +
                            "\nUps: Projek akhir teori sangat membantu penyelesaian UAS praktek ini, project take home memudahkan pencarian " +
                            " referensi di internet yang luas.");
                }
                else if(flag==6)
                {
                    soal.setText("Keluh Kesah Kelas Mobile");
                    jawaban.setText("Hanya sarana yang kurang mendukung (komputer di lab dan laptop sendiri yang memiliki kendala). ");
                }
                else if(flag==7)
                {
                    soal.setText("Kritik dan Saran");
                    jawaban.setText("Tidak ada. Semangat terus dan terima kasih atas pengabdiannya.");
                    nextbtn.setText("Return to beginning");
                    backbtn.setVisibility(View.VISIBLE);
                    backbtn.setClickable(true);
                }
                else if(flag==8)
                {
                    nextbtn.setText("Next Part");
                    flag = 0;
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutActivity.this.finish();
            }
        });

    }
}
