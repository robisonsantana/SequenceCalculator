package guy.voltaic.sequencecalculator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import guy.voltaic.sequencecalculator.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private TextView textView;
    private EditText etNumero;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        etNumero = findViewById(R.id.etNumero);
        etNumero.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        button.setOnClickListener(op -> calcular());
    }

    private void calcular(){
        try {
            int number = Integer.parseInt(etNumero.getText().toString());
            if (number < 1 || number > 19) {
                textView.setText("Digite um número no intervalo estipulado.");
                return;
            }

            double resultadoFinal = 0;
            for (int i = 1; i <= number; i++) {
                if (i % 2 != 0) {
                    resultadoFinal += (double) i / Math.pow(i, 2);
                } else {
                    resultadoFinal -= (double) i / Math.pow(i, 2);
                }
            }

            textView.setText(String.valueOf(resultadoFinal));
        } catch (NumberFormatException e) {
            textView.setText("Por favor, digite um número válido.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}