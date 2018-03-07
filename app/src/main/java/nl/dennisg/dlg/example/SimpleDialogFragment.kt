package nl.dennisg.dlg.example

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog

class SimpleDialogFragment: DialogFragment() {

    private lateinit var callback: DialogResultListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callback = if (targetFragment != null) {
            object : DialogResultListener {
                override fun onDialogResult(requestCode: Int, resultCode: Int, data: Intent?) {
                    targetFragment.onActivityResult(targetRequestCode, resultCode, data)
                }
            }
        } else {
            // will throw an exception
            activity as DialogResultListener
        }
    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity!!)
                .setTitle("Example Dialog")
                .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        callback.onDialogResult(targetRequestCode, Activity.RESULT_OK, null)
                    }
                })
                .setNegativeButton("Cancel", object: DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        callback.onDialogResult(targetRequestCode, Activity.RESULT_CANCELED, null)
                    }
                }).create()
    }

    interface DialogResultListener {
        fun onDialogResult(requestCode: Int, resultCode: Int, data: Intent?)
    }


    companion object {

        //call from activity
        fun newSimpleDialogFragment(requestCode: Int): Fragment {
            return  newSimpleDialogFragment(null, requestCode)
        }

        //call from fragment
        fun newSimpleDialogFragment(target: Fragment?, requestCode: Int): Fragment {
            return SimpleDialogFragment().apply {
                setTargetFragment(target, requestCode)
            }
        }
    }
}
