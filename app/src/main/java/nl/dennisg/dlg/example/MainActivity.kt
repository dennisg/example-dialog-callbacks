package nl.dennisg.dlg.example

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), SimpleDialogFragment.DialogResultListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
                .add(SimpleDialogFragment.newSimpleDialogFragment(1), null)
                .commit()

        println("showing the dialog from the activity")
    }

    override fun onDialogResult(requestCode: Int, resultCode: Int, data: Intent?) {
       println("in activity: requestCode= $requestCode, resultCode= $resultCode")

        //irrespective of the result let's add the fragment
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_target, InnerFragment())
                .commit()

    }
}
