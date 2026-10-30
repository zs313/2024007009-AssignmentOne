package nz.ac.waikato.campuslife;

import android.content.Intent;
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

    public static final String EXTRA_TOPIC_INDEX = "nz.ac.waikato.campuslife.TOPIC_INDEX";
    public static final String EXTRA_TOPIC_NAME = "nz.ac.waikato.campuslife.TOPIC_NAME";

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
        openDetail(view);
    }

    public void onEventsClick(View view) {
        openDetail(view);
    }

    public void onClubsClick(View view) {
        openDetail(view);
    }

    public void onSupportClick(View view) {
        openDetail(view);
    }

    private void openDetail(View view) {
        showTopicClick(view);

        // Both XML layouts use tags 0..3 in the supplied arrays' topic order.
        // A shared explicit Intent keeps all four handlers consistent.
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_TOPIC_INDEX, Integer.parseInt(view.getTag().toString()));
        intent.putExtra(EXTRA_TOPIC_NAME, view.getContentDescription().toString());
        startActivity(intent);
    }

    private void showTopicClick(View view) {
        String message = getString(R.string.topic_clicked, view.getContentDescription());
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
