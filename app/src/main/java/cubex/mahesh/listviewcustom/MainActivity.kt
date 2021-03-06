package cubex.mahesh.listviewcustom

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

var status = ContextCompat.checkSelfPermission(this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
 if(status==PackageManager.PERMISSION_GRANTED){
     readFiles()
 }else{
     ActivityCompat.requestPermissions(this,
             arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
             11)
 }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
               permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            readFiles()
        }else{
            Toast.makeText(this,
                    "U can't continue with App",
                    Toast.LENGTH_LONG).show()
        }
    }




    fun  readFiles( ){
        var path = "/storage/sdcard0/WhatsApp/Media/WhatsApp Images/"
        var file = File(path)
        if(!file.exists()){
            path= "/storage/emulated/0/WhatsApp/Media/WhatsApp Images/"
            file = File(path)
        }
       var files = file.listFiles()
      /*  var myadapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,
                files) */
        lview.adapter = MyAdapter(files,this)
    }
}
