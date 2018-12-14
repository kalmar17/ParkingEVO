import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MyFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val context = activity!!.applicationContext
        val layout = LinearLayout(context)
        val image = ImageView(context)
        layout.addView(image)
        val layoutText = LinearLayout(context)
            layout.addView(layoutText)
        val text = TextView(context)
        text.text = "Это область фрагмента"
        layoutText.addView(text)

        return layout
    }
}