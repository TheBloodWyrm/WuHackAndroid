package hackwu.wuhack;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity
{
    Spinner spinnerOptions, spinnerAll;
    Button btSearch;
    WebView wvLessons;
    String uname = "";
    char[] password;
    ScheduleModel model = new ScheduleModel();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerOptions = (Spinner) findViewById(R.id.spinnerOption);
        spinnerAll = (Spinner) findViewById(R.id.spinnerAll);
        btSearch = (Button) findViewById(R.id.btSearch);
        wvLessons = (WebView) findViewById(R.id.wvLessons);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText passwd = (EditText) findViewById(R.id.password);

        LayoutInflater li = LayoutInflater.from(getApplicationContext());
        View alertDialogView = li.inflate(R.layout.alertdialog, null);

        /*new AlertDialog.Builder(MainActivity.this, R.style.AppCompatAlertDialogStyle)
                .setView(alertDialogView)
                .setCancelable(false)
                .setTitle("Bitte einloggen")

                .setPositiveButton("Login", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        uname = username.getText().toString();
                        password = passwd.getText().toString().toCharArray();

                        if (!uname.equals("") && !(password.length == 0))
                        {
                            //Authenticator.setDefault(new AuthenticatorTest(uname, password));

                            //model.loadAllLessons(wvLessons, getCalendarWeek());

                            btSearch.setText("Update");

                        } else
                        {
                            Toast.makeText(getApplicationContext(), "Please login", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                })
                .setIcon(R.mipmap.ic_action)
                .show();*/

        ArrayAdapter<CharSequence> adapterOptions = ArrayAdapter.createFromResource(this,
                R.array.spinnerOptionsArray, android.R.layout.simple_spinner_dropdown_item);
        adapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptions.setAdapter(adapterOptions);

        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (id == 0)
                {
                    ArrayAdapter<CharSequence> adapterKlassen = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.spinnerKlassenArray, R.layout.spinner_item);
                    adapterKlassen.setDropDownViewResource(R.layout.spinner_item);
                    spinnerAll.setAdapter(adapterKlassen);

                    String str = spinnerAll.getSelectedItem().toString();

                    //HTMLModel.convertToHTMLv3(wvLessons, model.getClassLessons(str), "Klasse - " + str, getCalendarWeek());



                } else if (id == 1)
                {
                    ArrayAdapter<CharSequence> adapterLehrer = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.spinnerTeacherArray, R.layout.spinner_item);
                    adapterLehrer.setDropDownViewResource(R.layout.spinner_item);
                    spinnerAll.setAdapter(adapterLehrer);

                    String str = spinnerAll.getSelectedItem().toString();

                    //HTMLModel.convertToHTMLv3(wvLessons, model.getTeacherLessons(str), "Lehrer - " + str, getCalendarWeek());
                } else if (id == 2)
                {
                    ArrayAdapter<CharSequence> adapterRaum = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.spinnerRaumArray, R.layout.spinner_item);
                    adapterRaum.setDropDownViewResource(R.layout.spinner_item);
                    spinnerAll.setAdapter(adapterRaum);

                    String str = spinnerAll.getSelectedItem().toString();

                    //HTMLModel.convertToHTMLv3(wvLessons, model.getClassroomLessons(str), "Raum - " + str, getCalendarWeek());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        btSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                wvLessons.loadUrl("www.facebook.at");

            }
        });
    }

    private int getCalendarWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int week = cal.get(Calendar.WEEK_OF_YEAR);

        return week;
    }

}

