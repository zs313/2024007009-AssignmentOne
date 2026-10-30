package nz.ac.waikato.campuslife;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

/** The Java entry point for the assignment's XML-based home screen. */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        // Use the device's system-bar insets instead of fixed padding values.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()
                    | WindowInsetsCompat.Type.displayCutout());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // XML onClick handlers must be public and accept the clicked View.
    public void onFacilitiesClick(View view) {
        showTopicClick(view);
    }

    public void onEventsClick(View view) {
        showTopicClick(view);
    }

    public void onClubsClick(View view) {
        showTopicClick(view);
    }

    public void onSupportClick(View view) {
        showTopicClick(view);
    }

    private void showTopicClick(View view) {
        String message = getString(R.string.topic_clicked, view.getContentDescription());
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
