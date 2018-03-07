package nl.dennisg.dlg.example

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nl.dennisg.dlg.example.databinding.FragmentInnerBinding

class InnerFragment: Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentInnerBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentInnerBinding.inflate(layoutInflater).apply {
            listener = this@InnerFragment
        }

        return binding.root
    }

    override fun onClick(p0: View?) {
        println("showing the dialog from the fragment")

        val fragment = SimpleDialogFragment.newSimpleDialogFragment(this, 1)
        activity.supportFragmentManager.beginTransaction().add(fragment, null).commit()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        println("in fragment: requestCode = $requestCode, resultCode: $resultCode")
    }
}