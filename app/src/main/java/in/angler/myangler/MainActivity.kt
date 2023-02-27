package `in`.angler.myangler

import `in`.angler.myangler.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyEmployeeAdapter
    private  var employeeList= mutableListOf<EmployeeData>()
    private var empList= mutableListOf<EmployeeData>()
    private  var searchId="0"
    val searchList = mutableListOf<EmployeeData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        binding.searchView.doOnTextChanged { text, start, before, count ->
            searchReport()
        }

        binding.filter.setOnClickListener{
            searchOptions()


        }

    }
    private fun searchOptions() {
        val options = arrayOf<CharSequence>("Name", "Employee Id","Mobile")
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle("Select Search Options")
        builder.setItems(options) { dialog, item ->

            if (options[item] == "Name") {
                searchId="1"
                binding.searchView.hint="Search by Name"
                Toast.makeText(this, "Search by Name", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            else if (options[item] == "Employee Id") {
                searchId="2"
                binding.searchView.hint="Search by Employee Id"
                Toast.makeText(this, "Search by Employee Id", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            else if (options[item] == "Mobile") {
                searchId="3"
                binding.searchView.hint="Search by Mobile"
                Toast.makeText(this, "Search by Mobile", Toast.LENGTH_SHORT).show()

                dialog.dismiss()
            }


        }

        builder.show()
    }

    private fun searchReport() {
        binding.searchView.doOnTextChanged { text, _, _, _ ->
            if (searchId=="0"){
                searchOptions()
                Toast.makeText(this, "Select Any One Search Options", Toast.LENGTH_SHORT).show()
            }else{
                filterList(text.toString(),searchId)
            }

        }
    }
    private fun filterList(name: String, searchId: String) {

        searchList.clear()
        for (receipt in empList) {
            try{
                if (searchId=="1"){
                    if (receipt.name.lowercase().contains(name)) {
                        searchList.add(receipt)
                    }

                }
                else  if (searchId=="2"){
                    if (receipt.emp_id.lowercase().contains(name)) {
                        searchList.add(receipt)
                    }

                } else  if (searchId=="3"){
                    if (receipt.mobile.lowercase().contains(name)) {
                        searchList.add(receipt)
                    }

                }

            }catch (e:NullPointerException){
                e.printStackTrace()
            }
        }
        adapter = MyEmployeeAdapter()
        binding.employeeList.adapter = adapter
        binding.employeeList.layoutManager = LinearLayoutManager(this)
        adapter.differ.submitList(searchList)


    }

    private fun setData() {
        adapter = MyEmployeeAdapter()
        binding.employeeList.adapter = adapter
        binding.employeeList.layoutManager = LinearLayoutManager(this)

        employeeList = mutableListOf(
            EmployeeData("1", "Angler Technologies", "Ayyappan", "23", "8098464805"),
            EmployeeData("2", "Angler Technologies", "Bala", "50", "8098445805"),
            EmployeeData("3", "Angler Technologies", "Deva", "28", "8098464805"),
            EmployeeData("4", "Angler Technologies", "Fernando", "29", "8328464805"),
            EmployeeData("5", "Angler Technologies", "Hari", "32", "8498464805"),
            EmployeeData("6", "Angler Technologies", "Jeya Vel", "23", "9098464805"),
            EmployeeData("7", "Angler Technologies", "Karthi", "25", "6598464805"),
            EmployeeData("8", "Angler Technologies", "Lawrence", "22", "80953564805"),
            EmployeeData("9", "Angler Technologies", "Lalith", "20", "8009464805"),
            EmployeeData("10", "Angler Technologies", "Mani", "23", "8067464805"),
            EmployeeData("11", "Angler Technologies", "ManoKar", "29", "8066464805"),
            EmployeeData("12", "Angler Technologies", "Nagaraj", "33", "8098464805"),
            EmployeeData("13", "Angler Technologies", "Parthipan", "23", "8097764805"),
            EmployeeData("14", "Angler Technologies", "Pavan", "36", "8098994805"),
            EmployeeData("15", "Angler Technologies", "Raja", "46", "8099964805"),
            EmployeeData("16", "Angler Technologies", "Selva", "23", "8558464805"),
            EmployeeData("17", "Angler Technologies", "Thilip", "20", "8098444805"),
            EmployeeData("18", "Angler Technologies", "Vinith", "35", "8098400805"),
            EmployeeData("19", "Angler Technologies", "Vimal", "23", "8098844805"),
            EmployeeData("20", "Angler Technologies", "Vinoth", "40", "8099664805"),

            )
        empList=employeeList
        adapter.differ.submitList(employeeList)

    }
}