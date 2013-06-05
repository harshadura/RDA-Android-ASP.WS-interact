using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data;
using System.Data.Sql;
using System.Data.SqlClient;

[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]
public class Service : System.Web.Services.WebService
{
    public Service () {

        //Uncomment the following line if using designed components 
        //InitializeComponent(); 
    }

    [WebMethod]
    public String GetEmployeeByID(String id) {
          
            DataSet2TableAdapters.EmployeeTableAdapter dt1 = new DataSet2TableAdapters.EmployeeTableAdapter();
            String ename = "" + dt1.getEmpByID(id);
            return ename;
    }

    [WebMethod]
    public Employee[] GetEmployees()
    {
        var employees = new List<Employee>();

        try
        {
            string connect = @"Data Source=HARSHADURA-AZUS\SQLEXPRESS;Initial Catalog=RDA;Integrated Security=True";
            string query = "SELECT eid, ename FROM Employee";

                SqlConnection thisConnection = new SqlConnection(connect);
                thisConnection.Open();
                SqlCommand thisCommand = thisConnection.CreateCommand();
                thisCommand.CommandText = query;
                SqlDataReader thisReader = thisCommand.ExecuteReader();
                while (thisReader.Read())
                {
                    var emp = new Employee();
                    emp.eid = thisReader["eid"].ToString();
                    emp.ename = thisReader["ename"].ToString();
                    employees.Add(emp);
                }
                thisReader.Close();
                thisConnection.Close();
            }
            catch (SqlException e)
            {
                Console.WriteLine(e.Message);
            }
        return employees.ToArray();
    }
}
